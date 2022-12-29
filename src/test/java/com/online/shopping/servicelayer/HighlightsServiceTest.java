package com.online.shopping.servicelayer;

import com.online.shopping.requestdto.HighlightsRequestDto;
import com.online.shopping.responsedto.HighlightsResponseDto;
import com.online.shopping.services.HighlightsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class HighlightsServiceTest {

    @Autowired
    private HighlightsService highlightsService;

    @Test
    public void test_addHighlights() {
        HighlightsRequestDto highlightsRequest = new HighlightsRequestDto("82H801L7IN | 82H802FJIN | 82H802L3IN | 82H801LHIN", "intel processor/ 8 GB/ 512 GB SSD/ Windows 11 Home/ With MS Office/ Battery Backup Upto 8 Hrs", "362.2 x 253.4 x 19.9 mm | Weight 1.7 Kg | Screen Size 39.62 cm (15.6 Inch)");
        HighlightsResponseDto highlightsResponse = highlightsService.addHighlights(highlightsRequest);
        assertEquals(highlightsRequest.getModelNumber(), highlightsResponse.getModelNumber());
        assertEquals(highlightsRequest.getFeatures(), highlightsResponse.getFeatures());
        assertEquals(highlightsRequest.getSize(), highlightsResponse.getSize());
    }

    @Test
    public void test_getAllHighlights() {
        List<HighlightsResponseDto> highlights = highlightsService.getAllHighlights();
        assertThat(highlights.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void test_getSingleHighlights() {
        int highlightId = 1;
        HighlightsResponseDto highlightsResponse = highlightsService.getSingleHighlights(highlightId);
        assertEquals(highlightId, highlightsResponse.getId());
    }

    @Test
    public void test_removeHighlights() {
        int highlightId = 3;
        HighlightsResponseDto highlights = highlightsService.removeHighlights(highlightId);
        assertThat(highlights).isNotNull();
    }

}
