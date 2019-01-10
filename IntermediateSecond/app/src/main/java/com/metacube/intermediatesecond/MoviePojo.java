package com.metacube.intermediatesecond;

public class MoviePojo {
    private String movieName;
    private int movieImage;

    public MoviePojo(int movieImage, String movieName) {
        this.movieImage = movieImage;
        this.movieName = movieName;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getMovieImage() {
        return movieImage;
    }

    public void setMovieImage(int movieImage) {
        this.movieImage = movieImage;
    }
}
