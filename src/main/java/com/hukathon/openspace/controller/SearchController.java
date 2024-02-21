package com.hukathon.openspace.controller;

import com.hukathon.openspace.dto.SearchRequest;
import com.hukathon.openspace.dto.SearchResponse;
import com.hukathon.openspace.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {
    private final SearchService searchService;
    @PostMapping("")
    public ResponseEntity<SearchResponse> searchResponse(
            @RequestBody SearchRequest searchRequest)
    {
        return ResponseEntity.ok(searchService.searchEventsAndPlaces(searchRequest));
    }
}
