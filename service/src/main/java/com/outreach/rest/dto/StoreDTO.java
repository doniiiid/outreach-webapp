package com.outreach.rest.dto;

public class StoreDTO {
    private Long id;

    private String name;

    private String[] category;

    public StoreDTO() {}

    public StoreDTO (Long id, String name, String[] category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }
}
