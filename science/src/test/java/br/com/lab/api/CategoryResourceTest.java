package br.com.lab.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.lab.constant.AttributeType;
import br.com.lab.model.AttributeTransfer;
import br.com.lab.model.CategoryTransfer;
import br.com.lab.service.CategoryService;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoryResource.class)
class CategoryResourceTest {

    @Autowired
    private MockMvc mvc;

	@MockBean
	private CategoryService categoryService;

	@Test
	public void givenCategoriesAreReturned_whenGETCategories_thenResultsShouldBeProvided() throws Exception {
		given(categoryService.findAll()).willReturn(createCategoriesList());
		
		this.mvc.perform(get("/categories/"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value("1"))
				.andExpect(jsonPath("$[0].name").value("name"))
				.andExpect(jsonPath("$[0].attributes").exists())
				.andExpect(jsonPath("$[0].attributes[0].id").value(10))
				.andExpect(jsonPath("$[0].attributes[0].type").value("INTEGER"))
				.andExpect(jsonPath("$[0].attributes[0].name").value("attr"))
				.andExpect(jsonPath("$[0].attributes[0].nullable").value(false))
				.andReturn();
	}

	private List<CategoryTransfer> createCategoriesList() {
		List<CategoryTransfer> categories = new ArrayList<>();
		CategoryTransfer first = CategoryTransfer.builder().id(1L).name("name").build();
		List<AttributeTransfer> attributes = new ArrayList<>();
		AttributeTransfer attribute = AttributeTransfer.builder().id(10L).name("attr").nullable(false).type(AttributeType.INTEGER).build();
		attributes.add(attribute );
		first.setAttributes(attributes);
		categories.add(first);
		return categories;
	}
}
