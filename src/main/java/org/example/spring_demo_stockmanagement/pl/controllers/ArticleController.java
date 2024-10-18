package org.example.spring_demo_stockmanagement.pl.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.spring_demo_stockmanagement.bll.services.ArticleService;
import org.example.spring_demo_stockmanagement.bll.services.CategoryService;
import org.example.spring_demo_stockmanagement.bll.services.StockService;
import org.example.spring_demo_stockmanagement.dl.entities.enums.VAT;
import org.example.spring_demo_stockmanagement.dl.entities.stock.Article;
import org.example.spring_demo_stockmanagement.dl.entities.stock.Category;
import org.example.spring_demo_stockmanagement.dl.entities.stock.Stock;
import org.example.spring_demo_stockmanagement.pl.models.dto.ArticleDTO;
import org.example.spring_demo_stockmanagement.pl.models.dto.ArticleDetailsDTO;
import org.example.spring_demo_stockmanagement.pl.models.forms.ArticleForm;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {

    private final StockService stockService;
    private final ArticleService articleService;
    private final CategoryService categoryService;

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

    @GetMapping("/create")
    public String createArticle(Model model){
        model.addAttribute("articleForm", new ArticleForm());
        model.addAttribute("vatOptions", VAT.values());
        model.addAttribute("categories", categoryService.findAll());
        return "article/create";
    }

    @PostMapping("/create")
    public String createArticle(@Valid @ModelAttribute ArticleForm articleForm, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            List<String> errors = bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            model.addAttribute("articleForm", articleForm);
            model.addAttribute("vatOptions", VAT.values());
            model.addAttribute("categories", categoryService.findAll());
            return "article/create";
        }
        Category category = categoryService.findById(articleForm.getCategoryId());
        Article article = articleForm.toArticle();
        article.setCategory(category);
        articleService.save(article, articleForm.getImage());
        return "redirect:/article";
    }
}
