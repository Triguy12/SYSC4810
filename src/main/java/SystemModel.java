import java.util.ArrayList;
import java.util.HashMap;

public class SystemModel {
    ArrayList<User> users;
    PasswordManager passwordManager;
    User currentUser;
    public SystemModel()
    {
        passwordManager = new PasswordManager();
        users = new ArrayList<User>() {{
            add(new User("Sasha Kim", Role.Client));
            add(new User("Emery Blake", Role.Client));
            add(new User("Noor Abbasi", Role.PremiumClient));
            add(new User("Zuri Adebayo", Role.PremiumClient));
            add(new User("Mikael Chen", Role.FinancialAdvisor));
            add(new User("Jordan Riley", Role.FinancialAdvisor));
            add(new User("Ellis Nakamura", Role.FinancialPlanner));
            add(new User("Harper Diaz", Role.FinancialPlanner));
            add(new User("Alex Hayes", Role.Teller));
            add(new User("Adair Patel", Role.Teller));
        } };

        for (User u : users) {
            //GENERATE A PASSWORD STORE FOR EACH PERSON
            //usernameToPasswordMap.put(u.getName(),)
        }
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public User getUser(String username){
        for (User u : users) {
            if(username.equals(u.getName()))
            {
                return u;
            }
        }
        return null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void logOut()
    {
        setCurrentUser(null);
    }

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

    public void getAccountBalance(User user)
    {
        System.out.println("Here is your Account Balence");
    }

    public void getInvestmentPortfolio(User user)
    {
        System.out.println("Here is your Investment Portfolio");
    }

    public void setInvestmentPortfolio(User user)
    {
        System.out.println("Setting Investment Portfolio");
    }

    public void getAdvisorContactInfo(User user)
    {
        System.out.println("Here is your Advisor's Contact Info");
    }

    public void getPlannerContactInfo(User user)
    {
        System.out.println("Here is your Planner's Contact Info");
    }

    public void getMoneyMarketInstruments(User user)
    {
        System.out.println("Here is the Money Market Instruments");
    }

    public void getConsumerInstruments(User user)
    {
        System.out.println("Here is the Consumer Instruments");
    }
}
