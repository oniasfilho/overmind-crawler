package model;

import lombok.Data;

import java.util.List;

@Data
public class Movie {
    private String nome;
    private Double nota;
    private List<String> diretor;
    private List<String> elenco;
    private List<String> comentario;
}


