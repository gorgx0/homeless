package com.gorg.homeless;

import mil.nga.sf.geojson.Feature;
import mil.nga.sf.geojson.FeatureCollection;
import mil.nga.sf.geojson.Point;
import mil.nga.sf.geojson.Position;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Transient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
                .map(PlacesController::getFeature)
                .collect(Collectors.toList());
        return new FeatureCollection(features);
    }

    private static Feature getFeature(Place place) {
        Feature feature = new Feature();
        Map<String, Object> properties = new HashMap<>();
        properties.put("id", place.getId());
        properties.put("name", place.getName());
        properties.put("description", place.getDescription());
        properties.put("type", place.getTypeShortname());
        feature.setProperties(properties);
        if(place.getLocation()!=null) {
            feature.setGeometry(new Point(new Position(place.getLocation().getCoordinates()[0].x, place.getLocation().getCoordinates()[0].getY())));
        }
        return feature;
    }

}
