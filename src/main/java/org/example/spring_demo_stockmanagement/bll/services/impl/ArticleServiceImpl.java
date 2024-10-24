package org.example.spring_demo_stockmanagement.bll.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.spring_demo_stockmanagement.bll.services.ArticleService;
import org.example.spring_demo_stockmanagement.dal.repositories.stock.ArticleRepository;
import org.example.spring_demo_stockmanagement.dal.repositories.stock.StockRepository;
import org.example.spring_demo_stockmanagement.dl.entities.stock.Article;
import org.example.spring_demo_stockmanagement.dl.entities.stock.Stock;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final StockRepository stockRepository;

    public Article save(Article article, MultipartFile image){
        if(articleRepository.existsByDesignation(article.getDesignation())){
            throw new IllegalArgumentException("Designation already exists");
        }
        article.setId(UUID.randomUUID());

        if(!image.isEmpty()){
            article.setPicture(saveImage(image));
        }

        Article newArticle = articleRepository.save(article);
        stockRepository.save(new Stock(
                UUID.randomUUID(),
                0,
                newArticle
        ));
        return newArticle;
    }



    public void delete(UUID uuid){
        if(!articleRepository.existsById(uuid)){
            throw new IllegalArgumentException("Article does not exist");
        }
        articleRepository.deleteById(uuid);
    }



    public Article findById(UUID id){
        return articleRepository.findById(id).orElseThrow();
    }




    @Override
    public void update(Article article, MultipartFile image) {
        Article existingArticle = articleRepository.findById(article.getId()).orElseThrow();

        if(articleRepository.existsInOtherArticleByDesignation(article.getId(),article.getDesignation())){
            throw new IllegalArgumentException("Designation already exists");
        }

        existingArticle.setDesignation(article.getDesignation());
        existingArticle.setUnitPriceExcludingTax(article.getUnitPriceExcludingTax());
        existingArticle.setVat(article.getVat());
        existingArticle.setPicture(article.getPicture());
        existingArticle.setCategory(article.getCategory());

        articleRepository.save(existingArticle);
    }



    private String saveImage(MultipartFile image){
        String imageName = UUID.randomUUID() + "_" + image.getOriginalFilename();
        Path imagePath = Path.of(System.getProperty("user.dir"), "src", "main", "resources", "static","images", imageName);
        try{
            Files.write(imagePath, image.getBytes());
            return imageName;
        } catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}