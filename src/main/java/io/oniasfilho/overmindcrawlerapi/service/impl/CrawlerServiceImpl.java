package io.oniasfilho.overmindcrawlerapi.service.impl;

import io.oniasfilho.overmindcrawlerapi.crawler.IMDBCrawler;
import lombok.SneakyThrows;
import io.oniasfilho.overmindcrawlerapi.model.Movie;
import org.springframework.stereotype.Service;
import io.oniasfilho.overmindcrawlerapi.service.CrawlerService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CrawlerServiceImpl implements CrawlerService {

    IMDBCrawler crawler;

    public CrawlerServiceImpl(IMDBCrawler crawler) {
        this.crawler = crawler;
    }

    @SneakyThrows
    @Override
    public List<Movie> createMoviesList() {
        List<Movie> moviesList = crawler.createMoviesList();
        return moviesList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
    }
}
