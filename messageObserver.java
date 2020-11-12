import java.util.ArrayList;

public class messageObserver implements Observer{

    protected SubjectUser su;
    public messageObserver(SubjectUser su) {
        this.su = su;
        this.su.attach(this);
    }



    @Override
    public void update(User user, String s) {
        ArrayList<User> users = su.getUsers();
        ArrayList<String> followers = user.getFollowers();

        for (int i = 0; i < users.size(); i++) {
            for(int j = 0; j < followers.size(); j++) {
                if(users.get(i).getID().equals(followers.get(j))) {
                    users.get(i).addTweetNews(s);
                }
            }
        }
        su.updateUsers(users);
    }
}
