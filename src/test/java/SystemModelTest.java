import org.junit.Test;
import static org.junit.Assert.*;

public class SystemModelTest {

    @Test
    public void testingContructor()
    {
        SystemModel model = new SystemModel();

        User user = model.getUser("Emery Blake");

        assertNotNull(user);
        assertEquals("Emery Blake", user.getName());
    }

    @Test
    public void testingAddUser()
    {
        SystemModel model = new SystemModel();

        User user = new User("PersonName", Role.Client);
        model.addUser(user);
        user = null;
        user = model.getUser("PersonName");
        assertNotNull(user);
        assertEquals("PersonName", user.getName());
    }

    @Test
    public void testingCurrentUser()
    {
        SystemModel model = new SystemModel();
        assertNull(model.getCurrentUser());

        User userToAdd = new User("PersonName", Role.Client);
        model.setCurrentUser(userToAdd);

        User user = model.getCurrentUser();
        assertNotNull(model.getCurrentUser());
        assertEquals("PersonName", model.getCurrentUser().getName());

        model.logOut();
        assertNull(model.getCurrentUser());

    }

    @Test
    public void testingCreateAccount()
    {
        SystemModel model = new SystemModel();
        boolean failure = model.createAccount("Triton", "qazwsx12&");
        assertFalse(failure);
        boolean success = model.createAccount("Triton", "Qazwsx12&");
        assertTrue(success);

    }

    /*
    public boolean createAccount(String usernameInput, String passwordInput)
    {
        if(passwordManager.addUsernamePasswordPair("passwd.txt", usernameInput, passwordInput))
        {
           System.out.println("Account created successfully");
           return true;
        }

        System.out.println("Could not create account!");
        return false;
    }

    public boolean verifyUser(String usernameInput, String passwordInput)
    {
        if(passwordManager.verifyUsernamePasswordPair(usernameInput, passwordInput))
        {
            System.out.println("Account Retrieved");
            return true;
        }

        System.out.println("Could not retrieve account!");
        return false;
    }
    * */

}
