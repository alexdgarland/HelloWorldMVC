package personal.alexgarland.helloworldmvc.controller;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

public class HelloWorldControllerTest {

	@Test
	public void returnsExpectedMessageModelViewWithGivenName() {
		
		HelloWorldController controller = new HelloWorldController();
		
		ModelAndView result = controller.showMessage("Robert");
		Map<String, Object> model = result.getModel();
		
		assertEquals("helloworld", result.getViewName());
		
		assertEquals(2, model.size());
		assertEquals("Welcome to Spring MVC", model.get("message"));
		assertEquals("Robert", model.get("name"));
		
	}

}
