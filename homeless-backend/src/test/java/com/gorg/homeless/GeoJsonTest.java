package com.gorg.homeless;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import mil.nga.sf.geojson.Feature;
import mil.nga.sf.geojson.Point;
import mil.nga.sf.geojson.Position;
import mil.nga.sf.geojson.SimpleFeature;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class GeoJsonTest {

    @Test
    void geoJsonSerialization() throws JsonProcessingException {
        Position position = new Position(21.0099102, 52.2471075);

        Point point = new Point(position);
        Map<String, Object> properties = new HashMap<>();
        properties.put("xxxxx", "eeeeee");

        Feature feature = new Feature(point);

        feature.setProperties(properties);
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(feature);
        System.out.println(s);
    }


}
