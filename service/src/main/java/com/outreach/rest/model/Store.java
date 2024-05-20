package com.outreach.rest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "stores")
public class Store extends Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StoreInventory> storeInventoryList;

    @NotBlank
    private String name;

    @NotBlank
    private String[] category; //add enum

    public Store () {}

    public Store (Long id, List<StoreInventory> storeInventoryList, String name, String[] category) {
        this.id = id;
        this.storeInventoryList = storeInventoryList;
        this.name = name;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String[] getCategory() {
        return category;
    }

    public List<StoreInventory> getStoreInventoryList() {
        return storeInventoryList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String[] category) {
        this.category = category;
    }

    public void setStoreInventoryList(List<StoreInventory> storeInventoryList) {
        this.storeInventoryList = storeInventoryList;
    }
}
