package github.io.volong;

import github.io.volong.web.WebController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
public class WebControllerTest {

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new WebController()).build();
    }

    @Test
    public void testGetUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/getUser")).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testGetUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/getUsers")).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/get/xxx")).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void saveUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/saveUser")
                .param("name","")
                .param("age","666")
                .param("pass","test")
        ).andDo(MockMvcResultHandlers.print());
    }

}
