package io.jeidiiy.springbootwebservice.web

import io.jeidiiy.springbootwebservice.service.posts.PostsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class IndexController(
    @Autowired val postsService: PostsService
) {
    @GetMapping("/")
    fun index(model: Model): String {
        model.addAttribute("posts", postsService.findAllDesc())
        return "index"
    }

    @GetMapping("/posts/save")
    fun postsSave() = "posts-save"
}
