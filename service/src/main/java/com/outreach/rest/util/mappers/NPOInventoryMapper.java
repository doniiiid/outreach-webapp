package com.outreach.rest.util.mappers;

import com.outreach.rest.dto.NPOInventoryDTO;
import com.outreach.rest.model.NPO;
import com.outreach.rest.model.NPOInventory;
import com.outreach.rest.model.StoreInventory;

import java.util.List;
import java.util.stream.Collectors;

public class NPOInventoryMapper {
    public static NPOInventoryDTO toDTO(NPOInventory npoInventory) {
        NPOInventoryDTO npoInventoryDTO = new NPOInventoryDTO(
                npoInventory.getId(),
                npoInventory.getNpo().getId(),
                npoInventory.getItemName(),
                npoInventory.getQuantity(),
                npoInventory.getDemand()
        );

        return npoInventoryDTO;
    }

    public static List<NPOInventoryDTO> toDTOList(List<NPOInventory> npoInventoryList) {
        return npoInventoryList
                .stream()
                .map(NPOInventoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static NPOInventory toEntity(NPOInventoryDTO npoInventoryDTO, NPO npo) {
        NPOInventory npoInventory = new NPOInventory(
                npoInventoryDTO.getId(),
                npo,
                npoInventoryDTO.getItemName(),
                npoInventoryDTO.getQuantity(),
                npoInventoryDTO.getDemand()
        );

        return npoInventory;
    }

    public static List<NPOInventory> toEntityList(List<NPOInventoryDTO> inventoryDTOList, NPO npo) {
        return inventoryDTOList.stream().map(dto -> toEntity(dto, npo)).collect(Collectors.toList());
    }
}
