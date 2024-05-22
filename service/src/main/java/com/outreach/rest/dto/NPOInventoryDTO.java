package com.outreach.rest.dto;

public class NPOInventoryDTO {
    private Long id;

    private Long npoId;

    private String itemName;

    private int quantity;

    private int demand;

    public NPOInventoryDTO() {}

    public NPOInventoryDTO(Long id, Long npoId, String itemName, int quantity, int demand) {
        this.id = id;
        this.npoId = npoId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.demand = demand;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNpoId() {
        return npoId;
    }

    public void setNpoId(Long npoId) {
        this.npoId = npoId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDemand() {
        return demand;
    }

    public void setDemand(int demand) {
        this.demand = demand;
    }
}
