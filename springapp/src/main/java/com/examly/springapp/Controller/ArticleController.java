package com.examly.springapp.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Article;
import com.examly.springapp.service.ArticleService;

@RestController
@RequestMapping("/api/kb-articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    @GetMapping("/{id}")
    public Optional<Article> getArticleById(@PathVariable("id") Long articleId) {
        return articleService.getArticleById(articleId);
    }

    @PostMapping
    public Article createArticle(@RequestBody Article article) {
        return articleService.createArticle(article);
    }

    @PutMapping("/{id}")
    public Article updateArticle(@PathVariable("id") Long articleId, @RequestBody Article article) {
        return articleService.updateArticle(articleId, article);
    }

    @DeleteMapping("/{id}")
    public String deleteArticle(@PathVariable("id") Long articleId) {
        boolean isDeleted = articleService.deleteArticle(articleId);
        return isDeleted ? "Article deleted successfully!" : "Article not found!";
    }
}

