package personal.alexgarland.helloworldmvc.controller

import org.junit.Assert.assertEquals
import org.junit.Test

class HelloWorldControllerTest {

    @Test
    fun returnsExpectedMessageModelViewWithGivenName() {

        val controller = HelloWorldController()

        val result = controller.showMessage("Robert")

        assertEquals("helloworld", result.viewName)

        val model = result.model

        assertEquals(2, model.size.toLong())
        assertEquals("Welcome to Spring MVC", model["message"])
        assertEquals("Robert", model["name"])

    }

}
