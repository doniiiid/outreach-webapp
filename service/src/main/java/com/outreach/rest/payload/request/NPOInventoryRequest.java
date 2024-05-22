package com.outreach.rest.payload.request;

import com.outreach.rest.dto.NPOInventoryDTO;

import java.util.List;

public class NPOInventoryRequest {
    private List<NPOInventoryDTO> items;

    public List<NPOInventoryDTO> getItems() {
        return items;
    }

    public void setItems(List<NPOInventoryDTO> items) {
        this.items = items;
    }
}
