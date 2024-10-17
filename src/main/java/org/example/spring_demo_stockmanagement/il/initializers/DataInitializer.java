package org.example.spring_demo_stockmanagement.il.initializers;

import lombok.RequiredArgsConstructor;
import org.example.spring_demo_stockmanagement.dal.repositories.stock.ArticleRepository;
import org.example.spring_demo_stockmanagement.dal.repositories.stock.CategoryRepository;
import org.example.spring_demo_stockmanagement.dal.repositories.stock.StockMovementRepository;
import org.example.spring_demo_stockmanagement.dal.repositories.stock.StockRepository;
import org.example.spring_demo_stockmanagement.dl.entities.enums.StockMovementType;
import org.example.spring_demo_stockmanagement.dl.entities.enums.VAT;
import org.example.spring_demo_stockmanagement.dl.entities.stock.Article;
import org.example.spring_demo_stockmanagement.dl.entities.stock.Category;
import org.example.spring_demo_stockmanagement.dl.entities.stock.Stock;
import org.example.spring_demo_stockmanagement.dl.entities.stock.StockMovement;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {


    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;
    private final StockRepository stockRepository;
    private final StockMovementRepository stockMovementRepository;


    @Override
    public void run(String... args) throws Exception{
        if(categoryRepository.count() == 0){
            List<Category> categories = List.of(
                     new Category(UUID.randomUUID(), "Jeux videos"),
                     new Category(UUID.randomUUID(), "Livres"),
                     new Category(UUID.randomUUID(), "Films"));
            categoryRepository.saveAll(categories);
        };

        if(articleRepository.count() == 0){
            List<Category> categories = categoryRepository.findAll();
            List<Article> articles = List.of(
                    new Article(UUID.randomUUID(), "Dragon Ball Sparking Zero",50L, VAT.TWENTY_ONE, null,categories.stream().filter(c -> c.getDesignation().equals("Jeux videos")).findFirst().orElseThrow()),
                    new Article(UUID.randomUUID(), "Le Seigneur des Anneaux",50L, VAT.TWENTY_ONE, null,categories.stream().filter(c -> c.getDesignation().equals("Livres")).findFirst().orElseThrow()),
                    new Article(UUID.randomUUID(), "Django",50L, VAT.TWENTY_ONE, null,categories.stream().filter(c -> c.getDesignation().equals("Films")).findFirst().orElseThrow())
            );
            articleRepository.saveAll(articles);
        };

        if(stockMovementRepository.count() == 0){
            List<Article> articles = articleRepository.findAll();
            List<StockMovement> stockMovements = List.of(
                    new StockMovement(UUID.randomUUID(), StockMovementType.STOCK_IN, 10, LocalDateTime.now(), articles.stream().filter(a -> a.getDesignation().equals("Dragon Ball Sparking Zero")).findFirst().orElseThrow()),
                    new StockMovement(UUID.randomUUID(), StockMovementType.STOCK_IN, 3, LocalDateTime.now(), articles.stream().filter(a -> a.getDesignation().equals("Le Seigneur des Anneaux")).findFirst().orElseThrow()),
                    new StockMovement(UUID.randomUUID(), StockMovementType.STOCK_IN, 49, LocalDateTime.now(), articles.stream().filter(a -> a.getDesignation().equals("Django")).findFirst().orElseThrow())
            );
            List<Stock> stocks = List.of(
                    new Stock(UUID.randomUUID(), stockMovements.get(0).getQuantity(), stockMovements.get(0).getArticle()),
                    new Stock(UUID.randomUUID(), stockMovements.get(1).getQuantity(), stockMovements.get(1).getArticle()),
                    new Stock(UUID.randomUUID(), stockMovements.get(2).getQuantity(), stockMovements.get(2).getArticle())
            );

            stockMovementRepository.saveAll(stockMovements);
            stockRepository.saveAll(stocks);
        }
    }
}
