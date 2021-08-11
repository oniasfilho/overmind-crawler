package io.oniasfilho.overmindcrawlerapi.controller.impl;

import io.oniasfilho.overmindcrawlerapi.controller.CrawlerController;
import io.oniasfilho.overmindcrawlerapi.model.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import io.oniasfilho.overmindcrawlerapi.service.CrawlerService;

import java.util.List;
@RestController
public class CrawlerControllerImpl implements CrawlerController {

    CrawlerService service;

    public CrawlerControllerImpl(CrawlerService service) {
        this.service = service;
    }

    @GetMapping("/api/IMDB")
    public List<Movie> createMoviesList() {
        return service.createMoviesList();
    }

    @GetMapping("/ping")
    public String ping(){
        return "pong";
    }
}
