package personal.alexgarland.helloworldmvc.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

public class ErrorTestControllerTest {

	private ErrorTestController testController = new ErrorTestController();
	
	@Test
	public void getsErrorTestPage() {
		ModelAndView result = testController.getErrorTestPage();
		assertEquals("errorTest", result.getViewName());
		assertEquals(0, result.getModel().size());
	}
	
	@Test(expected = RuntimeException.class)
	public void throwsServerError() {
		testController.generateServerError();
	}

}
