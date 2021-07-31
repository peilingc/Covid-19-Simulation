import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import greenfoot.junitUtils.runner.GreenfootRunner;
import greenfoot.junitUtils.*;

/**
 * Write a description of test TestHeal here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
@RunWith(GreenfootRunner.class)
public class TestHeal {

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
        Person person = new Person(false,0,false,false);
        
        // WHEN : Actions to test are executed here.
        assertEquals(0,Person.getNumInfected());
        assertEquals(0,person.getInfectionTime());
        person.infect();
        assertEquals(1,Person.getNumInfected());
        assertEquals(400,person.getInfectionTime());
        for(int i=400; i>0; i--){
            person.heal();
        }
        assertEquals(0,person.getInfectionTime());
        assertEquals(0,Person.getNumInfected());
        assertEquals(HealthStatus.immune, person.getHealthStatus());
        // THEN : Asserts to verify the actions are executed here.

    }
    
}
