package es.json.examples;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

/**
 * Java object serialization to JSON using a tree model approach.
 * Example extracted from http://www.studytrails.com/java/json/jackson-create-json.jsp
 */
public class BasicTreeModelSerialization {

	public static void main(String[] args) throws IOException, IOException {
		// Create the node factory that gives us node instances.
		JsonNodeFactory factory = new JsonNodeFactory(false);

		// JsonFactory allows us to build a json reader (JsonParser) or writer (JsonGenerator).
		JsonFactory jsonFactory = new JsonFactory();

		// Create a json factory to write the treenode as json. For the example we just write to console.
		JsonGenerator generator = jsonFactory.createGenerator(System.out);
		ObjectMapper mapper = new ObjectMapper();

		// The root node (album).
		ObjectNode album = factory.objectNode();
		album.put("Album-Title", "Kind Of Blue");

		// We now add the links array.
		ArrayNode links = factory.arrayNode();
		links.add("link1").add("link2");
		album.set("links", links);

		// Next we add the artist object. Note that artist by itself is a JsonObject. we add that to the album.
		ObjectNode artist = factory.objectNode();
		artist.put("Artist-Name", "Miles Davis");
		artist.put("birthDate", "26 May 1926");
		album.set("artist", artist);

		// Next we add the musicians. They aren't in an array, its a subdocument, therefore we will use an ObjectNode
		ObjectNode musicians = factory.objectNode();
		musicians.put("Julian Adderley", "Alto Saxophone");
		musicians.put("Miles Davis", "Trumpet, Band leader");
		album.set("musicians", musicians);

		mapper.writeTree(generator, album);
	}
}
