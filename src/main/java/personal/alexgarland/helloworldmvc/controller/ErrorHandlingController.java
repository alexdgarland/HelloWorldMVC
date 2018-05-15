package personal.alexgarland.helloworldmvc.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorHandlingController {

  @RequestMapping("ohNoSomethingWentWrong")
  public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {

    int httpErrorCode = (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");

    switch (httpErrorCode) {
      case 500: {
        return new ModelAndView("serverError");
      }
      case 404: {
        return new ModelAndView("pageNotFound");
      }
    }

    return new ModelAndView("genericError");

  }

}
