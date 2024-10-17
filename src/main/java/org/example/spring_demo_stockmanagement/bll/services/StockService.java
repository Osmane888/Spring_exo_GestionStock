package org.example.spring_demo_stockmanagement.bll.services;

import org.example.spring_demo_stockmanagement.dl.entities.stock.Article;
import org.example.spring_demo_stockmanagement.dl.entities.stock.Stock;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


public interface StockService {

    List<Stock> getStock();

    Stock getStockByArticleId(UUID articleId);
}
