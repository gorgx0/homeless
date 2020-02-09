package com.gorg.homeless;

import mil.nga.sf.geojson.FeatureCollection;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PlacesController {

    private final PlacesService placesService;

    public PlacesController(PlacesService placesService) {
        this.placesService = placesService;
    }

    @GetMapping("/places")
    public FeatureCollection places() {
        return placesService.getPlaces();
    }

    @GetMapping("/types")
    public List<TypeOfPlace> typesOfPlaces(){
        return placesService.getTypeOfPlaces();
    }

}
