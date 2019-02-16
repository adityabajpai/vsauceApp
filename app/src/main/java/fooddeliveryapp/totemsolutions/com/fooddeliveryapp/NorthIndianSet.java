package fooddeliveryapp.totemsolutions.com.fooddeliveryapp;

/**
 * Created by asus on 02-04-2018.
 */

public class NorthIndianSet {
    public String name;
    public String url;
    public String price;
    public String description;
    public String quantity;
    public String plus;
    public String minus;

    public NorthIndianSet(String name, String url, String description, String quantity, String plus, String minus) {
        this.name = name;
        this.url = url;
        this.description = description;
        this.quantity = quantity;
        this.plus = plus;
        this.minus = minus;
    }

    public NorthIndianSet() {
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPlus() {
        return plus;
    }

    public void setPlus(String plus) {
        this.plus = plus;
    }

    public String getMinus() {
        return minus;
    }

    public void setMinus(String minus) {
        this.minus = minus;
    }
}

