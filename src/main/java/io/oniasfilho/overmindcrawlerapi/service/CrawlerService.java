package io.oniasfilho.overmindcrawlerapi.service;

import io.oniasfilho.overmindcrawlerapi.model.Movie;

import java.util.List;

public interface CrawlerService {
    public List<Movie> createMoviesList();
}
