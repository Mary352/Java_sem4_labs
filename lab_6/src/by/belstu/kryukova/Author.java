package by.belstu.kryukova;

public class Author {

    private String full_name;
    private String country;

    public Author(String full_name, String country) {
        this.full_name = full_name;
        this.country = country;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
