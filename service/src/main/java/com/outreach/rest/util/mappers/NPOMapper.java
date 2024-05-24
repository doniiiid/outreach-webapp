package com.outreach.rest.util.mappers;

import com.outreach.rest.dto.NPODto;
import com.outreach.rest.model.NPO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

public class NPOMapper {
    public static NPODto toDto(NPO npo) {
        NPODto npoDto = new NPODto(npo.getId(), npo.getName(), npo.getCategory());

        return npoDto;
    }

    public static List<NPODto> toDTOList(List<NPO> npos) {
        return npos.stream().map(NPOMapper::toDto).collect(Collectors.toList());
    }

    public static Page<NPODto> convertPageNPOToPageNPODto(Page<NPO> npoPage) {
        return new PageImpl<>(
                toDTOList(npoPage.getContent()),
                npoPage.getPageable(),
                npoPage.getTotalElements()
        );
    }
}
