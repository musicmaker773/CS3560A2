
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
        if (isUniqueUserID(user)) {
            User u = new User(user);
            DefaultMutableTreeNode ur = new DefaultMutableTreeNode(u.toString(), false);

            // users.add(u);

            UserGroup p = new UserGroup(parent);
            DefaultMutableTreeNode pr = new DefaultMutableTreeNode(p.toString(), true);
            if (pr.toString().equals(root.toString())) {
                root.add(ur);
                groups.get(0).addUser(u);


                subjectUser.add(u);
                reloadTree();

                userNum.increment();
                return true;
            } else {
                Enumeration en = root.depthFirstEnumeration();
                while (en.hasMoreElements()) {
                    DefaultMutableTreeNode iterator = (DefaultMutableTreeNode) en.nextElement();

                    if (iterator.toString().equals(pr.toString())) {
                        if (iterator.getAllowsChildren()) {
                            iterator.add(ur);
                            for (int i = 0; i < groups.size(); i++) {
                                if (groups.get(i).getID().equals(p.getID())) {
                                    groups.get(i).addUser(u);
                                    subjectUser.add(u);
                                    break;
                                }
                            }
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
        if(isUniqueUserGroupID(group)) {
            UserGroup g = new UserGroup(group);
            DefaultMutableTreeNode ur = new DefaultMutableTreeNode(g.toString(), true);

            groups.add(g);

            UserGroup p = new UserGroup(parent);
            DefaultMutableTreeNode pr = new DefaultMutableTreeNode(p.toString(), true);
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

                    if (iterator.toString().equals(pr.toString())) {
                        if (iterator.getAllowsChildren()) {
                            iterator.add(ur);
                            for (int i = 0; i < groups.size(); i++) {
                                if (groups.get(i).getID().equals(p.getID())) {
                                    groups.get(i).addUserGroup(g);
                                    break;
                                }
                            }
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
