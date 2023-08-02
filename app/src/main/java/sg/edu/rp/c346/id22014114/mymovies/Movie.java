package sg.edu.rp.c346.id22014114.mymovies;

import java.io.Serializable;

public class Movie implements Serializable {

    private int id;
    private String title;
    private String genre;
    private int year;
    private String rating;

    public Movie(int id, String title, String genre, int year, String rating) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
    }

    public static void add(Movie obj) {
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setMovieTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setMovieGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setMovieYear(int year) {
        this.year = year;
    }

    public String getRating() { return rating; }

    public void setMovieRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString()
    {
        String ratingST = "";
        if(rating == "G")
        {
            ratingST = "G";
        }
        else if(rating == "PG")
        {
            ratingST = "PG";
        }
        else if(rating == "PG13")
        {
            ratingST = "PG13";
        }
        else if(rating == "NC16")
        {
            ratingST = "NC16";
        }
        else if(rating == "M18")
        {
            ratingST = "M18";
        }
        else if(rating == "R21")
        {
            ratingST = "R21";
        }

        return ratingST;
    }

}

