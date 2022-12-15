package com.online.shopping.services;

import com.online.shopping.constants.ErrorConstants;
import com.online.shopping.entity.Highlights;
import com.online.shopping.exception.HighlightsNotFoundException;
import com.online.shopping.mapper.HighlightsMapper;
import com.online.shopping.repository.HighlightsRepository;
import com.online.shopping.requestdto.HighlightsRequestDto;
import com.online.shopping.responsedto.HighlightsResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HighlightsService {

    @Autowired
    private HighlightsRepository highlightsRepository;

    @Autowired
    private HighlightsMapper highlightsMapper;

    public List<HighlightsResponseDto> getAllHighlights() {
        return highlightsRepository.findAll().stream().map(highlights -> highlightsMapper.convertEntityToDto(highlights)).collect(Collectors.toList());
    }

    public HighlightsResponseDto getSingleHighlights(int highlightId) {
        Highlights highlights = highlightsRepository.findById(highlightId).orElseThrow(() -> new HighlightsNotFoundException(ErrorConstants.HIGHLIGHTS_NOT_FOUND_ERROR + highlightId));
        return highlightsMapper.convertEntityToDto(highlights);
    }

    public HighlightsResponseDto addHighlights(HighlightsRequestDto highlightsRequestDto) {
        Highlights highlightRequest = highlightsMapper.convertDtoToEntity(highlightsRequestDto);
        Highlights highlightResponse = highlightsRepository.save(highlightRequest);
        return highlightsMapper.convertEntityToDto(highlightResponse);
    }

    public String removeHighlights(int highlightId) {
        highlightsRepository.delete(highlightsRepository.findById(highlightId).orElseThrow(() -> new HighlightsNotFoundException(ErrorConstants.HIGHLIGHTS_NOT_FOUND_ERROR + highlightId)));
        return "Successfully deleted the Product highlights where id:" + highlightId;
    }

}
