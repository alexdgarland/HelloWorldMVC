package personal.alexgarland.helloworldmvc.service.repository.inmemory

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import personal.alexgarland.helloworldmvc.service.repository.inmemory.IdSequence

class IdSequenceTest {

    @Test
    fun worksWithDefaultSeed() {

        val sequence = IdSequence()

        assertThat(sequence.getNextId(), `is`(1))

        assertThat(sequence.getNextId(), `is`(2))

        assertThat(sequence.getNextId(), `is`(3))

    }

    @Test
    fun worksWithNonDefaultSeed() {

        val sequence = IdSequence(4)

        assertThat(sequence.getNextId(), `is`(4))

        assertThat(sequence.getNextId(), `is`(5))

        assertThat(sequence.getNextId(), `is`(6))

    }

}
