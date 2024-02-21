package com.hukathon.openspace.repository;

import com.hukathon.openspace.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Integer>, JpaSpecificationExecutor<Place> {
}
