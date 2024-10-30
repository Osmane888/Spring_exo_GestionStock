package org.example.spring_demo_stockmanagement.api.controllers;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.example.spring_demo_stockmanagement.bll.services.ArticleService;
import org.example.spring_demo_stockmanagement.bll.services.CategoryService;
import org.example.spring_demo_stockmanagement.bll.services.StockService;
import org.example.spring_demo_stockmanagement.dl.entities.enums.VAT;
import org.example.spring_demo_stockmanagement.dl.entities.stock.Article;
import org.example.spring_demo_stockmanagement.dl.entities.stock.Category;
import org.example.spring_demo_stockmanagement.dl.entities.stock.Stock;
import org.example.spring_demo_stockmanagement.api.controllers.models.dto.ArticleDTO;
import org.example.spring_demo_stockmanagement.api.controllers.models.forms.ArticleForm;
import org.example.spring_demo_stockmanagement.api.controllers.models.forms.ArticleUpdateForm;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import java.net.URI;
import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {

    private final StockService stockService;
    private final ArticleService articleService;
    private final CategoryService categoryService;
    private final View error;

    @GetMapping
    public ResponseEntity<List<ArticleDTO>> getArticle() {
        List<Stock> stocks = stockService.getStock();

        // la méthode lambda dans le map est un raccourcis de : .map(s -> ArticleDTO.fromStock(s))
        List<ArticleDTO> articles = stocks.stream().map(ArticleDTO::fromStock).toList();
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getDetails(@PathVariable UUID id) {
        Article articleDetails = articleService.findById(id);
            return ResponseEntity.ok(articleDetails);
    }


    @PostMapping("/create")
    public ResponseEntity<Void> createArticle(@Valid @RequestBody ArticleForm articleForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            throw new ValidationException("Validation Error : " + errors);
        }
        Category category = categoryService.findById(articleForm.categoryId());
        Article article = articleForm.toArticle();
        article.setCategory(category);
        Article createdArticle = articleService.save(article, articleForm.image());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id").buildAndExpand(createdArticle.getId()).toUri();
        return ResponseEntity.created(location).build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> updateArticle(@Valid @RequestBody ArticleUpdateForm articleUpdateForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<String> errors = bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            throw new ValidationException("Validation Error : " + errors);
        }

        Article article = articleUpdateForm.toArticle();
        article.setCategory(categoryService.findById(articleUpdateForm.categoryId()));
        articleService.update(article, articleUpdateForm.image());
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@ModelAttribute UUID id) {
        articleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
