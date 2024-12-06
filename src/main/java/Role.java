import java.util.Arrays;
import java.util.List;

public enum Role {
    Client(Arrays.asList(1, 2, 4, 8)),
    PremiumClient(Arrays.asList(1, 2, 3, 4, 8)),
    FinancialAdvisor(Arrays.asList(1, 2, 3, 7, 8)),
    FinancialPlanner(Arrays.asList(1, 2, 3, 6, 7, 8)),
    Teller(Arrays.asList(1, 2, 8));

    private List<Integer> permissions;
    Role(List<Integer> permissions)
    {
        this.permissions = permissions;
    }

    public List<Integer> getPermissions()
    {
        return permissions;
    }

}
