package com.outreach.rest.util.mappers;

import com.outreach.rest.dto.StoreDTO;
import com.outreach.rest.model.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

public class StoreMapper {
    public static StoreDTO toDTO(Store store) {
        StoreDTO storeDTO = new StoreDTO(store.getId(), store.getName(), store.getCategory());

        return storeDTO;
    }

    public static List<StoreDTO> toDTOList(List<Store> stores) {
        return stores.stream().map(StoreMapper::toDTO).collect(Collectors.toList());
    }

    public static Page<StoreDTO> convertPageStoreToPageStoreDTO(Page<Store> storePage) {
        List<Store> stores = storePage.getContent();
        List<StoreDTO> storeDTOList = toDTOList(stores);
        Page<StoreDTO> storeDTOPage = new PageImpl<>(storeDTOList, storePage.getPageable(), storePage.getTotalElements());

        return storeDTOPage;
    }
}
