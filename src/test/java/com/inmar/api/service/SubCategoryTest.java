package com.inmar.api.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.inmar.api.dao.CategoryDao;
import com.inmar.api.dao.SubCategoryDao;
import com.inmar.api.model.Category;
import com.inmar.api.model.SubCategory;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:**/WebContent/WEB-INF/inmar-services.xml")
@Transactional
public class SubCategoryTest {

	@Configuration
	static class SubCategoryTestContextConfiguration {
		@Bean
		public SubCategoryDao subCategoryDao() {
			return Mockito.mock(SubCategoryDao.class);
		}

		@Bean
		public CategoryDao categoryDao() {
			return Mockito.mock(CategoryDao.class);
		}

		@Bean
		public SubCategoryService subCategoryService() {
			return new SubCategoryServiceImpl();
		}
	}

	@Autowired
	SubCategoryService subCategoryService;
	@Autowired
	SubCategoryDao subCategoryDao;
	@Autowired
	CategoryDao categoryDao;

	@Before
	public void setup() {

		Category category = new Category();
		category.setId(1);
		category.setName("category");

		SubCategory subCategory = new SubCategory();
		subCategory.setId(1);
		subCategory.setName("subCategory");

		Mockito.when(categoryDao.findById(1)).thenReturn(category);
		Mockito.when(subCategoryDao.getSubCategories(category)).thenReturn(getSubCategories());
		Mockito.when(subCategoryDao.findById(1)).thenReturn(subCategory);

		Mockito.doAnswer(new Answer<Void>() {

			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				return null;
			}
		}).when(subCategoryDao).saveOrUpdate(Mockito.any(SubCategory.class));
	}

	private List<SubCategory> getSubCategories() {
		List<SubCategory> subCategories = new ArrayList<SubCategory>();
		for (int i = 0; i < 5; i++) {
			SubCategory subCategory = new SubCategory();
			subCategory.setName("subCategory" + i);
			subCategory.setId(i + 1);
			subCategories.add(subCategory);
		}
		return subCategories;
	}

	@Test
	public void testGetSubCategories() {
		List<SubCategory> subCategories = subCategoryService.getSubCategories(1, 1, 1);
		int i = 0;
		for (SubCategory subCategory : getSubCategories()) {
			assertEquals(subCategory.getId(), subCategories.get(i).getId());
			i++;
		}
	}

	@Test
	public void saveSubCategory() {
		Map<String, Object> payload = new HashMap<String, Object>();
		payload.put("name", "subCategory");
		SubCategory subCategory = subCategoryService.saveSubCategory(1, 1, 1, payload);

		assertEquals(subCategory.getName(), "subCategory");
	}

	@Test
	public void updateSubCategory() {
		Map<String, Object> payload = new HashMap<String, Object>();
		payload.put("name", "subCategory2");
		payload.put("id", 1);
		SubCategory subCategory = subCategoryService.saveSubCategory(1, 1, 1, payload);

		assertEquals(subCategory.getName(), "subCategory2");
	}
}
