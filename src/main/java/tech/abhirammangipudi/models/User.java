package tech.abhirammangipudi.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class User {
    private final UUID userId;
    private String firstName;
    private String lastName;
    private final LocalDateTime dateOfBirth;
    private final String email;
    private String address;
    private String phoneNumber;
    private final String username;
    private String passwordHash;
    private LocalDateTime createdAt;
    private List<Account> accounts;

    public User(String firstName, String lastName, LocalDateTime dateOfBirth, String email, String address,
            String phoneNumber, String username, String passwordHash, List<Account> accounts) {
        this.userId = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.passwordHash = passwordHash;
        this.createdAt = LocalDateTime.now();
        this.accounts = accounts;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public UUID getUserId() {
        return userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
