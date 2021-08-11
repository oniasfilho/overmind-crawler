package io.oniasfilho.overmindcrawlerapi.crawler;

import io.oniasfilho.overmindcrawlerapi.model.Movie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class IMDBCrawler {
    private List<Movie> movies = new ArrayList<Movie>();
    private final Document SITE = Jsoup.connect("https://www.imdb.com/chart/bottom").header("Accept-Language", "en").get();
    private final String IMDB_URL ="https://www.imdb.com/";
    private final Elements MOVIE_LIST = SITE.getElementsByClass("lister-list");

    public IMDBCrawler() throws IOException {
    }

    Logger logger = LoggerFactory.getLogger(IMDBCrawler.class);

    public List<String> getTitles(){
        List<String> movieTitles = new ArrayList<>();
        Elements movieNames = MOVIE_LIST.select("a");

        for(int i = 0; i<movieNames.size(); i++){
            if(!movieNames.get(i).text().equals("") ){
                movieTitles.add(movieNames.get(i).text());
            }
            if(movieTitles.size()==10){
                break;
            }
        }
       return movieTitles;
    }

    public List<Double> getNotas(){
        List<Double> notas = new ArrayList<>();
        MOVIE_LIST.forEach(list -> {
                list.select("strong").forEach( rate -> {
                    if(notas.size()<=9){
                        notas.add(Double.parseDouble(rate.text()));
                    }
                });
        });
        return notas;
    }

    public List<String> getLinks(){
        List<String> links = new ArrayList<>();
        MOVIE_LIST.select("a").forEach(link -> links.add(link.attr("href")));
        List<String> wholeLinks = new ArrayList<>();

        for(int i=0; i<links.size(); i++){
            if(wholeLinks.size()<10 && !(wholeLinks.contains(IMDB_URL + links.get(i)))){
                wholeLinks.add(IMDB_URL + links.get(i));
            }
        }
        return wholeLinks;
    }

    public Document getMovieSite(int movieId) throws IOException {
        String linkDoFilme =  getLinks().get(movieId);
        Document siteDoFilme = Jsoup.connect(linkDoFilme).get();
        return siteDoFilme;
    }

    public List<String> getDirectors(int movieId) throws IOException {
        List<String> diretores = new ArrayList<>();

        Elements presentations = getMovieSite(movieId).select("li");

        presentations.forEach(each -> {
            if(each.text().startsWith("Directors") || each.text().startsWith("Director ")){
                each.select("a").forEach(name -> {
                    if(!(diretores.contains(name.text()))){
                        diretores.add(name.text());
                    }
                });
            }
        });
        return diretores;
    }

    public List<String> getCast(int movieId) throws IOException {
        List<String> cast = new ArrayList<>();

        Elements castListItems = getMovieSite(movieId).select("li");

        castListItems.forEach(each -> {
            if(each.text().startsWith("Stars")){
                each.select("a").forEach(name -> {
                    if(!(cast.contains(name.text())) && !(name.text().equals("Stars")) && !(name.text().equals(""))){
                        cast.add(name.text());
                    }
                });
            }
        });
        return cast;
    }

    public String getGoodComment(String movieURL) throws IOException{
        Document paginaDeBonsReviews = Jsoup.connect(movieURL+"reviews/?ref_=tt_ql_urv&dir=desc&ratingFilter=10").get();
        Elements listaDeComentarios = paginaDeBonsReviews.getElementsByClass("lister-list");
        Elements links; // = lista.select("a");
        Elements comments; // = lista.select("div");
        String titulo = null;
        String comentario = null;

        for(Element e: listaDeComentarios){
            links = e.select("a");
            comments = e.select("div");
            Elements titles;

            for(Element link: links){
                if(link.attr("href").startsWith("/review/rw") && titulo == null){
                    titulo = link.text();
                }
            }

            for(Element comment: comments){
                if(comment.attr("class").endsWith("show-more__control") && comentario == null){
                    comentario = comment.text();
                }
            }
        }
        return titulo + " | " + comentario;
    }

    public List<Movie> createMoviesList() throws IOException {
        logger.info(" -------- >>>  aguarde um momento enquanto o scrapper é executado  <<< --------");
        List<Movie> top10 = new ArrayList<>();
        List<String> titles = getTitles();
        List<Double> rates = getNotas();
        List<String> links = getLinks();
        for(int i=0; i<10; i++){
            Movie newMovie = new Movie();
            String linkDoFilme = links.get(i);
            String comentarioDoFilme = getGoodComment(linkDoFilme);
            newMovie.setNome(titles.get(i));
            newMovie.setNota(rates.get(i));
            newMovie.setDiretor(getDirectors(i));
            newMovie.setElenco(getCast(i));
            newMovie.setLinkDoFilme(linkDoFilme);
            newMovie.setComentario(comentarioDoFilme);
            top10.add(newMovie);
        }
        return top10;
    }

}
