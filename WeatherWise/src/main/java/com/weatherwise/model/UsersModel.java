package com.weatherwise.model;

public class UsersModel {
    private String fullName;
    private String email;
    private String contact;
    private String location;
    private String gender;
    private String username;
    private String password;         // raw password (before hashing)
    private String image;            // image path or filename
    private int roleId;

    public UsersModel() {}
    public UsersModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UsersModel( String fullName, String email, String contact, String location,
                      String gender, String username, String password, String image, int roleId) {
        this.fullName = fullName;
        this.email = email;
        this.contact = contact;
        this.location = location;
        this.gender = gender;
        this.username = username;
        this.password = password;
        this.image = image;
        this.roleId = roleId;
    }

    // Getters and Setters


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UsersModel [ fullName=" + fullName + ", email=" + email + 
               ", contact=" + contact + ", location=" + location + ", gender=" + gender +
               ", username=" + username + ", password=PROTECTED, image=" + image + 
               ", roleId=" + roleId + "]";
    }
}
