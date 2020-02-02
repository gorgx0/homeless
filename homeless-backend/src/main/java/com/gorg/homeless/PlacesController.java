package com.gorg.homeless;

import mil.nga.sf.geojson.Feature;
import mil.nga.sf.geojson.FeatureCollection;
import mil.nga.sf.geojson.Point;
import mil.nga.sf.geojson.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PlacesController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/places")
    public FeatureCollection places() {

        Position position = new Position(21.0099102, 52.2471075);

        Point point = new Point(position);
        Map<String, Object> properties = new HashMap<>();
        properties.put("xxxxx", "eeeeee");

        Feature feature = new Feature(point);

        feature.setProperties(properties);

        FeatureCollection featureCollection = new FeatureCollection();

        featureCollection.addFeature(feature);

        return featureCollection;
    }
}
