package com.inmar.api.config;

import org.springframework.stereotype.Component;

@Component
public class Mappings {

	public static final String GET_LOCATIONS = "/location";
	public static final String SAVE_LOCATION = "/location";

	public static final String GET_DEPARTMENTS = "/location/{locationId}/department";
	public static final String SAVE_DEPARTMENT = "/location/{locationId}/department";

	public static final String GET_CATEGORIES = "/location/{locationId}/department/{departmentId}/category";
	public static final String SAVE_CATEGORY = "/location/{locationId}/department/{departmentId}/category";

	public static final String GET_SUB_CATEGORIES = "/location/{locationId}/department/{departmentId}/category/{categoryId}/sub-category";
	public static final String SAVE_SUB_CATEGORY = "/location/{locationId}/department/{departmentId}/category/{categoryId}/sub-category";

	public static final String GET_PRODUCTS = "/products";

}
