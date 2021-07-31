import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import greenfoot.junitUtils.runner.GreenfootRunner;
import greenfoot.junitUtils.*;

/**
 * Write a description of test TestSetStatus here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
@RunWith(GreenfootRunner.class)
public class TestSetStatus {

    // Common variables to all tests are declared here.

    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp() throws Exception {

        // Common variables to all tests are initialized here.

    }

    /**
     * The name of a test starts with the string "test" and 
     * must include the name of the action to be verified.
     *
     * Any unit test has three parts: GIVEN, WHEN and THEN.
     *
     * JUnit4 test must be public, void and without parameters.
     */
    @Test
    public void test() throws Exception {

        // GIVEN: Local variables to this test are declared and initialized here.

        // WHEN : Actions to test are executed here.

        // THEN : Asserts to verify the actions are executed here.
        Person person = new Person(false,0,false,false);
        person.setStatus(HealthStatus.immune);
        assertEquals(HealthStatus.immune,person.getHealthStatus());
        assertEquals(HealthStatus.immune.getIcon(),person.getHealthStatus().getIcon());

    }
    
}
