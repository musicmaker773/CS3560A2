

import java.util.ArrayList;

public class User extends Entries {


    private ArrayList<String> followers;
    private ArrayList<String> following;
    private ArrayList<String> newsFeed;

    private UserGroup parent;

    private long creationTime = 0;
    private long lastUpdateTime = 0;


    public User(String ID) {
        this.ID = ID;
        followers = new ArrayList<>();
        following = new ArrayList<>();
        newsFeed = new ArrayList<>();
    }
    public User(String ID, UserGroup ug) {
        this.ID = ID;
        followers = new ArrayList<>();
        following = new ArrayList<>();
        newsFeed = new ArrayList<>();
        setParent(ug);
    }
    public void setParent(UserGroup p) {
        parent = p;
    }
    public void setCreationTime(long t) {
        creationTime = t;
    }
    public void setLastUpdateTime(long t) {
        lastUpdateTime = t;
    }
    public long getCreationTime(){
        return creationTime;
    }
    public long getLastUpdateTime() {
        return lastUpdateTime;
    }
    public UserGroup getParent() {
        return parent;
    }


    public String getID() {
        return ID;
    }

    public ArrayList<String> getFollowers(){

        return followers;
    }
    public ArrayList<String> getNewsFeed() {
        return newsFeed;
    }
    public ArrayList<String> getFollowing() {
        return following;
    }



    public void updateFollowing(String user) {
        following.add(user);
    }

    public String postTweet(String tweet) {
        String t = ID + ": " + tweet;
        newsFeed.add(t);
        return t;
    }
    public void addTweetNews(String tweet) {
        newsFeed.add(tweet);
    }

    public void updateFollowers(String user){
        followers.add(user);
    }

    public String toString() {
        return ID;
    }

    public boolean isUser() {
        return true;
    }

}

