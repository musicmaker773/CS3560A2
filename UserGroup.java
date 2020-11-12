import java.util.ArrayList;

public class UserGroup extends Entries{

    private ArrayList<Entries> users;
    private ArrayList<Entries> groups;

    private UserGroup parent;

    public UserGroup(String id) {
        ID = id;
        users = new ArrayList<>();
        groups = new ArrayList<>();
    }
    public UserGroup(String id, UserGroup p) {
        ID = id;
        users = new ArrayList<>();
        groups = new ArrayList<>();
        setParent(p);
    }

    public void setParent(UserGroup ug) {
        parent = ug;
    }

    public void updateUser(User user) {
        for(int i = 0; i < users.size(); i++) {
            if(users.get(i).getID().equals(user.getID())) {
                users.set(i, user);
            }
        }
    }

    public void updateUserGroup(UserGroup ug) {
        for(int i = 0; i < groups.size(); i++) {
            if(groups.get(i).getID().equals(ug.getID())) {
                groups.set(i, ug);
            }
        }
    }
    public UserGroup getParent(){
        return parent;
    }

    public String getID() {
        return ID;
    }

    public void addUser(User user) {

        users.add(user);
    }
    public void addUserGroup(UserGroup group) {

        groups.add(group);
    }

    @Override
    public String toString() {
        // I used a star in front to distinguish between folder and leaf
        return "*" + ID;
    }

    public boolean isUser() {
        return false;
    }

}
