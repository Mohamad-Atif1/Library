package com.example.liabrary;

public class Book {

    private String name;
    private String urlImage;
    private int id;
    private int pages;
    private String shortDes;
    private String longDes;
    private String author;
    private boolean isExpended;



    public Book(String name,String author, String urlImage, int id, int pages, String shortDec, String longDes) {
        this.name = name;
        this.urlImage = urlImage;
        this.id = id;
        this.pages = pages;
        this.shortDes = shortDec;
        this.longDes = longDes;
        this.author = author;
        isExpended = false;

    }

    public String getLongDes() {
        return longDes;
    }

    public void setLongDes(String longDes) {
        this.longDes = longDes;
    }

    public boolean isExpended() {
        return isExpended;
    }

    public void setExpended(boolean expended) {
        isExpended = expended;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getShortDes() {
        return shortDes;
    }

    public void setShortDes(String shortDes) {
        this.shortDes = shortDes;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", urlImage='" + urlImage + '\'' +
                ", id=" + id +
                ", pages=" + pages +
                ", shortDec='" + shortDes + '\'' +
                ", longDec='" + longDes + '\'' +
                '}';
    }
}
