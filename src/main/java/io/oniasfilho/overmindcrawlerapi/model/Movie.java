package io.oniasfilho.overmindcrawlerapi.model;

import lombok.Data;

import java.util.Comparator;
import java.util.List;

@Data
public class Movie implements Comparable<Movie> {
    private String nome;
    private Double nota;
    private List<String> diretor;
    private List<String> elenco;
    private String comentario;
    private String linkDoFilme;


    @Override
    public int compareTo(Movie o) {
        return this.nota.compareTo(o.nota);
    }
}



