package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {
  private String title;
  private String posterPath;
  private String overview;
  private double popularity;

  public Movie() {}

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getPosterPath() {
    return this.posterPath;
  }

  public void setPosterPath(String posterPath) {
    this.posterPath = posterPath;
  }

  public String getOverview() {
    return this.overview;
  }

  public void setOverview(String overview) {
    this.overview = overview;
  }

  public double getPopularity() {
    return this.popularity;
  }

  public void setPopularity(double popularity) {
    this.popularity = popularity;
  }

  @Override
  public String toString() {
    return this.getTitle() + ", " + this.getOverview() + ", " + this.getPosterPath() + ", " + this.getPopularity();
  }
}
