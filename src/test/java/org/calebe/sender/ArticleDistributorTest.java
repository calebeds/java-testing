package org.calebe.sender;

import org.calebe.article.Article;
import org.calebe.article.Type;
import org.calebe.client.Channel;
import org.calebe.database.ArticleDataAccess;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.any;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ArticleDistributorTest {
    @Test
    public void sportGoesToSportPoliticsToOther() {
        Channel sport = mock(Channel.class);
        Channel entertainment = mock(Channel.class);
        Channel other = mock(Channel.class);
        ArticleDataAccess dataAccess = mock(ArticleDataAccess.class);
        ArticleDistributor articleDistributor = new ArticleDistributor(sport, entertainment, other, dataAccess);


        //Given this list of articles is returned from the database
        List<Article> list = Arrays.asList(
                new Article("Sport is fun", Type.SPORT),
                new Article("Politics is sad", Type.POLITICS));

        when(dataAccess.getTodaysArticles()).thenReturn(list);

        // When we distribute
        articleDistributor.distributeTodays();

        // Then one goes to sport and one goes to other
        verify(sport).accept((Article) any());
        verify(other, times(1)).accept((Article) any());
        verify(entertainment, never()).accept((Article) any());
    }
}