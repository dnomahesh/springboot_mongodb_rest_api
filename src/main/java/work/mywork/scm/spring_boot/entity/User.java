package work.mywork.scm.spring_boot.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

    @Id
    @Indexed(unique = true)
    private String id;
    private String name;
    @Indexed(unique = true)
    private String email;
    private String password;
    private String about;
    private String profilePicture;
    @Indexed(unique = true)
    private String phoneNumber;
    private boolean enabled;
    private boolean emailVerified;
    private boolean phoneVerified;
    private Providers provider;
    private String emailToken;

    @DBRef
    private Set<Contact> contacts = new LinkedHashSet<>();

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public boolean isPhoneVerified() {
        return phoneVerified;
    }

    public void setPhoneVerified(boolean phoneVerified) {
        this.phoneVerified = phoneVerified;
    }

    public Providers getProvider() {
        return provider;
    }

    public void setProvider(Providers provider) {
        this.provider = provider;
    }

    public String getEmailToken() {
        return emailToken;
    }

    public void setEmailToken(String emailToken) {
        this.emailToken = emailToken;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    public void addContact(Contact contact) {
        this.contacts.add(contact);
    }

    public void removeContact(Contact contact) {
        this.contacts.remove(contact);
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", about=" + about
                + ", profilePicture=" + profilePicture + ", phoneNumber=" + phoneNumber + ", enabled=" + enabled
                + ", emailVerified=" + emailVerified + ", phoneVerified=" + phoneVerified + ", provider=" + provider
                + ", emailToken=" + emailToken + ", contacts=" + contacts + ", getId()=" + getId() + ", getName()="
                + getName() + ", getEmail()=" + getEmail() + ", getPassword()=" + getPassword() + ", getClass()="
                + getClass() + ", getAbout()=" + getAbout() + ", getProfilePicture()=" + getProfilePicture()
                + ", getPhoneNumber()=" + getPhoneNumber() + ", isEnabled()=" + isEnabled() + ", isEmailVerified()="
                + isEmailVerified() + ", isPhoneVerified()=" + isPhoneVerified() + ", getProvider()=" + getProvider()
                + ", getEmailToken()=" + getEmailToken() + ", getContacts()=" + getContacts() + ", hashCode()="
                + hashCode() + ", toString()=" + super.toString() + "]";
    }

    
}