package fooddeliveryapp.totemsolutions.com.fooddeliveryapp;

/**
 * Created by aakash on 3/3/2018.
 */

public class DessertImageUpload {
    public String name;
    public String url;
    public String price;
    public String description;
    public String minus;

    public DessertImageUpload(String name, String url, String price, String description, String minus) {
        this.name = name;
        this.url = url;
        this.price = price;
        this.description = description;
        this.minus = minus;
    }

    public DessertImageUpload() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMinus() {
        return minus;
    }

    public void setMinus(String minus) {
        this.minus = minus;
    }
}

