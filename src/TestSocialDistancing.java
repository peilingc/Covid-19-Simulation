import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import greenfoot.junitUtils.runner.GreenfootRunner;
import greenfoot.junitUtils.*;

/**
 * Write a description of test TestSocialDistancing here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
@RunWith(GreenfootRunner.class)
public class TestSocialDistancing {

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
        MyWorld myWorld = new MyWorld();
        Person person1 = new Person(false,101,false,false);
        Person person2 = new Person(false,101,false,false);
        
        // WHEN : Actions to test are executed here.
        myWorld.addObject(person1, 500, 300);
        myWorld.addObject(person2, 500, 300);
        person1.infect();
        person1.infectOthers();
        
        // THEN : Asserts to verify the actions are executed here.
        assertEquals(HealthStatus.susceptible, person2.getHealthStatus());
    }
    
}
