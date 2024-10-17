package org.example.spring_demo_stockmanagement.bll.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.spring_demo_stockmanagement.bll.services.StockService;
import org.example.spring_demo_stockmanagement.dal.repositories.stock.StockRepository;
import org.example.spring_demo_stockmanagement.dl.entities.stock.Article;
import org.example.spring_demo_stockmanagement.dl.entities.stock.Stock;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    @Override
    public List<Stock> getStock() {
        return stockRepository.findAll();
    }

    @Override
    public Stock getStockByArticleId(UUID articleId) {
        return stockRepository.findByArticleId(articleId).orElseThrow();
    }


}