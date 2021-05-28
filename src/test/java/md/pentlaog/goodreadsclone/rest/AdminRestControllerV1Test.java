package md.pentlaog.goodreadsclone.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AdminRestControllerV1Test {

    @Autowired
    private MockMvc mock;

    @Autowired
    AdminRestControllerV1 adminRestControllerV1;

    @Test
    void getUserById() {
        assertThat(adminRestControllerV1).isNotNull();
    }

//    @Test
//    @WithMockUser(username = "test123", roles = {"ADMIN"})
//    void shouldReturnAUser() throws Exception {
//        this.mock.perform(get("/api/v1/admin/users/3"))
//                .andDo(print())
//                .andExpect(status().isOk());
//
//    }
}