package io.jeidiiy.springbootwebservice.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class IndexController {
    @GetMapping("/")
    fun index() = "index"

    @GetMapping("/posts/save")
    fun postsSave() = "posts-save"
}
