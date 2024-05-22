package com.outreach.rest.dto;

import java.util.List;

public class NPODetailDTO {
    private List<NPOInventoryDTO> inventoryList;

    public List<NPOInventoryDTO> getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(List<NPOInventoryDTO> inventoryList) {
        this.inventoryList = inventoryList;
    }

    public NPODetailDTO(List<NPOInventoryDTO> inventoryList) {
        this.inventoryList = inventoryList;
    }
}
