package com.outreach.rest.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "npos")
public class NPO extends Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "npo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NPOInventory> inventoryList;

    private String name;

    private List<String> category;

    public NPO() {};

    public NPO(Long id, String name, List<NPOInventory> inventoryList, List<String> category) {
        this.id = id;
        this.name = name;
        this.inventoryList = inventoryList;
        this.category = category;
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<NPOInventory> getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(List<NPOInventory> inventoryList) {
        this.inventoryList = inventoryList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }
}
