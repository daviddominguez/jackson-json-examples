package es.json.examples;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import es.json.examples.model.Album;
import es.json.examples.model.Artist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Java object serialization to JSON using Jackson ObjectMapper.
 * Example extracted from http://www.studytrails.com/java/json/jackson-create-json.jsp
 */
public class BasicJavaObjectJsonSerializationExample {

	private static final Logger LOGGER = LoggerFactory.getLogger(BasicJavaObjectJsonSerializationExample.class);

	public static void main(String[] args) throws ParseException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();

		// Indent output for easy reading, only for testing purposes, not suitable for production.
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);

		// Order map entries alphabetically by key value.
		mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);

		// Use a date format for dates instead epoch style.
		mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));

		// Dont include properties with null or empty values.
		mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

		// Override key naming strategy without using annotations.
		mapper.setPropertyNamingStrategy(new PropertyNamingStrategy() {
			@Override
			public String nameForField(MapperConfig<?> config, AnnotatedField field, String defaultName) {
				if (field.getFullName().equals(Artist.class.getName() + "#name"))
					return "Artist-name";
				return super.nameForField(config, field, defaultName);
			}

			@Override
			public String nameForGetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
				if (method.getAnnotated().getDeclaringClass().equals(Album.class) && defaultName.equals("title"))
					return "Album-title";
				return super.nameForGetterMethod(config, method, defaultName);
			}
		});

		Album album = new Album("Kind Of Blue");
		album.setLinks(new String[]{"link1", "link2"});
		album.setSongs(Arrays.asList("So What", "Flamenco Sketches", "Freddie Freeloader"));

		Artist artist = new Artist();
        artist.name = "Miles Davis";
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        artist.birthDate = format.parse("26-05-1926");
        album.setArtist(artist);
		album.addMusician("Miles Davis", "Trumpet, Band leader");
		album.addMusician("Julian Adderley", "Alto Saxophone");
		album.addMusician("Paul Chambers", "double bass");

		LOGGER.debug(mapper.writeValueAsString(album));
	}

}
