package fooddeliveryapp.totemsolutions.com.fooddeliveryapp;

/**
 * Created by asus on 09-04-2018.
 */

public class PromoCodeSet {
    public String name, url, price, details;

    public PromoCodeSet(String name, String url, String price, String details) {
        this.name = name;
        this.url = url;
        this.price = price;
        this.details = details;
    }

    public PromoCodeSet(String name, String url, String price) {
        this.name = name;
        this.url = url;
        this.price = price;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
