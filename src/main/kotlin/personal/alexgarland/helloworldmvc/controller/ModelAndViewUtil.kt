package personal.alexgarland.helloworldmvc.controller

import org.springframework.web.servlet.ModelAndView


fun ModelAndView.withObject(attributeName: String, attributeValue: Any): ModelAndView {
    addObject(attributeName, attributeValue)
    return this
}
