package com.inmar.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.inmar.api.config.Mappings;
import com.inmar.api.helper.Utilities;
import com.inmar.api.model.SubCategory;
import com.inmar.api.service.AuthenticationService;
import com.inmar.api.service.AuthenticationServiceImpl;
import com.inmar.api.service.SubCategoryService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:**/WebContent/WEB-INF/inmar-services.xml")
@AutoConfigureMockMvc
public class SubCategoryControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Configuration
	static class SubCategoryTestContextConfiguration {
		@Bean
		public SubCategoryService subCategoryDao() {
			return Mockito.mock(SubCategoryService.class);
		}

		@Bean
		public AuthenticationService authenticationService() {
			return new AuthenticationServiceImpl();
		}
	}

	@Autowired
	SubCategoryService subCategoryService;
	@Autowired
	AuthenticationService authenticationService;

	public List<SubCategory> getAllSubCategories() {
		List<SubCategory> subCategories = new ArrayList<SubCategory>();
		SubCategory subCategory = new SubCategory();
		subCategory.setId(1);
		subCategory.setName("subCategory");
		subCategories.add(subCategory);
		return subCategories;
	}

	@Before
	public void setup() {
		Mockito.when(subCategoryService.getAllSubCategories()).thenReturn(getAllSubCategories());
	}

	@Test
	public void testGetSubCategories() throws Exception {

		Map<String, Object> response = new HashMap<String, Object>();

		response.put("subCategories", getAllSubCategories());

		String expectedContent = Utilities.getStringFromMap(response);

		String token = authenticationService.generateToken(1);

		this.mockMvc.perform(get(Mappings.GET_ALL_SUB_CATEGORIES).param("access-token", token)).andDo(print())
				.andExpect(status().isOk()).andExpect(content().string(expectedContent));
	}
}
