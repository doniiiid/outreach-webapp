package com.outreach.rest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "store_inventory")
public class StoreInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="store_id", nullable = false)
    private Store store;

//    @NotBlank
    private String itemName;

//    @NotBlank
    private Float price;

//    @NotBlank
    private int quantity;

    public StoreInventory() {}

    public StoreInventory(Long id, String itemName, Float price, int quantity, Store store) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.store = store;
    }


    public Long getId() {
        return id;
    }

    public Store getStore() {
        return store;
    }

    public Float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}


