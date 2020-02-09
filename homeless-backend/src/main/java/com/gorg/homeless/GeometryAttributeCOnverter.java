package com.gorg.homeless;

import lombok.SneakyThrows;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKTReader;
import org.locationtech.jts.io.WKTWriter;

import javax.persistence.AttributeConverter;

public class GeometryAttributeCOnverter implements AttributeConverter<Geometry, String> {

    WKTReader wktReader = new WKTReader();
    WKTWriter wktWriter = new WKTWriter();
    @Override
    public String convertToDatabaseColumn(Geometry attribute) {
        return wktWriter.write(attribute);
    }

    @SneakyThrows
    @Override
    public Geometry convertToEntityAttribute(String dbData) {
        return wktReader.read(dbData);
    }
}
