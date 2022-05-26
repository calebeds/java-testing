package org.calebe.database;

import org.calebe.article.Article;

import java.util.List;

public interface ArticleDataAccess {
    /**
     * @return all the articles from today
     */
    List<Article> getTodaysArticles();
}
