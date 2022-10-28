package models;

public class Book {
    private int id;
    private final String title;
    private String author;
    private String genre;
    private String address;
    private String description;
    private boolean isAvailable;

    
    
    public Book(int id, String title) {
        this. id = id;
        this.title = title;
        this.isAvailable = true;
    }

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public Book(int id, String title, String author, String genre, String description) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.description = description;
        this.isAvailable = true;
    }

    public Book(int id, String title, String author, String genre, String address, String description,
            boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.address = address;
        this.description = description;
        this.isAvailable = isAvailable;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return "\n{ id: " + id + ",\n title: " + title + ",\n author: " + author + ",\n genre: " + genre + ",\n address: "
                + address + ",\n description: " + description + ",\n isAvailable: " + isAvailable + "\n}";
    }


}
