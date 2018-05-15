package personal.alexgarland.helloworldmvc.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import javax.servlet.http.HttpServletRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

public class ErrorHandlingControllerTest {

  private ErrorHandlingController testController = new ErrorHandlingController();

  @Mock
  private HttpServletRequest httpRequest;

  @Before
  public void initMocks() {
    MockitoAnnotations.initMocks(this);
  }

  private void setMockResponseCode(int responseCode) {
    when(httpRequest.getAttribute("javax.servlet.error.status_code"))
        .thenReturn(new Integer(responseCode));
  }

  private void returnsCorrectViewForError(int responseCode, String expectedViewName) {
    setMockResponseCode(responseCode);
    ModelAndView result = testController.renderErrorPage(httpRequest);
    assertEquals(expectedViewName, result.getViewName());
    assertEquals(0, result.getModel().size());
  }

  @Test
  public void handles404Error() {
    returnsCorrectViewForError(404, "pageNotFound");
  }

  @Test
  public void handles500Error() {
    returnsCorrectViewForError(500, "serverError");
  }

  @Test
  public void handlesOtherError() {
    returnsCorrectViewForError(304, "genericError");
  }

}
