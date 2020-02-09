package com.gorg.homeless;

import mil.nga.sf.geojson.Feature;
import mil.nga.sf.geojson.FeatureCollection;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PlacesController {

    private final PlacesService placesService;

    public PlacesController(PlacesService placesService) {
        this.placesService = placesService;
    }

    @GetMapping("/types")
    public Iterable<TypeOfPlace> typesOfPlaces(){
        return placesService.getTypeOfPlaces();
    }

    @GetMapping("/places")
    public FeatureCollection getPlaces() {
        Iterable<Place> allPlaces = placesService.getAllPlaces();
        List<Feature> features = StreamSupport.stream(allPlaces.spliterator(), false)
                .map(place -> place.getFeature())
                .collect(Collectors.toList());
        return new FeatureCollection(features);
    }

}
