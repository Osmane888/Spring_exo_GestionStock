package org.example.spring_demo_stockmanagement.bll.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.spring_demo_stockmanagement.bll.services.ArticleService;
import org.example.spring_demo_stockmanagement.dal.repositories.stock.ArticleRepository;
import org.example.spring_demo_stockmanagement.dl.entities.stock.Article;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    public Article save(Article article, MultipartFile image){
        if(articleRepository.existsByDesignation(article.getDesignation())){
            throw new IllegalArgumentException("Designation already exists");
        }
        article.setId(UUID.randomUUID());

        if(!image.isEmpty()){
            String imageName = UUID.randomUUID() + "_" + image.getOriginalFilename();
            Path imagePath = Path.of(System.getProperty("user.dir"), "serc", "main", "static", imageName);
        }

        return articleRepository.save(article);
    }
}