package org.example.spring_demo_stockmanagement.bll.services;

import org.example.spring_demo_stockmanagement.dl.entities.stock.Article;
import org.springframework.web.multipart.MultipartFile;

public interface ArticleService {


    Article save(Article article, MultipartFile image);
}
