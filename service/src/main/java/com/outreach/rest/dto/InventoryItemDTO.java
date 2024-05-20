package com.outreach.rest.dto;

public class InventoryItemDTO {
    private Long id;

    private Long storeId;

    private String name;

    private Float price;

    private int quantity;

    public InventoryItemDTO(Long id, Long storeId, String name, Float price, int quantity) {
        this.id = id;
        this.storeId = storeId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getStoreId() {
        return storeId;
    }
}
