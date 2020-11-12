
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.*;


public class LeftTreePanel {

    private JPanel jPanel = new JPanel();
    private JTree tree;
    private DefaultMutableTreeNode root;
    private ArrayList<UserGroup> groups;
    private numOfMessages messageNum;
    private numOfGroups groupNum;
    private numOfUsers userNum;
    private positiveMessages pMes;
    private Visitor v;

    private SubjectUser subjectUser;

    // initializes the left panel tree
    public LeftTreePanel(){



        messageNum = new numOfMessages();
        groupNum = new numOfGroups();
        userNum = new numOfUsers();
        pMes = new positiveMessages();
        v = new statVisitor();


        groups = new ArrayList<>();


        UserGroup r = new UserGroup("Root");
        root = new DefaultMutableTreeNode(r.toString(), true);

        subjectUser = new SubjectUser();
        new messageObserver(subjectUser);

        groups.add(r);

        jPanel = new JPanel(new GridLayout(0, 1));
        jPanel.setBorder(new LineBorder(Color.BLACK));

        tree = new JTree(root);

        tree.setRootVisible(true);
        tree.setShowsRootHandles(true);
        jPanel.add(tree);


    }
    // tree needs to be reloaded every time a node is added
    private void reloadTree() {
        ((DefaultTreeModel)tree.getModel()).reload();

    }
    private boolean isUniqueUserID(String id){
        if(subjectUser.getUsers().size() == 0) {
            return true;
        }
        for(int i = 0; i < subjectUser.getUsers().size(); i++) {
            if(subjectUser.getUsers().get(i).getID().equals(id)) {
                return false;
            }
        }

        return true;
    }
    private boolean isUniqueUserGroupID(String id) {
        if(groups.size() <= 1) {
            return true;
        }
        for(int i = 0; i < groups.size(); i++) {
            if(groups.get(i).getID().equals(id)) {
                return false;
            }
        }
        return true;
    }
    // checks if a message is positive 
    // also checks if these positive words don't have a word preceeding with 'not'
    private void isPositiveMessage(String message) {
        String[] pWs = {"good", "great", "awesome", "brilliant", "spectacular", "happy", "jazzed", "excited", "glad", "smile"};
        for (int i = 0; i < pWs.length; i++) {
            if (message.toLowerCase().contains(pWs[i].toLowerCase()) && !message.toLowerCase().contains(("not " + pWs[i]).toLowerCase())) {
                pMes.increment();
                return;
            }
        }

    }

    public JPanel getjPanel() {
        return jPanel;
    }
    public JTree getTree() {
        return tree;
    }

    public void updateUser(User user) {
        subjectUser.updateUser(user);
    }
    public User postTweet(User user, String message) {
        messageNum.increment();
        isPositiveMessage(message);
        return subjectUser.updateNewsMessage(user, message);
    }
    public User addFollowing(User user, String following) {
        User f = requestUser(following);
        if(!f.getID().equals("")) {
            user = subjectUser.updateFollowing(user, f);

        }
        return user;
    }

    public boolean addUser(String user, String parent){
        // checks is the user being added is unique
        if (isUniqueUserID(user)) {
            User u = new User(user);
            // if it is a user, the ability to add children is false
            DefaultMutableTreeNode ur = new DefaultMutableTreeNode(u.toString(), false);


            UserGroup p = new UserGroup(parent);
            DefaultMutableTreeNode pr = new DefaultMutableTreeNode(p.toString(), true);
            // if user is being added to the root
            if (pr.toString().equals(root.toString())) {
                root.add(ur);
                groups.get(0).addUser(u);


                subjectUser.add(u);
                reloadTree();

                userNum.increment();
                return true;
            } else {
                Enumeration en = root.depthFirstEnumeration();
                // traverses the tree for the group it is being added to
                while (en.hasMoreElements()) {
                    DefaultMutableTreeNode iterator = (DefaultMutableTreeNode) en.nextElement();
              
                    if (iterator.toString().equals(pr.toString())) {
                        // checks if this is a group by checking if the node allows children
                        if (iterator.getAllowsChildren()) {
                            iterator.add(ur);
                            for (int i = 0; i < groups.size(); i++) {
                                if (groups.get(i).getID().equals(p.getID())) {
                                    groups.get(i).addUser(u);
                                    subjectUser.add(u);
                                    break;
                                }
                            }
                            // reloading tree after node is added
                            reloadTree();

                            userNum.increment();
                            return true;
                        }
                    }
                }
            }
        }
        return false;

    }
    public boolean addUserGroup(String group, String parent){
        // checks if the user group name is unique
        if(isUniqueUserGroupID(group)) {
            UserGroup g = new UserGroup(group);
            // if user group is being added, ability to add children is true
            DefaultMutableTreeNode ur = new DefaultMutableTreeNode(g.toString(), true);

            groups.add(g);

            UserGroup p = new UserGroup(parent);
            DefaultMutableTreeNode pr = new DefaultMutableTreeNode(p.toString(), true);
            // if group is being added to the root
            if (pr.toString().equals(root.toString())) {
                root.add(ur);
                groups.get(0).addUserGroup(g);
                reloadTree();

                groupNum.increment();
                return true;
            } else {
                Enumeration en = root.depthFirstEnumeration();
                while (en.hasMoreElements()) {
                    DefaultMutableTreeNode iterator = (DefaultMutableTreeNode) en.nextElement();
                    // traverses the tree to find the group the group is being added to

                    if (iterator.toString().equals(pr.toString())) {
                        //checks by seeing if the node can add children
                        if (iterator.getAllowsChildren()) {
                            iterator.add(ur);
                            for (int i = 0; i < groups.size(); i++) {
                                if (groups.get(i).getID().equals(p.getID())) {
                                    groups.get(i).addUserGroup(g);
                                    break;
                                }
                            }
                            // reloading tree after node is added
                            reloadTree();

                            groupNum.increment();
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    public User requestUser(String user){
        User u = subjectUser.getUser(user);
        return u;
    }

    // Visitor pattern outputs
    public String getUserTotal() {
        return userNum.accept(v);
    }
    public String getGroupTotal() {
        return groupNum.accept(v);
    }
    public String getPositiveMessagePercent() {
        pMes.calculatePercent(messageNum);
        return pMes.accept(v);
    }
    public String getTotalMessages() {
        return messageNum.accept(v);
    }



}
