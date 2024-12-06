public class User {
    String name;
    Role role;

    User viewedUser;
    boolean canSetViewedUser;

    public User(String name, Role role) {
        this.name = name;
        this.role = role;

        viewedUser = this;
        canSetViewedUser = false;

        if(role != Role.Client && role != Role.PremiumClient)
        {
            viewedUser = null;
            canSetViewedUser = true;
        }
    }

    public Role getRole()
    {
        return role;
    }

    public String getName() {
        return name;
    }

    public void setViewedUser(User toView)
    {
        if(canSetViewedUser)
        {
            viewedUser = toView;
        }
        else
        {
            System.out.println("You only have permission to view your own data.");
        }
    }

    public User getViewedUser() {
        return viewedUser;
    }
}
