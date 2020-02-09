package com.gorg.homeless;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import mil.nga.sf.geojson.Feature;
import mil.nga.sf.geojson.Point;
import mil.nga.sf.geojson.Position;
import org.hibernate.annotations.Type;
import org.locationtech.jts.geom.Geometry;
import org.n52.jackson.datatype.jts.GeometryDeserializer;
import org.n52.jackson.datatype.jts.GeometrySerializer;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity(name = "places")
@Data
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @JsonSerialize(using = GeometrySerializer.class)
    @JsonDeserialize(contentUsing = GeometryDeserializer.class)
    @Column(columnDefinition = "GEOMETRY")
    private Geometry location;
    private String typeShortname;


    public Feature getFeature() {
        Feature feature = new Feature();
        Map<String, Object> properties = new HashMap<>();
        properties.put("id", id);
        properties.put("name", name);
        properties.put("description", description);
        properties.put("type", typeShortname);
        feature.setProperties(properties);
        feature.setGeometry(new Point(new Position(location.getCoordinates()[0].x,location.getCoordinates()[0].getY())));
        return feature;
    }
}
