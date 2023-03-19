package io.jeidiiy.springbootwebservice.web

import io.jeidiiy.springbootwebservice.config.auth.dto.SessionUser
import io.jeidiiy.springbootwebservice.service.posts.PostsService
import io.jeidiiy.springbootwebservice.web.dto.PostsResponseDto
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import javax.servlet.http.HttpSession

@Controller
class IndexController(
    val postsService: PostsService,
    val httpSession: HttpSession
) {
    @GetMapping("/posts/update/{id}")
    fun postsUpdate(@PathVariable id: Long, model: Model): String {
        val dto: PostsResponseDto = postsService.findById(id)
        model.addAttribute("post", dto)
        return "posts-update"
    }

    @GetMapping("/")
    fun index(model: Model): String {
        model.addAttribute("posts", postsService.findAllDesc())
        var user = httpSession.getAttribute("user")

        if (user != null) {
            user = user as SessionUser
            model.addAttribute("userName", user.name)
        }


        return "index"
    }

    @GetMapping("/posts/save")
    fun postsSave() = "posts-save"
}
