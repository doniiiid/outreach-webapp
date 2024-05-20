package com.outreach.rest.payload.request;

import com.outreach.rest.dto.InventoryItemDTO;

import java.util.List;

public class InventoryItemRequest {
    private List<InventoryItemDTO> items;

    public List<InventoryItemDTO> getItems() {
        return items;
    }

    public void setItems(List<InventoryItemDTO> items) {
        this.items = items;
    }
}
