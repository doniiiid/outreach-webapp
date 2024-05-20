package com.outreach.rest.controllers;

import com.outreach.rest.dto.StoreDTO;
import com.outreach.rest.model.Store;
import com.outreach.rest.payload.request.InventoryItemRequest;
import com.outreach.rest.repository.StoreRepository;
import com.outreach.rest.service.StoreService;
import com.outreach.rest.util.mappers.StoreMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/store")
public class StoreController {

    @Autowired
    private StoreRepository storeRespository;

    @Autowired
    private StoreService storeService;

    @GetMapping
    public ResponseEntity<Page<StoreDTO>> getAllStores(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<StoreDTO> users = storeService.getAllStores(page, size);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/detail")
    public ResponseEntity<Store> getStoreDetail(@RequestParam Long storeId) {
        Optional<Store> store = storeService.getStore(storeId);
        return store.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

//    @PostMapping("/detail")
//    public ResponseEntity<Store> updateStoreDetails(@RequestParam Long storeId) {}

    @PutMapping("/inventory")
    public ResponseEntity<StoreDTO> insertInventory (@RequestParam Long storeId, @Valid @RequestBody InventoryItemRequest inventoryItemRequest) {
        return ResponseEntity.ok(storeService.addStoreInventory(storeId, inventoryItemRequest.getItems()));
    }
}
