package personal.alexgarland.helloworldmvc.controller

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import javax.servlet.http.HttpServletRequest

class ErrorHandlingControllerTest {

    private val testController = ErrorHandlingController()

    @Mock
    private val httpRequest: HttpServletRequest? = null

    @Before
    fun initMocks() {
        MockitoAnnotations.initMocks(this)
    }

    private fun setMockResponseCode(responseCode: Int) {
        `when`(httpRequest!!.getAttribute("javax.servlet.error.status_code"))
                .thenReturn(responseCode)
    }

    private fun returnsCorrectViewForError(responseCode: Int, expectedViewName: String) {
        setMockResponseCode(responseCode)
        val result = testController.renderErrorPage(httpRequest!!)
        assertEquals(expectedViewName, result.viewName)
        assertEquals(0, result.model.size.toLong())
    }

    @Test
    fun handles404Error() {
        returnsCorrectViewForError(404, "pageNotFound")
    }

    @Test
    fun handles500Error() {
        returnsCorrectViewForError(500, "serverError")
    }

    @Test
    fun handlesOtherError() {
        returnsCorrectViewForError(304, "genericError")
    }

}
