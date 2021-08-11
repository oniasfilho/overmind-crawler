package io.oniasfilho.overmindcrawlerapi.controller;

import io.oniasfilho.overmindcrawlerapi.model.Movie;

import java.util.List;

public interface CrawlerController {
    public List<Movie> createMoviesList();
}
