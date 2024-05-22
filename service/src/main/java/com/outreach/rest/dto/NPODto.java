package com.outreach.rest.dto;

import java.util.List;

public class NPODto {
    private Long id;

    private String name;

    private List<String> category;

    public NPODto() {};

    public NPODto(Long id, String name, List<String> category) {
        this.id = id;
        this.name = name;
        this.category = category;
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

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }
}
