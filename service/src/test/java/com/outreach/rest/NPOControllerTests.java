package com.outreach.rest;

import com.outreach.rest.controllers.NPOContoller;
import com.outreach.rest.dto.NPODetailDTO;
import com.outreach.rest.dto.NPODto;
import com.outreach.rest.dto.NPOInventoryDTO;
import com.outreach.rest.dto.StoreDTO;
import com.outreach.rest.model.NPO;
import com.outreach.rest.model.NPOInventory;
import com.outreach.rest.payload.request.InventoryItemRequest;
import com.outreach.rest.payload.request.NPOInventoryRequest;
import com.outreach.rest.repository.NPORepository;
import com.outreach.rest.service.NPOService;
import com.outreach.rest.util.mappers.NPODetailMapper;
import com.outreach.rest.util.mappers.NPOInventoryMapper;
import com.outreach.rest.util.mappers.NPOMapper;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class NPOControllerTests {
    @InjectMocks
    private NPOContoller npoContoller;

    @Mock
    private NPOService npoService;

    @Mock
    private NPORepository npoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllNpos() {
        int page = 1;
        int size = 10;
        Long id1 = 1L;
        Long id2 = 2L;
        String npoName1 = "Direct Relief";
        String npoName2 = "Red Cross";
        List<String> category = new ArrayList<>();
        category.add("Clothes");
        List<NPOInventory> npoInventoryList = new ArrayList<>();

        Pageable pageable = PageRequest.of(page, size);
        NPO npo1 = new NPO(id1, npoName1, npoInventoryList, category);
        NPO npo2 = new NPO(id2, npoName2, npoInventoryList, category);
        NPODto npoDto1 = NPOMapper.toDto(npo1);
        NPODto npoDto2 = NPOMapper.toDto(npo2);

        Page<NPO> npoPage = new PageImpl<>(Arrays.asList(npo1, npo2));
        Page<NPODto> npoDtoPage = new PageImpl<>(Arrays.asList(npoDto1, npoDto2));

        when(npoRepository.findAll(pageable)).thenReturn(npoPage);
        when(npoService.getAllNPOs(page, size)).thenReturn(npoDtoPage);

        ResponseEntity<Page<NPODto>> response = npoContoller.getAllNpos(page, size);

        assertNotNull(response);
    }

    @Test
    public void testGetNPOInventory() {
        Long id = 1l;
        List<String> category = new ArrayList<>();
        category.add("Clothes");
        List<NPOInventory> npoInventoryList = new ArrayList<>();
        Optional<NPO> npo = Optional.of(new NPO(
                id,
                "Red Cross",
                npoInventoryList,
                category
        ));

        when(npoService.getNPOInventory(id)).thenReturn(npo);

        ResponseEntity<NPODetailDTO> response = npoContoller.getNPOInventory(id);

        assertNotNull(response);
    }

    @Test
    public void testInsertInventory() {
        Long id = 1L;
        Long itemId = 2L;
        String npoName = "Red Cross";
        List<String> category = new ArrayList<>();
        List<NPOInventory> inventoryList = new ArrayList<>();
        NPO npo = new NPO();
        NPOInventory inventoryItem = new NPOInventory(itemId, npo, "Shirt", 4, 10);
        NPOInventoryDTO inventoryItemDTO = NPOInventoryMapper.toDTO(inventoryItem);
        NPOInventoryRequest npoInventoryRequest = new NPOInventoryRequest();
        List<NPOInventoryDTO> inventoryItemDTOList = new ArrayList<>();
        inventoryItemDTOList.add(inventoryItemDTO);
        npoInventoryRequest.setItems(inventoryItemDTOList);

        Optional<NPO> existingNPO = Optional.of(new NPO(
                id,
                npoName,
                inventoryList,
                category
        ));

        NPO updatedNPO = new NPO(
                id,
                npoName,
                NPOInventoryMapper.toEntityList(inventoryItemDTOList, existingNPO.get()),
                category
        );

        when(npoRepository.findById(id)).thenReturn(existingNPO);
        when(npoRepository.save(updatedNPO)).thenReturn(updatedNPO);
        when(npoService.addNPOInventory(id, inventoryItemDTOList)).thenReturn(NPODetailMapper.toDto(updatedNPO));

        Assertions.assertEquals(0, existingNPO.get().getInventoryList().size());

        ResponseEntity<NPODetailDTO> response = npoContoller.insertInventory(id, npoInventoryRequest);

        assertNotNull(response);
    }

    @Test
    public void testUpdateInventory() {
        Long npoId = 1L;
        Long itemId = 2L;
        String npoName = "Red Cross";
        String itemName = "Shirt";
        String newItemName = "Pants";
        int quantity = 2;
        int demand = 2;
        NPOInventoryRequest npoInventoryRequest = new NPOInventoryRequest();
        List<NPOInventoryDTO> inventoryItemDTOList = new ArrayList<>();
        List<NPOInventory> existingInventory = new ArrayList<>();
        NPO existingNPO = new NPO(
                npoId,
                npoName,
                new ArrayList<>(),
                new ArrayList<>()
        );
        existingInventory.add(new NPOInventory(
                itemId,
                existingNPO,
                itemName,
                quantity,
                demand
        ));
        Optional<NPO> expectedNPO = Optional.of(new NPO(
                npoId,
                npoName,
                existingInventory,
                new ArrayList<>()
        ));

        NPOInventory newItem = new NPOInventory(
                itemId,
                expectedNPO.get(),
                newItemName,
                quantity,
                demand
        );

        List<NPOInventory> newInventory = new ArrayList<>();
        newInventory.add(newItem);
        inventoryItemDTOList.add(NPOInventoryMapper.toDTO(newItem));
        npoInventoryRequest.setItems(inventoryItemDTOList);
        NPO updatedNPO = new NPO(
                npoId,
                npoName,
                newInventory,
                new ArrayList<>()
        );
        NPODetailDTO updatedNpoDTO = NPODetailMapper.toDto(updatedNPO);


        assertEquals(1, expectedNPO.get().getInventoryList().size());
        assertEquals(itemName, expectedNPO.get().getInventoryList().get(0).getItemName());

        when(npoRepository.findById(npoId)).thenReturn(expectedNPO);
        when(npoService.updateNPOInventory(npoId, inventoryItemDTOList)).thenReturn(updatedNpoDTO);
        when(npoRepository.save(updatedNPO)).thenReturn(updatedNPO);

        ResponseEntity<NPODetailDTO> response = npoContoller.updateInventory(npoId, npoInventoryRequest);

        assertNotNull(response);
        assertEquals(1, response.getBody().getInventoryList().size());
        assertEquals(newItemName, response.getBody().getInventoryList().get(0).getItemName());
    }

    @Test
    public void testSearchNPOs() {
        String npoName1 = "Red Cross";
        String npoName2 = "Blue Shield";
        String location_name = "Houston%2C%20TX";
        String city = "Houston";
        String state = "TX";
        String npo_name = "Red";

        NPO npo1 = new NPO(
                1L,
                npoName1,
                new ArrayList<>(),
                new ArrayList<>()
        );
        NPO npo2 = new NPO(
                2L,
                npoName2,
                new ArrayList<>(),
                new ArrayList<>()
        );
        Page<NPO> npoPage = new PageImpl<>(Arrays.asList(npo1, npo2));
        Pageable pageable = PageRequest.ofSize(1);

        when(npoRepository.findByNameStartingWithAndCityAndState(
                npo_name,
                city,
                state,
                pageable
        )).thenReturn(npoPage);
        when(npoService.searchNpos(
                0,
                10,
                npo_name,
                city,
                state
        )).thenReturn(NPOMapper.convertPageNPOToPageNPODto(npoPage));

        ResponseEntity<Page<NPODto>> response = npoContoller.searchNPOs(0, 10, npo_name, location_name);

        verify(npoService, times(1)).searchNpos(
                0,
                10,
                npo_name,
                city,
                state);

        verifyNoMoreInteractions(npoService);
        assertEquals(1, response.getBody().getTotalPages());
    }
}
