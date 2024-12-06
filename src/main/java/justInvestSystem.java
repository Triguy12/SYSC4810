import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class justInvestSystem {

    public static void main(String[] args) {

        SystemState currentState = SystemState.NO_USER;

        Scanner scanner = new Scanner(System.in);

        InputController inputController = new InputController();

        SystemModel systemModel = new SystemModel();
        boolean logout = false;

        while(true)
        {
            if(logout)
            {
                break;
            }
            switch (currentState)
            {
                case MAIN_SYSTEM:
                    System.out.println("\njustInvest System");
                    System.out.println("___________________________________");
                    System.out.println("Operations available on the system: ");
                    System.out.println("1.View account balence");
                    System.out.println("2.View investment portfolio");
                    System.out.println("3.Modify invetment portfolio");
                    System.out.println("4.View Financial Advisor contact info");
                    System.out.println("5.View Financial Planner contact info");
                    System.out.println("6.View money market instruments");
                    System.out.println("7.View private consumer instruments");
                    System.out.println("8.Log out...");

                    String commandWord = "";

                    while(true)
                    {
                        System.out.println("\nUser: " + systemModel.getCurrentUser().getName() + " Role: " + systemModel.getCurrentUser().getRole().toString());
                        System.out.println("Your authorized operations are: " + systemModel.getCurrentUser().getRole().getPermissions());
                        System.out.println("Which operation would you like to perform?");
                        commandWord = scanner.nextLine();

                        if(commandWord.length() != 1)
                        {
                            System.out.println("Invalid entry: please enter number between 1 and 8.");
                            continue;
                        }

                        if (commandWord.equals("8")){
                            System.out.println("Logging out ;)");
                        }
                        else if(!systemModel.getCurrentUser().getRole().getPermissions().contains(Integer.parseInt(commandWord)))
                        {
                            System.out.println("You do not have permission to use command: " + commandWord + " or command is invalid!");
                            continue;
                        }
                        break;
                    }
                    if(inputController.isValidInput(commandWord, Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8")))
                    {
                        switch (commandWord) {
                            case "1":
                                systemModel.getAccountBalance(systemModel.getCurrentUser().getViewedUser());
                                break;
                            case "2":
                                systemModel.getInvestmentPortfolio(systemModel.getCurrentUser().getViewedUser());
                                break;
                            case "3":
                                systemModel.setInvestmentPortfolio(systemModel.getCurrentUser().getViewedUser());
                                break;
                            case "4":
                                systemModel.getAdvisorContactInfo(systemModel.getCurrentUser().getViewedUser());
                                break;
                            case "5":
                                systemModel.getPlannerContactInfo(systemModel.getCurrentUser().getViewedUser());
                                break;
                            case "6":
                                systemModel.getMoneyMarketInstruments(systemModel.getCurrentUser().getViewedUser());
                                break;
                            case "7":
                                systemModel.getConsumerInstruments(systemModel.getCurrentUser().getViewedUser());
                                break;
                            case "8":
                                systemModel.logOut();
                                currentState = SystemState.NO_USER;
                                break;

                            default:
                                System.out.println("Not a valid command.");
                                break;
                        }
                    }
                    break;

                case LOGIN:
                    System.out.println("\njustInvest System");
                    System.out.println("___________________________________");
                    System.out.println("-- Login Page --");

                    while (true)
                    {
                        System.out.print("Enter username: ");
                        String usernameInput = scanner.nextLine();

                        System.out.print("Enter password: ");
                        String passwordInput = scanner.nextLine();

                        if(!inputController.sanitizeInput(usernameInput))
                        {
                            System.out.print("Stop the trickery with your username!");
                            continue;
                        }

                        if(!inputController.sanitizeInput(passwordInput))
                        {
                            System.out.print("Stop the trickery with your password!");
                            continue;
                        }

                        if(systemModel.verifyUser(usernameInput, passwordInput))
                        {
                            System.out.println("Logged in Successfully");
                            System.out.print("ACCESS GRANTED!");
                            currentState = SystemState.MAIN_SYSTEM;
                            systemModel.setCurrentUser(systemModel.getUser(usernameInput));
                        }
                        else
                        {
                            System.out.println("Login Failed");
                            currentState = SystemState.LOGIN;
                        }
                        break;
                    }
                    break;
                case REGISTER:
                    System.out.println("\njustInvest System");
                    System.out.println("___________________________________");
                    System.out.println("-- Registration Page --");

                    while (true)
                    {
                        System.out.print("Enter username: ");
                        String usernameInput = scanner.nextLine();

                        System.out.print("Enter password: ");
                        String passwordInput = scanner.nextLine();

                        System.out.println("Valid Roles Include: ");
                        System.out.println("1.Client");
                        System.out.println("2.PremiumClient");
                        System.out.println("3.FinancialAdvisor");
                        System.out.println("4.FinancialPlanner");
                        System.out.println("5.Teller");
                        System.out.println("Enter Role: ");
                        String roleValue = scanner.nextLine();

                        Role regRole;

                        if(inputController.isValidInput(roleValue, Arrays.asList("1", "2", "3", "4", "5")))
                        {
                            switch (roleValue) {
                                case "1":
                                    regRole = Role.Client;
                                    break;
                                case "2":
                                    regRole = Role.PremiumClient;
                                    break;
                                case "3":
                                    regRole = Role.FinancialAdvisor;
                                    break;
                                case "4":
                                    regRole = Role.FinancialPlanner;
                                    break;
                                case "5":
                                    regRole = Role.Teller;
                                    break;
                                default:

                                    break;
                            }
                        }


                        if(!inputController.sanitizeInput(usernameInput))
                        {
                            System.out.print("Stop the trickery with your username!");
                            continue;
                        }

                        if(!inputController.sanitizeInput(passwordInput))
                        {
                            System.out.print("Stop the trickery with your password!");
                            continue;
                        }

                        if(systemModel.createAccount(usernameInput, passwordInput))
                        {
                            System.out.println("Registration Complete, returning to home page.");
                            User newUser = new User(usernameInput, Role.Client);
                            systemModel.addUser(newUser);
                            currentState = SystemState.LOGIN;
                        }
                        else
                        {
                            System.out.println("Registration Failed");
                            currentState = SystemState.REGISTER;
                        }
                        break;
                    }
                    break;
                default:
                    System.out.println("\njustInvest System");
                    System.out.println("___________________________________");
                    System.out.println("Operations available on the system: ");
                    System.out.println("1.Register as a new user");
                    System.out.println("2.Login to an existing account");
                    System.out.println("3.Exit System");

                    while (true)
                    {
                        System.out.println("Your authorized operations are 1, 2, or 3");
                        System.out.println("Which operation would you like to perform?");
                        System.out.print("Selection: ");
                        String input = scanner.nextLine();

                        if(inputController.isValidInput(input, Arrays.asList("1", "2", "3")))
                        {
                            if(input.equals("1"))
                            {
                                currentState = SystemState.REGISTER;
                                break;
                            }
                            else if (input.equals("2"))
                            {
                                currentState = SystemState.LOGIN;
                                break;
                            }
                            else if (input.equals("3"))
                            {
                                logout = true;
                                break;
                            }
                            else
                            {
                                System.out.println("Invalid Input.");
                            }
                        }
                    }
                    break;
            }
        }
        System.out.println("Exiting System. Bye for now ;~;");
    }
}
