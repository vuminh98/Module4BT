package com.example.bookmanagement.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String codeUser;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

}
