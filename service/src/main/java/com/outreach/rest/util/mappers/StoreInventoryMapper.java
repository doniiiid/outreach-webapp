package com.outreach.rest.util.mappers;

import com.outreach.rest.dto.InventoryItemDTO;
import com.outreach.rest.dto.StoreDTO;
import com.outreach.rest.model.Store;
import com.outreach.rest.model.StoreInventory;

import java.util.List;
import java.util.stream.Collectors;

public class StoreInventoryMapper {
    public static InventoryItemDTO toDTO(StoreInventory storeInventory) {
        InventoryItemDTO inventoryItemDTO = new InventoryItemDTO(
                storeInventory.getId(),
                storeInventory.getStore().getId(),
                storeInventory.getItemName(),
                storeInventory.getPrice(),
                storeInventory.getQuantity()
        );

        return inventoryItemDTO;
    }

    public static List<InventoryItemDTO> toDTOList(List<StoreInventory> storeInventory) {
        return storeInventory.stream().map(StoreInventoryMapper::toDTO).collect(Collectors.toList());
    }

    public static StoreInventory toEntity(InventoryItemDTO inventoryItemDTO, Store store) {
        StoreInventory storeInventory = new StoreInventory(
                inventoryItemDTO.getId(),
                inventoryItemDTO.getName(),
                inventoryItemDTO.getPrice(),
                inventoryItemDTO.getQuantity(),
                store
        );

        return storeInventory;
    }

    public static List<StoreInventory> toEntityList(List<InventoryItemDTO> inventoryItemDTOS, Store store) {
        return inventoryItemDTOS.stream().map(dto -> toEntity(dto, store)).collect(Collectors.toList());
    }
}

