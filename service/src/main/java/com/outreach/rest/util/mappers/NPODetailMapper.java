package com.outreach.rest.util.mappers;

import com.outreach.rest.dto.NPODetailDTO;
import com.outreach.rest.model.NPO;

public class NPODetailMapper {
    public static NPODetailDTO toDto(NPO npo) {
        NPODetailDTO npoDto = new NPODetailDTO(NPOInventoryMapper.toDTOList(npo.getInventoryList()));

        return npoDto;
    }
}
