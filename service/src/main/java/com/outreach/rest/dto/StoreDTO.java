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

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String[] getCategory() {
        return category;
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
}
