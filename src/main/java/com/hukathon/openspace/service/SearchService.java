package com.hukathon.openspace.service;

import com.hukathon.openspace.dto.PlaceDto;
import com.hukathon.openspace.dto.SearchRequest;
import com.hukathon.openspace.dto.SearchResponse;
import com.hukathon.openspace.entity.Event;
import com.hukathon.openspace.entity.Place;
import com.hukathon.openspace.mapper.EventMapper;
import com.hukathon.openspace.mapper.PlaceMapper;
import com.hukathon.openspace.repository.EventRepository;
import com.hukathon.openspace.repository.EventSpecifications;
import com.hukathon.openspace.repository.PlaceRepository;
import com.hukathon.openspace.repository.PlaceSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    PlaceMapper placeMapper;

    @Autowired
    EventMapper eventMapper;

    public SearchResponse searchEventsAndPlaces(SearchRequest searchRequest) {
    List<Place> foundPlaces = new ArrayList<>();
    List<Event> foundEvents = new ArrayList<>();

    Specification<Event> eventSpecification = EventSpecifications.searchByCriteria(searchRequest);
    Specification<Place> placeSpecification = PlaceSpecifications.searchByCriteria(searchRequest);

    foundPlaces = placeRepository.findAll(placeSpecification);
    var eventsByPlace = foundPlaces.stream().map(Place::getEvents).collect(Collectors.toList());
    foundEvents = eventRepository.findAll(eventSpecification);
    for (List<Event> eventList : eventsByPlace) {
        foundEvents.addAll(eventList);
    }


    return SearchResponse.builder()
            .events(foundEvents.stream().map(eventMapper).collect(Collectors.toList()))
            .places(foundPlaces.stream().map(placeMapper).collect(Collectors.toList()))
            .build();
    }
}
