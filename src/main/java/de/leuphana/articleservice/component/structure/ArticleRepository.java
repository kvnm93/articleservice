package de.leuphana.articleservice.component.structure;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ArticleRepository extends CrudRepository<Article, UUID> {
}
