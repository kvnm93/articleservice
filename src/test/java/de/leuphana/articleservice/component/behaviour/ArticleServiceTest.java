package de.leuphana.articleservice.component.behaviour;

import de.leuphana.articleservice.component.structure.Article;
import de.leuphana.articleservice.component.structure.ArticleRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ArticleService.class})
public class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleRepository articleRepository;

    private String articleName = "Corona Shampoo";
    private double price = 999.00;
    private int stockQuantity = 1;

    public Article createArticle () {
        Article article = new Article();
        article.setName(articleName);
        article.setPrice(price);
        article.setStockQuantity(stockQuantity);

        return article;
    }

    @org.junit.Test
    public void getAll() {
        Article article = this.createArticle();

        assertEquals(0, articleRepository.count());

        articleService.upsertArticle(article);

        assertEquals(1, articleRepository.count());
    }

    @org.junit.Test
    public void upsertArticle() {
        Article article = this.createArticle();

        Article newArticle = articleService.upsertArticle(article);

        assertNotNull(newArticle);
        assertEquals(articleName, newArticle.getName());
        assertEquals(price, newArticle.getPrice(), 0.001);
        assertEquals(stockQuantity, newArticle.getStockQuantity());
        assertNotNull(newArticle.getArticleId());
    }
}