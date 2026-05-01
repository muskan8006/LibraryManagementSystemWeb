package com.library.model;

public class Book {
    private int id;
    private String title;
    private String author;
    private int quantity;
    private boolean available;

    public Book() {
    }

    public Book(int id, String title, String author, int quantity, boolean available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.quantity = quantity;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.available = quantity > 0;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Book ID: " + id +
                ", Title: " + title +
                ", Author: " + author +
                ", Quantity: " + quantity +
                ", Available: " + (available ? "Yes" : "No");
    }
}