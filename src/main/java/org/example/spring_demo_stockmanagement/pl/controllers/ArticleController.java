package org.example.spring_demo_stockmanagement.pl.controllers;

import lombok.RequiredArgsConstructor;
import org.example.spring_demo_stockmanagement.bll.services.StockService;
import org.example.spring_demo_stockmanagement.dl.entities.stock.Article;
import org.example.spring_demo_stockmanagement.dl.entities.stock.Stock;
import org.example.spring_demo_stockmanagement.pl.models.ArticleDTO;
import org.example.spring_demo_stockmanagement.pl.models.ArticleDetailsDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {

    private final StockService stockService;

    @GetMapping
    public String getArticle(Model model){
        List<Stock> stocks = stockService.getStock();

        // la méthode lambda dans le map est un raccourcis de : .map(s -> ArticleDTO.fromStock(s))
        List<ArticleDTO> articles = stocks.stream().map(ArticleDTO::fromStock).toList();
        model.addAttribute("articles", articles);
        return "article/index";
    }

    @GetMapping("/{id}")
    public String getDetails(@PathVariable UUID id, Model model){
        try{
            Stock articleDetails  = stockService.getStockByArticleId(id);
            ArticleDetailsDTO dto = ArticleDetailsDTO.detailsArticle(articleDetails);
            model.addAttribute("article", dto);
            return "article/details";
        } catch (NoSuchElementException e){
            return "error";
        }
    }
}
