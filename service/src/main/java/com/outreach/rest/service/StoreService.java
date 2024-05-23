package com.outreach.rest.service;

import com.outreach.rest.dto.InventoryItemDTO;
import com.outreach.rest.dto.StoreDTO;
import com.outreach.rest.dto.StoreDetailDTO;
import com.outreach.rest.model.Store;
import com.outreach.rest.model.StoreInventory;
import com.outreach.rest.repository.StoreRepository;
import com.outreach.rest.util.StoreNotFoundException;
import com.outreach.rest.util.mappers.StoreDetailMapper;
import com.outreach.rest.util.mappers.StoreInventoryMapper;
import com.outreach.rest.util.mappers.StoreMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StoreService {
    @Autowired
    private StoreRepository storeRepository;

    private static final Logger logger = LoggerFactory.getLogger(StoreService.class);


    public Page<StoreDTO> getAllStores(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Store> storePage = storeRepository.findAll(pageable);

        return storePage.map(StoreMapper::toDTO);
    }

    public Optional<Store> getStore(Long storeId) {
        Optional<Store> store = storeRepository.findById(storeId);
        return store;
    }

    public StoreDTO addStoreInventory(Long storeId, List<InventoryItemDTO> items) {
        Optional<Store> store = storeRepository.findById(storeId);
        Store exitingStore = store.get();
        List<StoreInventory> existingInventory = exitingStore.getStoreInventoryList();
        List<InventoryItemDTO> updatedInventoryDTO = StoreInventoryMapper.toDTOList(existingInventory);
        items.forEach(item -> {
            updatedInventoryDTO.add(item);
        });
        List<StoreInventory> updatedInventory = StoreInventoryMapper.toEntityList(updatedInventoryDTO, exitingStore);
        Store updatedStore = new Store(exitingStore.getId(), updatedInventory, exitingStore.getName(), exitingStore.getCategory());

        return StoreMapper.toDTO(storeRepository.save(updatedStore));
    }

    public StoreDetailDTO updateStoreInventory(Long id, List<InventoryItemDTO> items) {
        Optional<Store> store = storeRepository.findById(id);
        if(store.isPresent()) {
            Store existingStore = store.get();
            List<InventoryItemDTO> existingInventoryDTO = StoreInventoryMapper.toDTOList(existingStore.getStoreInventoryList());
            List<InventoryItemDTO> updatedInventoryDTO = items.stream().map(dto -> {
                Optional<InventoryItemDTO> existingItem = existingInventoryDTO
                        .stream()
                        .filter(item -> item.getId().equals(dto.getId()))
                        .findFirst();

                if (existingItem.isPresent()) {
                    InventoryItemDTO updatedItem = existingItem.get();
                    updatedItem.setName(dto.getName());
                    updatedItem.setPrice(dto.getPrice());
                    updatedItem.setQuantity(dto.getQuantity());
                    return updatedItem;
                } else {
                    return dto;
                }
            }).collect(Collectors.toList());
            Store updatedStore = new Store(
                    existingStore.getId(),
                    StoreInventoryMapper.toEntityList(updatedInventoryDTO, existingStore),
                    existingStore.getName(),
                    existingStore.getCategory());

            return StoreDetailMapper.toDTO(storeRepository.save(updatedStore));
        }
        else {
            throw new StoreNotFoundException("Store id not found: " + id);
        }
    }

    public Page<StoreDTO> searchStores(
            int page,
            int size,
            String name,
            String city,
            String state) {
        Pageable pageable = PageRequest.of(page, size);
        return StoreMapper.convertPageStoreToPageStoreDTO(storeRepository.findByNameStartingWithAndCityAndState(name, city, state, pageable));
    }
}
