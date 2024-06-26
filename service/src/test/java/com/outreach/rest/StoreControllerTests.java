package com.outreach.rest;

import com.outreach.rest.controllers.StoreController;
import com.outreach.rest.dto.InventoryItemDTO;
import com.outreach.rest.dto.StoreDTO;
import com.outreach.rest.dto.StoreDetailDTO;
import com.outreach.rest.model.Store;
import com.outreach.rest.model.StoreInventory;
import com.outreach.rest.payload.request.InventoryItemRequest;
import com.outreach.rest.repository.StoreRepository;
import com.outreach.rest.service.StoreService;
import com.outreach.rest.util.mappers.StoreDetailMapper;
import com.outreach.rest.util.mappers.StoreMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


public class StoreControllerTests {
    private MockMvc mockMvc;

    @InjectMocks
    private StoreController storeController;

    @Mock
    private StoreService storeService;

    @Mock
    private StoreRepository storeRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(storeController).build();
    }

    @Test
    public void testGetAllStores() {
        int page = 1;
        int size = 10;
        Long id1 = 1L;
        Long id2 = 2L;
        String storeName1 = "Walmart";
        String storeName2 = "Kroger";
        List<StoreInventory> storeInventoryList = new ArrayList<>();

        Pageable pageable = PageRequest.of(page, size);
        String[] category = new String[1];
        category[0] = "Grocery";

        Store store1 = new Store(id1, storeInventoryList, storeName1, category);
        Store store2 = new Store(id2, storeInventoryList, storeName2, category);
        StoreDTO storeDTO1 = new StoreDTO(id1, storeName1, category);
        StoreDTO storeDTO2 = new StoreDTO(id2, storeName2, category);

        Page<Store> storePage = new PageImpl<>(Arrays.asList(store1, store2));
        Page<StoreDTO> storeDTOPage = new PageImpl<>(Arrays.asList(storeDTO1, storeDTO2));

        when(storeRepository.findAll(pageable)).thenReturn(storePage);
        when(storeService.getAllStores(page, size)).thenReturn(storeDTOPage);

        ResponseEntity<Page<StoreDTO>> response = storeController.getAllStores(page, size);

        assertNotNull(response);
    }

    @Test
    public void testGetStoreDetail() {
        Long id = 1l;
        String[] category = new String[1];
        category[0] = "Grocery";
        List<StoreInventory> storeInventoryList = new ArrayList<>();

        Optional<Store> store = Optional.of(new Store(
                id,
                storeInventoryList,
                "Walmart",
                category
        ));
        when(storeService.getStore(id)).thenReturn(store);

        ResponseEntity<StoreDetailDTO> response = storeController.getStoreDetail(id);

        assertNotNull(response);
    }

    @Test
    public void testInsertInventory() {
        Long id = 1l;
        Long itemId = 2L;
        List<StoreInventory> storeInventoryList = new ArrayList<>();
        Store existingStore = new Store();
        StoreInventory inventoryItem = new StoreInventory(
                itemId,
                "Toothpaste",
                3.99f,
                13,
                existingStore
        );
        InventoryItemDTO inventoryItemDTO = new InventoryItemDTO(
                itemId,
                id,
                "Toothpaste",
                3.99f,
                13
        );
        List<InventoryItemDTO> inventoryItemDTOList = new ArrayList<>();
        inventoryItemDTOList.add(inventoryItemDTO);

        List<StoreInventory> updatedInventoryList = new ArrayList<>();
        updatedInventoryList.add(inventoryItem);
        InventoryItemRequest inventoryItemRequest = new InventoryItemRequest();
        inventoryItemRequest.setItems(inventoryItemDTOList);

        Optional<Store> store = Optional.of(new Store(
                id,
                storeInventoryList,
                "Walmart",
                new String[1]
        ));

        Store updatedStore = new Store(
                id,
                updatedInventoryList,
                "Walmart",
                new String[1]
        );
        StoreDTO updatedStoreDTO = StoreMapper.toDTO(updatedStore);
        Assertions.assertEquals(0, store.get().getStoreInventoryList().size());

        when(storeRepository.findById(id)).thenReturn(store);
        when(storeRepository.save(updatedStore)).thenReturn(updatedStore);
        when(storeService.addStoreInventory(id, inventoryItemDTOList)).thenReturn(updatedStoreDTO);

        ResponseEntity<StoreDTO> response = storeController.insertInventory(id, inventoryItemRequest);

        assertNotNull(response);
    }

    @Test
    public void testUpdateStoreInventory() {
        Long storeId = 1L;
        Long inventoryId = 2L;
        String itemName = "Toothpaste";
        Float price = 3.99f;
        int quantity = 4;
        Optional<Store> store = Optional.of(new Store(storeId, new ArrayList<StoreInventory>(), "CVS", new String[1]));
        StoreInventory newItem = new StoreInventory(
                inventoryId,
                itemName,
                price,
                quantity,
                new Store(storeId, new ArrayList<StoreInventory>(), "CVS", new String[1])
        );

        List<StoreInventory> newInventory = new ArrayList<>();
        newInventory.add(newItem);
        Store updatedStore = new Store(
                store.get().getId(),
                newInventory,
                store.get().getName(),
                store.get().getCategory()
        );
        StoreDetailDTO updatedStoreDTO = StoreDetailMapper.toDTO(updatedStore);
        InventoryItemRequest inventoryItemRequest = new InventoryItemRequest();
        List<InventoryItemDTO> items = new ArrayList<>();
        items.add(new InventoryItemDTO(inventoryId, storeId, itemName, price, quantity));
        inventoryItemRequest.setItems(items);

        Assertions.assertEquals(0, store.get().getStoreInventoryList().size());

        when(storeRepository.findById(storeId)).thenReturn(store);
        when(storeService.updateStoreInventory(storeId, items)).thenReturn(updatedStoreDTO);
        when(storeRepository.save(updatedStore)).thenReturn(updatedStore);

        ResponseEntity<StoreDetailDTO> response = storeController.updateInventory(storeId, inventoryItemRequest);

        assertNotNull(response);

        assertEquals(1, response.getBody().getStoreInventoryList().size());
    }

    @Test
    public void testSearchStores() throws Exception {
        String storeName1 = "Walgreens";
        String storeName2 = "Walmart";
        String location_name = "Houston%2C%20TX";
        String city = "Houston";
        String state = "TX";
        String store_name = "Wa;";
        Store store1 = new Store(
                1L,
                storeName1,
                new String[0]
        );
        Store store2 = new Store(
                2L,
                storeName2,
                new String[0]
        );

        List<Store> stores = new ArrayList<>(Arrays.asList(store1, store2));
        Page<Store> storePage = new PageImpl<>(stores);
        Pageable pageable = PageRequest.ofSize(1);

        when(storeRepository.findByNameStartingWithAndCityAndState(
                store_name,
                city,
                state,
                pageable)).thenReturn(storePage);
        when(storeService.searchStores(
                0,
                10,
                store_name,
                city,
                state)).thenReturn(StoreMapper.convertPageStoreToPageStoreDTO(storePage));

        ResponseEntity<Page<StoreDTO>> response = storeController.searchStores(0, 10, store_name, location_name);

        verify(storeService, times(1)).searchStores(0,
                10,
                store_name,
                city,
                state);

        verifyNoMoreInteractions(storeService);
    }
}
