package de.leuphana.articleservice.connector;

import de.leuphana.articleservice.component.behaviour.ArticleService;
import de.leuphana.articleservice.component.structure.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RestController
public class ArticleServiceRestConnectorProvider {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/article")
    public Set<Article> allArticles() {
        Set<Article> articles = new HashSet<Article>();

        Iterable<Article> iter = articleService.getAll();

        iter.forEach(article -> {
            articles.add(article);
        });

        return articles;
    }

    @PostMapping("/article/{articleId}/")
    public Article updateArticle(@PathVariable("articleId") UUID articleId, @RequestBody Article article) {
        article.setArticleId(articleId);

        return articleService.upsertArticle(article);
    }

    @PostMapping("/article")
    public Article upsertArticle(@RequestBody Article article) {
        return articleService.upsertArticle(article);
    }
}
