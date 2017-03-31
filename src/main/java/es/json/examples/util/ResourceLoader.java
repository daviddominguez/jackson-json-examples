package es.json.examples.util;

import java.io.IOException;
import java.net.URISyntaxException;

import static com.google.common.io.Resources.getResource;
import static java.nio.file.Files.lines;
import static java.nio.file.Paths.get;

public class ResourceLoader {
	public static String loadResourceInOneLine (String fileName) throws IOException, URISyntaxException {
		return lines(get(getResource(fileName).toURI())).map(s-> s.replaceAll("\\s", "")).reduce("", (a, b) -> (a + b));
	}
}
