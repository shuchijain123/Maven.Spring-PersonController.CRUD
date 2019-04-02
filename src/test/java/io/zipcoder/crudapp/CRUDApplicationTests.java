package io.zipcoder.crudapp;

import io.zipcoder.crudapp.controllers.PersonController;
import io.zipcoder.crudapp.models.Person;
import io.zipcoder.crudapp.repositories.PersonRepository;
import org.apache.coyote.Response;
import org.junit.Test;
import org.junit.runner.Request;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)

public class CRUDApplicationTests {

@Autowired
private MockMvc mockMvc;

	@MockBean
	private PersonRepository repository;




	//String expected = "{\"firstName\": \"Shuchi\", \"lastName\": \"Jain\"}";


	@Test


	public void testPersonObject() throws Exception{
		Long givenId = 1L;

		Person person = new Person(1L,"Shuchi","Jain");

		String expected = "{\"id\":1,\"firstName\":\"Shuchi\",\"lastName\":\"Jain\"}";

		 Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(person));


		this.mockMvc.perform(MockMvcRequestBuilders
				.get("/people/" +givenId))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(expected));
	}







	@Test
	public void contextLoads() {
	}

}
