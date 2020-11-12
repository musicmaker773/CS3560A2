import java.util.ArrayList;

// Subject for Observer pattern
// This file takes in any user and updates them accordingly on the following or news feed aspect
public class SubjectUser {
    private ArrayList<User> users;

    private Observer observer;

    public SubjectUser() {
        users = new ArrayList<>();

    }

    // attaches the Observer file to this file 
    public void attach(Observer observer) {
        this.observer = observer;
    }


    public void add(User user) {

            users.add(user);


    }

    public ArrayList<User> getUsers() {
        return users;
    }



    public User getUser(String u) {
        User user = new User("");
        for(int i = 0; i < users.size(); i++) {
            if(users.get(i).getID().equals(u)) {
                user = users.get(i);
                break;
            }
        }

        return user;
    }

    public User updateNewsMessage(User user, String message) {
            for(int i = 0; i < users.size(); i++) {
                if(users.get(i).getID().equals(user.getID())){
                    String tweet = users.get(i).postTweet(message);
                    user = users.get(i);

                    // update other users in the observer file
                    observer.update(user, tweet);
                }
            }
            return user;


    }

    public User updateFollowing(User user, User following) {
        for(int i = 0; i < users.size(); i++) {
            if(user.getID().equals(users.get(i).getID())) {
                users.get(i).updateFollowing(following.getID());
                user = users.get(i);
                // updates for followers added
                for(int j = 0; j < users.size(); j++) {
                    if(following.getID().equals(users.get(j).getID())) {
                        users.get(j).updateFollowers(user.getID());
                        break;
                    }
                }
                break;

            }
        }

        return user;
    }
    public void updateUser(User user) {


        for(int i = 0; i < users.size(); i++) {
            if(users.get(i).getID().equals(user.getID())) {
                users.set(i, user);
                break;
            }
        }
    }
    public void updateUsers(ArrayList<User> us) {
        users = us;
    }

}
