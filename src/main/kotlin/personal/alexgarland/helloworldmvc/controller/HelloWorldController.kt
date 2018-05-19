package personal.alexgarland.helloworldmvc.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

@Controller
class HelloWorldController {

    private val MESSAGE = "Welcome to Spring MVC"

    @RequestMapping("/hello")
    fun showMessage(
            @RequestParam(value = "name", required = false, defaultValue = "World") name: String): ModelAndView {
        val mv = ModelAndView("helloworld")
        mv.addObject("message", MESSAGE)
        mv.addObject("name", name)
        return mv
    }

}
