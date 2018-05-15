package personal.alexgarland.helloworldmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorTestController {

  @RequestMapping("/errorTest")
  public ModelAndView getErrorTestPage() {
    return new ModelAndView("errorTest");
  }

  @RequestMapping("/generateServerError")
  public ModelAndView generateServerError() {
    throw new RuntimeException("Something bad happened!");
  }

}
