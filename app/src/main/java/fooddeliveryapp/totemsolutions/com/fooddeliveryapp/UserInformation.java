package fooddeliveryapp.totemsolutions.com.fooddeliveryapp;

/**
 * Created by aakash on 2/15/2018.
 */

public class UserInformation {
    public String name;
    public String email;
    public String gender;
    public String dob;
    public String address;
    public String url;

    public UserInformation(String name, String gender, String dob, String address, String url) {
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.url = url;
    }

    public UserInformation(String name, String email, String gender, String dob, String address, String url) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}