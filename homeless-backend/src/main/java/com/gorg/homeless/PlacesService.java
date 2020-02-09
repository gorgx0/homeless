package com.gorg.homeless;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlacesService {

    @Autowired
    private PlacesRestRepository placesRestRepository;

    @Autowired
    private TypesRestRepository typesRestRepository;

    public Iterable<TypeOfPlace> getTypeOfPlaces() {
        return typesRestRepository.findAll();
    }


    public Iterable<Place> getAllPlaces() {
        return placesRestRepository.findAll();
    }

}
