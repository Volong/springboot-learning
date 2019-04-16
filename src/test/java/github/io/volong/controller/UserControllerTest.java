package github.io.volong.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


/**
 * @time 2019-04-16
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController()).build();

    }

    @Test
    public void testGetUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user")
               .param("name", "yhl")
               .accept(MediaType.APPLICATION_JSON_UTF8))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.content().string("yhl"))
               .andReturn();

    }
}
