package com.outreach.rest.controllers;

import com.outreach.rest.dto.NPODetailDTO;
import com.outreach.rest.dto.NPODto;
import com.outreach.rest.model.NPO;
import com.outreach.rest.payload.request.NPOInventoryRequest;
import com.outreach.rest.repository.NPORepository;
import com.outreach.rest.service.NPOService;
import com.outreach.rest.util.mappers.NPODetailMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/npo")
public class NPOContoller {

    @Autowired
    private NPORepository npoRepository;

    @Autowired
    private NPOService npoService;

    @GetMapping
    public ResponseEntity<Page<NPODto>> getAllNpos(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(npoService.getAllNPOs(page, size));
    }

    @GetMapping("/inventory")
    public ResponseEntity<NPODetailDTO> getNPOInventory(@RequestParam Long id) {
        Optional<NPO> npo = npoService.getNPOInventory(id);
        return npo.map(npoInventory -> {
            return ResponseEntity.ok(NPODetailMapper.toDto(npoInventory));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/inventory")
    public ResponseEntity<NPODetailDTO> insertInventory(@RequestParam Long id, @Valid @RequestBody NPOInventoryRequest npoInventoryRequest) {
        return ResponseEntity.ok(npoService.addNPOInventory(id, npoInventoryRequest.getItems()));
    }

    @PostMapping("/inventory")
    public ResponseEntity<NPODetailDTO> updateInventory (@RequestParam Long id, @Valid @RequestBody NPOInventoryRequest npoInventoryRequest) {
        return ResponseEntity.ok(npoService.updateNPOInventory(id, npoInventoryRequest.getItems()));
    }
}
