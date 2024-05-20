package com.outreach.rest.dto;

import java.util.List;

public class StoreDetailDTO extends StoreDTO{
    private List<InventoryItemDTO> storeInventoryList;

    public List<InventoryItemDTO> getStoreInventoryList() {
        return storeInventoryList;
    }

    public void setStoreInventoryList(List<InventoryItemDTO> storeInventoryList) {
        this.storeInventoryList = storeInventoryList;
    }

    public StoreDetailDTO() {}

    public StoreDetailDTO(Long id, String name, String[] category, List<InventoryItemDTO> storeInventoryList) {
        super(id, name, category);
        this.storeInventoryList = storeInventoryList;
    }

}
