package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import sun.awt.image.ImageWatched;

import java.util.Map;
import java.util.Set;
import java.util.*;
import java.util.stream.*;
import javax.xml.ws.EndpointReference;

import java.util.ArrayList;

import java.util.List;

import static java.lang.String.valueOf;
import static org.codehaus.groovy.runtime.DefaultGroovyMethods.collect;

@Controller
public class MovieController {

  @RequestMapping("/")
  public String home(Model model) {
    return "home";
  }

  @RequestMapping("/now_playing")
  public String nowPlaying(Model model) {
    model.addAttribute("nowPlaying", getMovies("/now_playing"));
    return "now-playing";
  }
  @RequestMapping("medium_popular_long_name")
  public String mediumPopularLongNameMovies(Model model) {
    model.addAttribute("medPopLongNameMovieList", getMediumPopLongNameMovies("/now_playing"));
    return "medium-popular-long-name";
  }

  public static List<Movie> getMediumPopLongNameMovies(String route) {
    RestTemplate restTemplate = new RestTemplate();
    Map<?, ?> movieResponse = restTemplate.getForObject("https://api.themoviedb.org/3/movie" + route + "?api_key=be2a38521a7859c95e2d73c48786e4bb", Map.class);
    List<?> results = (List<?>) movieResponse.get("results");
    List<Movie> movieList = new ArrayList<>();
    for (Object object : results) {
      HashMap<String, String> thisMovie = (LinkedHashMap<String, String>) object;
      Movie currentMovie = new Movie();
      currentMovie.setTitle(thisMovie.get("title"));
      currentMovie.setOverview(thisMovie.get("overview"));
      currentMovie.setPopularity(Double.parseDouble(valueOf(thisMovie.get("popularity"))));
      currentMovie.setPosterPath(thisMovie.get("poster_path"));
      movieList.add(currentMovie);

    }
    for (Movie movie : movieList) {
      System.out.println("Length: " + movie.getTitle().length());
    }
    for (Movie movie : movieList) {
      System.out.println("Pop: " + movie.getPopularity());
    }
    List<Movie> medPopLongNameMovieList = movieList.stream()
      .filter(movie -> movie.getPopularity() >= 30)
      .filter(movie -> movie.getPopularity() <= 80)
      .filter(movie -> movie.getTitle().length() >= 10)
      .collect(Collectors.toList());
    for (Movie movie : medPopLongNameMovieList) {
      System.out.println(movie.getTitle().length());
    }
    return medPopLongNameMovieList;
  }

  public static List<Movie> getMovies(String route) {
    RestTemplate restTemplate = new RestTemplate();
    Map<?, ?> movieResponse = restTemplate.getForObject("https://api.themoviedb.org/3/movie" + route + "?api_key=be2a38521a7859c95e2d73c48786e4bb", Map.class);
    List<?> results = (List<?>) movieResponse.get("results");
    List<Movie> movieList = new ArrayList<>();
    for (Object object : results) {
      HashMap<String, String> thisMovie = (LinkedHashMap<String, String>) object;
      Movie currentMovie = new Movie();
      currentMovie.setTitle(thisMovie.get("title"));
      currentMovie.setOverview(thisMovie.get("overview"));
      currentMovie.setPopularity(Double.parseDouble(valueOf(thisMovie.get("popularity"))));
      currentMovie.setPosterPath(thisMovie.get("poster_path"));
      movieList.add(currentMovie);
    }
    return movieList;
  }

//  public static void printResponse() {
//    RestTemplate restTemplate = new RestTemplate();
//    Map<?, ?> movieResponse = restTemplate.getForObject("https://api.themoviedb.org/3/movie/now_playing?api_key=be2a38521a7859c95e2d73c48786e4bb", Map.class);
//    List<?> results = (List<?>) movieResponse.get("results");
//    List<Movie> movieList = new ArrayList<>();
//    for (Object object : results) {
//      HashMap<String, String> thisMovie = (LinkedHashMap<String, String>) object;
//      Movie currentMovie = new Movie();
//      currentMovie.setTitle(thisMovie.get("title"));
//      currentMovie.setOverview(thisMovie.get("overview"));
//      currentMovie.setPopularity(Double.parseDouble(valueOf(thisMovie.get("popularity"))));
//      currentMovie.setPosterPath(thisMovie.get("poster_path"));
////      System.out.println(currentMovie.toString());
//      movieList.add(currentMovie);
//    }
//    for (Movie movie : movieList) {
//      System.out.println(movie.getTitle());
//    }
//  }
}