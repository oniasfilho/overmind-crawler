package io.oniasfilho.overmindcrawlerapi.util;

import io.oniasfilho.overmindcrawlerapi.crawler.IMDBCrawler;

import java.io.IOException;
import java.util.Comparator;

public class TestUtil {
    public static void main(String[] args) throws IOException {
        //essa classe foi feita para testar manualmente (sem depender da API) o web crawler

        new IMDBCrawler().createMoviesList().stream().sorted(Comparator.reverseOrder()).forEach(movie ->{
            System.out.println(movie.toString());
        });
    }
}
