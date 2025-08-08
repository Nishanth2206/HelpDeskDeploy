package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Article;
import com.examly.springapp.repository.ArticleRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Optional<Article> getArticleById(Long articleId) {
        return articleRepository.findById(articleId);
    }

    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    public Article updateArticle(Long articleId, Article updatedArticle) {
        return articleRepository.findById(articleId).map(article -> {
            if (updatedArticle.getTitle() != null) {
                article.setTitle(updatedArticle.getTitle());
            }
            if (updatedArticle.getContent() != null) {
                article.setContent(updatedArticle.getContent());
            }
            return articleRepository.save(article);
        }).orElse(null);
    }

    public boolean deleteArticle(Long articleId) {
        if (articleRepository.existsById(articleId)) {
            articleRepository.deleteById(articleId);
            return true;
        }
        return false;
    }
}

