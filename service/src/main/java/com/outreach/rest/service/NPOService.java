package com.outreach.rest.service;

import com.outreach.rest.dto.InventoryItemDTO;
import com.outreach.rest.dto.NPODetailDTO;
import com.outreach.rest.dto.NPODto;
import com.outreach.rest.dto.NPOInventoryDTO;
import com.outreach.rest.model.NPO;
import com.outreach.rest.model.NPOInventory;
import com.outreach.rest.repository.NPORepository;
import com.outreach.rest.util.NPONotFoundException;
import com.outreach.rest.util.mappers.NPODetailMapper;
import com.outreach.rest.util.mappers.NPOInventoryMapper;
import com.outreach.rest.util.mappers.NPOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NPOService {
    @Autowired
    private NPORepository npoRepository;

    public Page<NPODto> getAllNPOs(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<NPO> npoPage = npoRepository.findAll(pageable);

        return npoPage.map(NPOMapper::toDto);
    }

    public Optional<NPO> getNPOInventory(Long id) {
        Optional<NPO> npo = npoRepository.findById(id);
        return npo;
    }

    public NPODetailDTO addNPOInventory(Long id, List<NPOInventoryDTO> inventoryDTOList) {
        Optional<NPO> existingNPO = npoRepository.findById(id);
        if(existingNPO.isPresent()) {
            NPO updatedNPO = existingNPO.get();
            List<NPOInventory> updatedInventory = existingNPO.get().getInventoryList();
            inventoryDTOList.stream().forEach(item -> updatedInventory.add(NPOInventoryMapper.toEntity(item, updatedNPO)));
            updatedNPO.setInventoryList(updatedInventory);

            return NPODetailMapper.toDto(updatedNPO);
        } else {
            throw new NPONotFoundException("NPO id not found:" + id);
        }
    }

    public NPODetailDTO updateNPOInventory(Long id, List<NPOInventoryDTO> inventoryDTOList) {
        Optional<NPO> existingNPO = npoRepository.findById(id);
        if(existingNPO.isPresent()) {
            NPO updatedNPO = existingNPO.get();
            List<NPOInventoryDTO> existingInventory = NPOInventoryMapper.toDTOList(existingNPO.get().getInventoryList());
            List<NPOInventoryDTO> updatedInventory = inventoryDTOList.stream().map(dto -> {
                   Optional<NPOInventoryDTO> existingItem = existingInventory
                           .stream()
                           .filter(item -> item.getId().equals(dto.getId()))
                           .findFirst();
                   if(existingItem.isPresent()) {
                       NPOInventoryDTO item = existingItem.get();
                       item.setItemName(dto.getItemName());
                       item.setQuantity(dto.getQuantity());
                       item.setDemand(dto.getDemand());
                       return item;
                   } else {
                       return dto;
                   }
            }).collect(Collectors.toList());
            updatedNPO.setInventoryList(NPOInventoryMapper.toEntityList(updatedInventory, existingNPO.get()));

            return NPODetailMapper.toDto(npoRepository.save(updatedNPO));
        } else {
            throw new NPONotFoundException("NPO id not found:" + id);
        }
    }
}
