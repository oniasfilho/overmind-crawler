package crawler;

import model.Movie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IMDBCrawler {
    public List<Movie> movies = new ArrayList<Movie>();
    public Document site = Jsoup.connect("https://www.imdb.com/chart/bottom").get();

    public IMDBCrawler() throws IOException {
    }

    public List<String> getTitles(){
        List<String> movieTitles = new ArrayList<>();
        Elements movieList = site.getElementsByClass("lister-list");
        Elements movieNames = movieList.select("a");

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
        Elements movieList = site.getElementsByClass("lister-list");

        movieList.forEach(list -> {
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
        Elements movieList = site.getElementsByClass("lister-list");
        movieList.select("a").forEach(link -> links.add(link.attr("href")));
        List<String> wholeLinks = new ArrayList<>();

        for(int i=0; i<links.size(); i++){
            if(wholeLinks.size()<10 && !(wholeLinks.contains("https://www.imdb.com/" + links.get(i)))){
                wholeLinks.add("https://www.imdb.com/" + links.get(i));
            }
        }

        return wholeLinks;
    }

    public void getDirectors(){
        List<String> diretores = new ArrayList<>();
        Elements movieList = site.getElementsByClass("lister-list");
        List<String> movieLinks = getLinks();

        System.out.println("");


    }

    public List<Movie> createMoviesList(){
        List<Movie> top10 = new ArrayList<>();
        List<Double> rates = getNotas();
        List<String> titles = getTitles();
        for(int i=0; i<10; i++){
            Movie newMovie = new Movie();
            newMovie.setNome(titles.get(i));
            newMovie.setNota(rates.get(i));
            top10.add(newMovie);
        }

        return top10;
    }

    public static void main(String[] args) throws IOException {
        new IMDBCrawler().getDirectors();
        System.out.println("deu bom");
    }
}
