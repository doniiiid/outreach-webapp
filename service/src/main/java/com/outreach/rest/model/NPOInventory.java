package com.outreach.rest.model;

import jakarta.persistence.*;

public class NPOInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="npo_id", nullable = false)
    private NPO npo;

    private String itemName;

    private int quantity;

    private int demand;

    public NPOInventory() {};

    public NPOInventory(Long id, NPO npo, String itemName, int quantity, int demand) {
        this.id = id;
        this.npo = npo;
        this.itemName = itemName;
        this.quantity = quantity;
        this.demand = demand;
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NPO getNpo() {
        return npo;
    }

    public void setNpo(NPO npo) {
        this.npo = npo;
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
