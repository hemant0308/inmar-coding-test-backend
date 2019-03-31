package com.inmar.api.helper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utilities {

	public static Map<String, Object> getMapFromString(String rawString)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> result = new HashMap<String, Object>();
		if (rawString != null) {
			result = objectMapper.readValue(rawString, HashMap.class);
		}
		return result;
	}

	public static String getStringFromMap(Map<String, Object> map)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(map);
	}
}
