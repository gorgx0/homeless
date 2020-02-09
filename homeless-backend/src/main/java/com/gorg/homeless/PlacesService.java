package com.gorg.homeless;

import mil.nga.sf.geojson.Feature;
import mil.nga.sf.geojson.FeatureCollection;
import mil.nga.sf.geojson.Point;
import mil.nga.sf.geojson.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class PlacesService {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    private PlacesRestRepository placesRestRepository;


    public PlacesService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public FeatureCollection getPlaces() {

        List<Feature> query = jdbcTemplate.query(
        "select ID, NAME, DESCRIPTION, TYPE_SHORTNAME, LOCATION FROM PLACES;",
            (rs, rowNum) -> {
                Feature feature = new Feature();
                Map<String, Object> properties = new HashMap<>();
                properties.put("id",rs.getString("ID"));
                properties.put("name",rs.getString("NAME"));
                properties.put("description",rs.getString("DESCRIPTION"));
                properties.put("type",rs.getString("TYPE_SHORTNAME"));
                org.locationtech.jts.geom.Point location = (org.locationtech.jts.geom.Point) rs.getObject("LOCATION");
                feature.setProperties(properties);
                feature.setGeometry(new

                Point(new Position(location.getX(),location.getY())));
            return feature;
            });

        FeatureCollection featureCollection = new FeatureCollection();
        featureCollection.addFeatures(query);

        return featureCollection;
    }

    public List<TypeOfPlace> getTypeOfPlaces() {
        return jdbcTemplate.query(
                "select ID, ICON, NAME, SHORTNAME, ORDERING FROM TYPES order by ORDERING",
                ((rs, rowNum) -> {
                    return new TypeOfPlace(
                            rs.getLong("id"),
                            rs.getString("icon"),
                            rs.getString("name"),
                            rs.getString("SHORTNAME"),
                            rs.getInt("ordering")
                    );
                })
        );
    }

//    public TypeOfPlace addNewPlace() {
//        jdbcTemplate.query("insert into L")
//    }
}
