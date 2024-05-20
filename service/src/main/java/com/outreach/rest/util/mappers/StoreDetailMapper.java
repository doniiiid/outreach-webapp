package com.outreach.rest.util.mappers;

import com.outreach.rest.dto.StoreDetailDTO;
import com.outreach.rest.model.Store;

public class StoreDetailMapper {
    public static StoreDetailDTO toDTO(Store store) {
        StoreDetailDTO storeDetailDTO = new StoreDetailDTO(
                store.getId(),
                store.getName(),
                store.getCategory(),
                StoreInventoryMapper.toDTOList(store.getStoreInventoryList())
        );

        return storeDetailDTO;
    }
}
