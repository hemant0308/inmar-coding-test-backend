package com.inmar.api.config;

import org.springframework.stereotype.Component;

@Component
public class Mappings {

	public static final String GET_LOCATIONS = "/location";
	public static final String SAVE_LOCATION = "/location";
	public static final String DELETE_LOCATION = "/location/{locationId}/delete";

	public static final String GET_DEPARTMENTS = "/location/{locationId}/department";
	public static final String SAVE_DEPARTMENT = "/location/{locationId}/department";
	public static final String GET_ALL_DEPARTMENTS = "/department";
	public static final String DELETE_DEPARTMENT = "/location/{locationId}/department/{departmentId}/delete";

	public static final String GET_CATEGORIES = "/location/{locationId}/department/{departmentId}/category";
	public static final String SAVE_CATEGORY = "/location/{locationId}/department/{departmentId}/category";
	public static final String GET_ALL_CATEGORIES = "/category";
	public static final String DELETE_CATEGORY = "/location/{locationId}/department/{departmentId}/category/{categoryId}/delete";

	public static final String GET_SUB_CATEGORIES = "/location/{locationId}/department/{departmentId}/category/{categoryId}/sub-category";
	public static final String SAVE_SUB_CATEGORY = "/location/{locationId}/department/{departmentId}/category/{categoryId}/sub-category";
	public static final String GET_ALL_SUB_CATEGORIES = "/sub-category";
	public static final String DELETE_SUB_CATEGORY = "/location/{locationId}/department/{departmentId}/category/{categoryId}/sub-category/{subCategoryId}/delete";

	public static final String GET_ALL_PRODUCTS = "/products";
	public static final String SAVE_PRODUCT = "/products";
	public static final String DELETE_PRODUCT = "/products/{productId}/delete";
	public static final String GET_META_DATA = "/meta-data";

	public static final String LOGIN_USER = "/login";
	public static final String REGISTER_USER = "/register";

}
