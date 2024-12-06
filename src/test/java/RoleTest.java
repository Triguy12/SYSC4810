import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class RoleTest {

    @Test
    public void testRoleConstruction()
    {
        Role role = Role.PremiumClient;
        assertEquals("Role should be 'PremiumClient'", Role.PremiumClient, role);
        assertEquals("Role permissions should be '1, 2, 3, 4, 8'", Role.PremiumClient.getPermissions(), Arrays.asList(1, 2, 3, 4, 8));
    }

    @Test
    public void testRolePermissions() {
        assertEquals(Role.Client.getPermissions(), Arrays.asList(1, 2, 4, 8));
        assertEquals(Role.PremiumClient.getPermissions(), Arrays.asList(1, 2, 3, 4, 8));
        assertEquals(Role.FinancialAdvisor.getPermissions(), Arrays.asList(1, 2, 3, 7, 8));
        assertEquals(Role.FinancialPlanner.getPermissions(), Arrays.asList(1, 2, 3, 6, 7, 8));
        assertEquals(Role.Teller.getPermissions(), Arrays.asList(1, 2, 8));
    }
}
