package personal.alexgarland.helloworldmvc.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest

@Controller
class ErrorHandlingController {

    @RequestMapping("ohNoSomethingWentWrong")
    fun renderErrorPage(httpRequest: HttpServletRequest): ModelAndView {

        val httpErrorCode = httpRequest.getAttribute(STATUS_ATTRIBUTE) as Int

        val viewName = when (httpErrorCode) {
            500 -> "serverError"
            404 -> "pageNotFound"
            else -> "genericError"
        }

        return ModelAndView(viewName)
    }

    companion object {

        private const val STATUS_ATTRIBUTE: String = "javax.servlet.error.status_code"

    }

}

