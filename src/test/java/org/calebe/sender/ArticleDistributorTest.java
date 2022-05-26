package org.calebe.sender;

import org.calebe.article.Article;
import org.calebe.article.Type;
import org.calebe.client.Channel;
import org.calebe.database.ArticleDataAccess;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class ArticleDistributorTest {
    @Mock
    private Channel sport;
    @Mock
    private Channel entertainment;
    @Mock
    private Channel other;
    @Mock
    private ArticleDataAccess dataAccess;
    @InjectMocks
    private ArticleDistributor articleDistributor;

    @Test
    public void sportGoesToSportPoliticsToOther() {



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