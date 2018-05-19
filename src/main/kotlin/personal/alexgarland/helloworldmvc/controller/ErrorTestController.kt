package personal.alexgarland.helloworldmvc.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class ErrorTestController {

    val errorTestPage: ModelAndView
        @RequestMapping("/errorTest")
        get() = ModelAndView("errorTest")

    @RequestMapping("/generateServerError")
    fun generateServerError(): ModelAndView {
        throw RuntimeException("Something bad happened!")
    }

}
