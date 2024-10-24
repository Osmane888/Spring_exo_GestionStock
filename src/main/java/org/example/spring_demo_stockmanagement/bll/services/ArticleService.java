package org.example.spring_demo_stockmanagement.bll.services;

import org.example.spring_demo_stockmanagement.dl.entities.stock.Article;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;

public interface ArticleService {


    Article save(Article article, MultipartFile image);

    void delete(UUID uuid);

    //A changer en retour Article
    Article findById(UUID uuid);

    void update(Article article, MultipartFile image);


}
