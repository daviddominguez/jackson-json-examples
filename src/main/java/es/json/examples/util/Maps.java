package es.json.examples.util;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Predicate;

import static java.lang.String.format;
import static java.util.Optional.of;

public class Maps {

	private static final String PROPERTY_MAP_DELIMITER = ".";

	public static void flatten(SortedMap<String, Object> map, String property, Object value) {
		if (value instanceof Map) {
			Map<?, ?> values = (Map) value;
			for (Map.Entry<?, ?> entry : values.entrySet()) {
				flatten(map, format("%s.%s", property, entry.getKey()), entry.getValue());
			}
		}
		else if (value instanceof String) {
			map.put(property, value);
		}
		else {
			// Arrays not supported (yet)
			throw new IllegalArgumentException();
		}
	}

	public static Map<String, Object> flatten(SortedMap<String, Object> map) {
		SortedMap<String, Object> flattenedMap = new TreeMap<>();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			flatten(flattenedMap, entry.getKey(), entry.getValue());
		}
		return flattenedMap;
	}

	public static Map<String, Object> unflatten(String path, SortedMap<String, Object> subMap) {
		Map<String, Object> map = new HashMap<>();
		for (String entryKey : subMap.keySet()) {
			String property = getNextPropertyToken(path, entryKey);
			if (isANestedMap(subMap, property)) {
				String newPath = format("%s%s%s", path, property, PROPERTY_MAP_DELIMITER);
				map.put(property, unflatten(newPath, getSubMapWithAllKeysPrefixedBy(newPath, subMap)));
			}
			else {
				map.put(property, subMap.get(entryKey));
			}
		}
		return map;
	}

	private static String getNextPropertyToken(String currentPathKey, String currentMapKey) {
		Predicate<Integer> notFound = (i -> i >= 0);
		int startingPosition = currentPathKey.length();
		int endPosition = of(currentMapKey.indexOf(PROPERTY_MAP_DELIMITER, startingPosition))
				.filter(notFound)
				.orElse(currentMapKey.length());
		return currentMapKey.substring(currentPathKey.length(), endPosition);
	}

	private static SortedMap<String, Object> getSubMapWithAllKeysPrefixedBy(String prefix, SortedMap<String, Object> map) {
		return map.subMap(prefix, prefix + Character.MAX_VALUE);
	}

	private static boolean isANestedMap(SortedMap<String, Object> map, String property) {
		for (String entryKey : map.keySet()) {
			if (entryKey.endsWith(property))
				return false;
		}
		return true;
	}


}
