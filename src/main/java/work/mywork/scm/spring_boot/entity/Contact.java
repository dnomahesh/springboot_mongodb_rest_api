package work.mywork.scm.spring_boot.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Document(collection = "contacts")
public class Contact {

    @Id
    @Indexed(unique = true)
    private String id;
    private String name;
    @Indexed(unique = true)
    private String email;
    @Indexed(unique = true)
    private String phoneNumber;
    private String address;
    private String picture;
    private String description;
    private boolean favorite;
    private String websiteLink;
    private String linkedInLink;
    private String instagramLink;
    private String cloudinaryImagePublicId;

    @DBRef
    private User user;

    public Contact() {
    }

    public Contact(String id, String name, String email, String phoneNumber, String address, String picture,
                   String description, boolean favorite, String websiteLink, String linkedInLink,
                   String instagramLink, String cloudinaryImagePublicId, User user) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.picture = picture;
        this.description = description;
        this.favorite = favorite;
        this.websiteLink = websiteLink;
        this.linkedInLink = linkedInLink;
        this.instagramLink = instagramLink;
        this.cloudinaryImagePublicId = cloudinaryImagePublicId;
        this.user = user;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getWebsiteLink() {
        return websiteLink;
    }

    public void setWebsiteLink(String websiteLink) {
        this.websiteLink = websiteLink;
    }

    public String getLinkedInLink() {
        return linkedInLink;
    }

    public void setLinkedInLink(String linkedInLink) {
        this.linkedInLink = linkedInLink;
    }

    public String getInstagramLink() {
        return instagramLink;
    }

    public void setInstagramLink(String instagramLink) {
        this.instagramLink = instagramLink;
    }

    public String getCloudinaryImagePublicId() {
        return cloudinaryImagePublicId;
    }

    public void setCloudinaryImagePublicId(String cloudinaryImagePublicId) {
        this.cloudinaryImagePublicId = cloudinaryImagePublicId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Contact [id=" + id + ", name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber
                + ", address=" + address + ", picture=" + picture + ", description=" + description + ", favorite="
                + favorite + ", websiteLink=" + websiteLink + ", linkedInLink=" + linkedInLink + ", instagramLink="
                + instagramLink + ", cloudinaryImagePublicId=" + cloudinaryImagePublicId + ", user=" + user
                + ", getClass()=" + getClass() + ", getId()=" + getId() + ", getName()=" + getName() + ", getEmail()="
                + getEmail() + ", getPhoneNumber()=" + getPhoneNumber() + ", getAddress()=" + getAddress()
                + ", getPicture()=" + getPicture() + ", getDescription()=" + getDescription() + ", isFavorite()="
                + isFavorite() + ", getWebsiteLink()=" + getWebsiteLink() + ", getLinkedInLink()=" + getLinkedInLink()
                + ", getInstagramLink()=" + getInstagramLink() + ", hashCode()=" + hashCode()
                + ", getCloudinaryImagePublicId()=" + getCloudinaryImagePublicId() + ", getUser()=" + getUser()
                + ", toString()=" + super.toString() + "]";
    }

    
}