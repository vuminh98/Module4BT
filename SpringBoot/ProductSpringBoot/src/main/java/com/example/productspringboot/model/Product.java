package com.example.productspringboot.model;
import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private String description;
    private String nsx;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product() {
    }

    public Product(String name, double price, String description, String nsx) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.nsx = nsx;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getNsx() {
        return nsx;
    }

    public void setNsx(String nsx) {
        this.nsx = nsx;
    }
}
