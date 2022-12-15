package com.online.shopping.mapper;

import com.online.shopping.entity.Highlights;
import com.online.shopping.requestdto.HighlightsRequestDto;
import com.online.shopping.responsedto.HighlightsResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HighlightsMapper {

    @Autowired
    private ModelMapper mapper;

    public Highlights convertDtoToEntity(HighlightsRequestDto highlightsRequestDto) {
        return new Highlights(highlightsRequestDto.getModelNumber(), highlightsRequestDto.getFeatures(), highlightsRequestDto.getSize());
    }

    public HighlightsResponseDto convertEntityToDto(Highlights highlights) {
        return mapper.map(highlights, HighlightsResponseDto.class);
    }

}
