package personal.alexgarland.helloworldmvc.controller

import org.junit.Assert.assertEquals
import org.junit.Test
import org.springframework.web.servlet.ModelAndView

class ErrorTestControllerTest {

    private val testController = ErrorTestController()

    @Test
    fun getsErrorTestPage() {
        val result = testController.errorTestPage
        assertEquals("errorTest", result.viewName)
        assertEquals(0, result.model.size.toLong())
    }

    @Test(expected = RuntimeException::class)
    fun throwsServerError() {
        testController.generateServerError()
    }

}
