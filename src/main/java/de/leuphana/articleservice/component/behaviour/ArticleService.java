package de.leuphana.articleservice.component.behaviour;

import de.leuphana.articleservice.component.structure.Article;
import de.leuphana.articleservice.component.structure.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.util.Optional;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"de.leuphana"})
@EntityScan( basePackages = {"de.leuphana"})
@EnableJpaRepositories(basePackages = "de.leuphana.articleservice.component.structure")
public class ArticleService {


    @Autowired
    private ArticleRepository articleRepository;

    /**
     *  Initial creation of Database entries
     */
    @PostConstruct
    public void initDb() {
        Article article1 = new Article();
        article1.setName("Corona Shampoo");
        article1.setStockQuantity(20);
        article1.setPrice(19.99);

        Article article2 = new Article();
        article2.setName("Festival Zelt");
        article2.setStockQuantity(10);
        article2.setPrice(99.99);

        Article article3 = new Article();
        article3.setName("Kaffeetasse");
        article3.setStockQuantity(200);
        article3.setPrice(9.99);

        Article article4 = new Article();
        article4.setName("Wodka");
        article4.setStockQuantity(120);
        article4.setPrice(12.99);

        articleRepository.save(article1);
        articleRepository.save(article2);
        articleRepository.save(article3);
        articleRepository.save(article4);
    }

    public Iterable<Article> getAll() {
        return articleRepository.findAll();
    }

    public Article upsertArticle(Article article){
        if (article.getArticleId() != null && articleRepository.existsById(article.getArticleId())){
            Optional<Article> oldArticle = articleRepository.findById(article.getArticleId());
            oldArticle.get().setName(article.getName());
            oldArticle.get().setPrice(article.getPrice());
            oldArticle.get().setStockQuantity(article.getStockQuantity());
            article = oldArticle.get();
        }

        return articleRepository.save(article);
    }

    public static void main(String[] args) {
        SpringApplication.run(ArticleService.class);
    }


}
