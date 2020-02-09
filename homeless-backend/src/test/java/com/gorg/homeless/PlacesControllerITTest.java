package com.gorg.homeless;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PlacesControllerITTest {



    @Autowired
    private PlacesRestRepository placesRestRepository;


    @Test
    void wktFormatTest() throws ParseException {

        WKTReader wktReader = new WKTReader();
        Geometry geometry = wktReader.read("POINT (21 52)");
        assertThat(geometry).isNotNull();
        String geometryType = geometry.getGeometryType();
        assertThat(geometryType).isEqualToIgnoringCase("point");
    }

    @Test
    void testInsertGeomentry() throws JsonProcessingException, ParseException {


        Place place = new Place();
        place.setTypeShortname("MEDA");
        WKTReader wktReader = new WKTReader();
        Geometry geometry = wktReader.read("POINT (21 52)");
        place.setLocation(geometry);
        placesRestRepository.deleteAll();
        Place save = placesRestRepository.save(place);
        assertThat(save).isNotNull();
        Iterable<Place> all = placesRestRepository.findAll();
        assertThat(all).hasSize(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String string = objectMapper.writeValueAsString(save);
        assertThat(string).isNotBlank();
    }
}
