package com.newsagency.business.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.newsagency.data.entity.Article;

@Component
public class JsonUtil {
	private static ObjectMapper mapper;
	static {
		mapper = new ObjectMapper();
	}

	public static String convertJavaToJson(Object object) {
		String jsonResult = "";
		try {
			jsonResult = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			System.out.println("Exception occured while converting Java object to JSON --> " + e.getMessage());
			e.printStackTrace();
		}
		return jsonResult;
	}

	public static <T> T convertJsonToJava(String jsonString, Class<T> cls) {
		T result = null;
		try {
			result = mapper.readValue(jsonString, cls);
		} catch (IOException e) {
			System.out.println("Exception occured while converting JSON to Java object --> " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	public static String convertListToJson(List<Article> list) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if (list == null) {
			return null;
		}
		return mapper.writeValueAsString(list);
	}

	public static List<Article> convertJsonToList(String jsonString)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		CollectionType javaType = mapper.getTypeFactory().constructCollectionType(List.class, Article.class);
		List<Article> articles = mapper.readValue(jsonString, javaType);
		return articles;
	}

}
