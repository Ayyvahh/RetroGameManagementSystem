package system.dsaaca2.Models;

import system.dsaaca2.Controllers.GameAPI;
import system.dsaaca2.Datastructures.SillyList;
import system.dsaaca2.utils.Utilities;

import java.util.Objects;

public class GamesMachine {
    private String name, manufacturer, description, type, media, image;
    private int year;
    private double price;

    SillyList<Game> games = new SillyList<>();

    public GamesMachine(String name, String manufacturer, String description, String type, String media, String image, int year, double price) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.description = description;
        this.type = type;
        this.media = media;
        this.image = image;
        setYear(year);
        this.price = price;
    }


    public SillyList<Game> getGames() {
        return games;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getMedia() {
        return media;
    }
    public void setMedia(String media) {
        this.media = media;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        if(Utilities.intValidRange(year,1900,2023)){
            this.year = year;
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void addGame(Game g) {
        games.add(g);
        g.setGamesMachine(this);
    }

    public void removeGame(Game g) {
        games.remove(g);
    }

    @Override
    public String toString() {
        return "----------------------------------------------------------------" + "\n" +
                "                MACHINE NAME: " + name.toUpperCase() + "\n" +
                "----------------------------------------------------------------" + "\n" +
                "|Manufacturer: " + manufacturer.toUpperCase() + "\n" +
                "|Year: " + year + "\n" +
                "|Description: " + description.toUpperCase() + "\n" +
                "|Machine Type: " + type.toUpperCase() + "\n" +
                "|Media Type: " + media.toUpperCase() + "\n" +
                "|Image: " + image + "\n" +
                "|Price: €" + price + "\n" +
                "****************************************************************";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GamesMachine that = (GamesMachine) o;
        return getYear() == that.getYear() && Double.compare(getPrice(), that.getPrice()) == 0 && Objects.equals(getName(), that.getName()) && Objects.equals(getManufacturer(), that.getManufacturer()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getType(), that.getType()) && Objects.equals(getMedia(), that.getMedia()) && Objects.equals(getImage(), that.getImage()) && Objects.equals(getGames(), that.getGames());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getManufacturer(), getDescription(), getType(), getMedia(), getImage(), getYear(), getPrice(), getGames());
    }
}
