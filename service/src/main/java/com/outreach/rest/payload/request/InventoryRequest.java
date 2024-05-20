package com.outreach.rest.payload.request;

import com.outreach.rest.dto.InventoryItemDTO;

import java.util.List;

public class InventoryRequest {
    private Long storeId;

    private List<InventoryItemRequest> items;
}
