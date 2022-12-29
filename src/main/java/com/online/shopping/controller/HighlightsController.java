package com.online.shopping.controller;

import com.online.shopping.requestdto.HighlightsRequestDto;
import com.online.shopping.responsedto.HighlightsResponseDto;
import com.online.shopping.services.HighlightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/highlights")
public class HighlightsController {

    @Autowired
    private HighlightsService highlightsService;

    @PreAuthorize("hasRole('ROLE_Manager')")
    @GetMapping
    public List<HighlightsResponseDto> getAllHighlights() {
        return highlightsService.getAllHighlights();
    }

    @PreAuthorize("hasRole('ROLE_Seller') or hasRole('ROLE_Customer')")
    @GetMapping("/{highlightId}")
    public HighlightsResponseDto getSingleHighlight(@PathVariable int highlightId) {
        return highlightsService.getSingleHighlights(highlightId);
    }

    @PreAuthorize("hasRole('ROLE_Seller')")
    @PostMapping
    public HighlightsResponseDto addHighlights(@Valid @RequestBody HighlightsRequestDto highlightsRequestDto) {
        return highlightsService.addHighlights(highlightsRequestDto);
    }

    @PreAuthorize("hasRole('ROLE_Seller') or hasRole('ROLE_Manager')")
    @DeleteMapping("/{highlightId}")
    public HighlightsResponseDto removeHighlights(@PathVariable int highlightId) {
        return highlightsService.removeHighlights(highlightId);
    }

}
