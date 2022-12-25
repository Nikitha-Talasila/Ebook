package com.example.ebook;

public class helper {

    String Book_Addition, App_Perfomance, Query;

    public String getBook_Addition() {
        return Book_Addition;
    }

    public void setBook_Addition(String book_Addition) {
        Book_Addition = book_Addition;
    }

    public String getApp_Perfomance() {
        return App_Perfomance;
    }

    public void setApp_Perfomance(String app_Perfomance) {
        App_Perfomance = app_Perfomance;
    }

    public String getQuery() {
        return Query;
    }

    public void setQuery(String query) {
        Query = query;
    }

    public helper(String book_Addition, String app_Perfomance, String query) {
        Book_Addition = book_Addition;
        App_Perfomance = app_Perfomance;
        Query = query;
    }
}
