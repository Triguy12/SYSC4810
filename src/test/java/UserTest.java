import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void testUserConstruction()
    {
        User testUser1 = new User("Garfield", Role.Client);
        User testUser2 = new User("Bugs Bunny", Role.FinancialPlanner);

        assertEquals("User name should be 'Garfield'", "Garfield", testUser1.getName());
        assertEquals("User name should be 'Bugs Bunny'", "Bugs Bunny", testUser2.getName());

        assertEquals("User Role should be 'Client'", Role.Client, testUser1.getRole());
        assertEquals("User Role should be 'FinancialPlanner'", Role.FinancialPlanner, testUser2.getRole());

        assertEquals("User 'Client' can access should be self:", testUser1.getViewedUser(), testUser1);
        assertEquals("User 'FinancialPlanner' can access should be null", testUser2.getViewedUser(), null);
    }

    @Test
    public void testSetUser()
    {
        User testUser1 = new User("Garfield agian", Role.PremiumClient);
        User testUser2 = new User("Bugs Bunny again", Role.FinancialAdvisor);

        testUser1.setViewedUser(testUser2);
        testUser2.setViewedUser(testUser1);

        assertEquals("User 'Premium Client' can not set its viewed user", testUser1.getViewedUser(), testUser1);
        assertEquals("User name should be 'Bugs Bunny'", testUser2.getViewedUser(), testUser1);
    }
}
