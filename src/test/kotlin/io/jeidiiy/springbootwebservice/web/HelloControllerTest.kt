package io.jeidiiy.springbootwebservice.web

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [HelloController::class])
class HelloControllerTest(
    @Autowired val mvc: MockMvc
) {
    @Test
    fun hello가_리턴된다() {
        val hello = "hello"

        mvc.perform(get("/hello"))
            .andExpect(status().isOk)
            .andExpect(content().string(hello))
    }
}
