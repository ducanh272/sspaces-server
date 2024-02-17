package com.hukathon.openspace.controller;

import com.hukathon.openspace.dto.PlaceDto;
import com.hukathon.openspace.entity.Place;
import com.hukathon.openspace.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/places")
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("")
    public ResponseEntity<List<PlaceDto>> getAllPlaces() {
        return ResponseEntity.ok(placeService.getAllPlaces());
    }

    @GetMapping("/{placeId}")
    public ResponseEntity<PlaceDto> getPlaceById(@PathVariable Integer placeId) {
        return ResponseEntity.ok(placeService.getPlaceById(placeId));
    }

}
