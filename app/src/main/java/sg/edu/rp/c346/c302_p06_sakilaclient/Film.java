package sg.edu.rp.c346.c302_p06_sakilaclient;

import java.io.Serializable;

public class Film implements Serializable {
    private int id;
    private String title;
    private int releaseYear;
    private String rating;

    public Film(int id, String title, int releaseYear, String rating) {
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getRating() {
        return rating;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {

        return title;//to display in ListView
    }
}
