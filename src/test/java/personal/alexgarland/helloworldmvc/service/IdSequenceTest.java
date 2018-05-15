package personal.alexgarland.helloworldmvc.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class IdSequenceTest {

  @Test
  public void worksWithDefaultSeed() {

    IdSequence sequence = new IdSequence();

    assertThat(sequence.getNextId(), is(1));

    assertThat(sequence.getNextId(), is(2));

    assertThat(sequence.getNextId(), is(3));

  }

  @Test
  public void worksWithNonDefaultSeed() {

    IdSequence sequence = new IdSequence(4);

    assertThat(sequence.getNextId(), is(4));

    assertThat(sequence.getNextId(), is(5));

    assertThat(sequence.getNextId(), is(6));

  }

}
