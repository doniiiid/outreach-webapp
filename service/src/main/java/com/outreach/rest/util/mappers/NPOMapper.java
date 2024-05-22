package com.outreach.rest.util.mappers;

import com.outreach.rest.dto.NPODto;
import com.outreach.rest.model.NPO;

public class NPOMapper {
    public static NPODto toDto(NPO npo) {
        NPODto npoDto = new NPODto(npo.getId(), npo.getName(), npo.getCategory());

        return npoDto;
    }
}
