package com.outreach.rest.controllers;

import com.outreach.rest.dto.StoreDTO;
import com.outreach.rest.dto.StoreDetailDTO;
import com.outreach.rest.model.Store;
import com.outreach.rest.payload.request.InventoryItemRequest;
import com.outreach.rest.repository.StoreRepository;
import com.outreach.rest.service.StoreService;
import com.outreach.rest.util.mappers.StoreDetailMapper;
import com.outreach.rest.util.mappers.StoreInventoryMapper;
import com.outreach.rest.util.mappers.StoreMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
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
    public ResponseEntity<StoreDetailDTO> getStoreDetail(@RequestParam Long storeId) {
        Optional<Store> store = storeService.getStore(storeId);
        return store.map(storeDetail -> {
            return ResponseEntity.ok(StoreDetailMapper.toDTO(storeDetail));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/inventory")
    public ResponseEntity<StoreDetailDTO> updateInventory(@RequestParam Long storeId, @Valid @RequestBody InventoryItemRequest inventoryItemRequest) {
        return ResponseEntity.ok(storeService.updateStoreInventory(storeId, inventoryItemRequest.getItems()));
    }

    @PutMapping("/inventory")
    public ResponseEntity<StoreDTO> insertInventory (@RequestParam Long storeId, @Valid @RequestBody InventoryItemRequest inventoryItemRequest) {
        return ResponseEntity.ok(storeService.addStoreInventory(storeId, inventoryItemRequest.getItems()));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<StoreDTO>> searchStores(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam String store_name,
            @RequestParam String location_name
    ) {
        String decodedLocationName = "";
        String decodedStoreName = "";

        try {
            decodedLocationName = URLDecoder.decode(location_name, StandardCharsets.UTF_8.name());
            decodedStoreName = URLDecoder.decode(store_name, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return ResponseEntity.status(400).build();
        }

        String[] locationParts = decodedLocationName.split(", ");
        if (locationParts.length != 2) {
            return ResponseEntity.status(400).build();
        }

        String city = locationParts[0];
        String state = locationParts[1];

        return ResponseEntity.ok(storeService.searchStores(page, size, decodedStoreName, city, state));
    }
}
