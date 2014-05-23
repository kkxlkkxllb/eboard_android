package org.eboard.app.core;

import java.io.Serializable;

/**
 * Created by libiao on 14-5-14.
 */
public class Movie implements Serializable {
    private String Title;
    private String Year;
    private String imdbID;
    private String Type;
    private String poster;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        this.Year = year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        this.Type = type;
    }
}
