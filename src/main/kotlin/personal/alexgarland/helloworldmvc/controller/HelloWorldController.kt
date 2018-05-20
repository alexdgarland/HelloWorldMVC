package personal.alexgarland.helloworldmvc.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView


@Controller
class HelloWorldController {

    @RequestMapping("/hello")
    fun showMessage(@RequestParam(value = "name", required = false, defaultValue = "World") name: String): ModelAndView {

        return ModelAndView("helloworld")
                .withObject("message", message)
                .withObject("name", name)

    }

    companion object {
        private const val message = "Welcome to Spring MVC"
    }

}


