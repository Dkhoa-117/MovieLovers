package com.example.movielovers.Models;

import java.util.ArrayList;

public class ResponseBody {
    private int page;
    private ArrayList<Movie> results;
    private int total_results;
    private int total_pages;

    public int getPage() {
        return page;
    }

    public ArrayList<Movie> getResults() {
        return results;
    }

    public int getTotal_results() {
        return total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }
}
