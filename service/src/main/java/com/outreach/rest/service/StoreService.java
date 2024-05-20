package com.outreach.rest.service;

import com.outreach.rest.dto.InventoryItemDTO;
import com.outreach.rest.dto.StoreDTO;
import com.outreach.rest.model.Store;
import com.outreach.rest.model.StoreInventory;
import com.outreach.rest.repository.StoreRepository;
import com.outreach.rest.util.mappers.StoreInventoryMapper;
import com.outreach.rest.util.mappers.StoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService {
    @Autowired
    private StoreRepository storeRepository;

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
}
