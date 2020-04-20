package builder;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class TrainerBuilderTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void createWithoutAge() {
        expectedException.expect(IllegalStateException.class);
        new TrainerBuilder().build();
    }

    @Test
    public void createWithInvalidAge() {
        expectedException.expect(IllegalArgumentException.class);
        new TrainerBuilder().withAge(10);
    }

    @Test
    public void createWithInvalidFormatAge() {
        expectedException.expect(IllegalArgumentException.class);
        new TrainerBuilder().withAge("ten").build();
    }

    @Test
    public void createWithoutName() {
        Trainer trainer = new TrainerBuilder().withAge(30).build();
        assertThat(trainer.getName(), equalTo("anonymous"));
        assertThat(trainer.getAge(), equalTo(30));
    }

    @Test
    public void createWithName() {
        Trainer trainer = new TrainerBuilder().withName("John Doe").withAge(30).build();
        assertThat(trainer.getName(), equalTo("John Doe"));
        assertThat(trainer.getAge(), equalTo(30));
    }
}
