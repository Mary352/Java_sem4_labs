package by.belstu.kryukova;

public class Book {

    private String name;
    private String authorFullName;
    private int releaseYear;
    private String publishingHouse;

    public Book() {
    }

    public Book(String name, String authorFullName, int releaseYear, String publishingHouse) {
        this.name = name;
        this.authorFullName = authorFullName;
        this.releaseYear = releaseYear;
        this.publishingHouse = publishingHouse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorFullName() {
        return authorFullName;
    }

    public void setAuthorFullName(String authorFullName) {
        this.authorFullName = authorFullName;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }
}
