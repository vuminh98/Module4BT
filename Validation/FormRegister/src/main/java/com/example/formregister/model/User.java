package com.example.formregister.model;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 5,max = 45)
    private String firstName;
    @NotBlank
    @Size(min = 5,max = 45)
    private String lastName;

    @Pattern(regexp = "(84|0[3|5|7|8|9])+([0-9]{8})\\b")
    private String phoneNumber;
    @Min(18)
    private Double age;
    @Email
    private String email;

    public User() {
    }

    public User(@NotBlank @Size(min = 5,max = 45) String firstName,@NotBlank @Size(min = 5,max = 45) String lastName,@Pattern(regexp = "(84|0[3|5|7|8|9])+([0-9]{8})\\b") String phoneNumber,@Min(18) Double age,@Email String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.email = email;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
