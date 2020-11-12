import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TwitterGUI{
    private JPanel adminControl = new JPanel(new BorderLayout(3, 3));
    private JPanel leftTreePanel = new JPanel();
    private JPanel controlSection = new JPanel();
    private LeftTreePanel ltp = new LeftTreePanel();

    private static Highlighter rp;

    private static TwitterGUI cb = null;

    // Assets to set up Admin's Panel
    private JTextField UserID;
    private JButton addUser;
    private JTextField GroupID;
    private JButton addGroup;
    private JButton openUserView;
    private JButton showUserTotal;
    private JButton showGroupTotal;
    private JButton showMessageTotal;
    private JButton showPositiveMessage;
    private JPanel blankArea1;
    private JPanel blankArea2;
    private JLabel displayOfTotals;

    //Assets to set up User's Panel
    private JPanel userControl = new JPanel(new BorderLayout(3, 3));
    private JPanel userSection = new JPanel();
    private JTextField textUserFollow;
    private JButton followUser;
    private JList listOfFollowers;
    private JPanel blankArea3;
    private JTextField textTweet;
    private JButton sendTweet;
    private JList newsFeed;
    private JPanel blankArea4;
    private JPanel topButtons;
    private JPanel topList;
    private JPanel midButtons;
    private JPanel midList;
    private JLabel followersLabel;
    private JLabel newsLabel;
    private DefaultListModel dlm;
    private DefaultListModel dlm2;

    private User tempUser;
    private User tempUser2;
    private String highlighted;


    private TwitterGUI(){
        initializeGUI();
    }
    public final void initializeGUI(){
        rp = Highlighter.getInstance();

        GridLayout layout = new GridLayout(0, 2);
        adminControl.setLayout(layout);

        adminControl.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        adminControl.add(tools, BorderLayout.PAGE_START);

        adminControl.add(new JLabel(""), BorderLayout.LINE_START);

        initializeRightPanel();


        leftTreePanel = ltp.getjPanel();


        ltp.getTree().addTreeSelectionListener(rp);

        adminControl.add(leftTreePanel);

        adminControl.add(controlSection);
        adminControl.setSize(1000,1000);
        adminControl.show();

        initializeUserEnd();

        userControl.add(userSection);
        userControl.setSize(1000,1000);

    }


    // initializes the right panel for the admin UI
    public final void initializeRightPanel() {

        UserID = new JTextField();
        addUser = new JButton("Add User");
        GroupID = new JTextField();
        addGroup = new JButton("Add Group");
        openUserView = new JButton("Open User View");
        showUserTotal = new JButton("Show User Total");
        showGroupTotal = new JButton("Show Group Total");
        showMessageTotal = new JButton("Show Message Total");
        showPositiveMessage = new JButton("Show Positive Percentage");
        blankArea1 = new JPanel();
        blankArea2 = new JPanel();
        displayOfTotals = new JLabel("");


        controlSection = new JPanel(new GridLayout(0, 2));
        controlSection.setBorder(new LineBorder(Color.BLACK));

        Insets buttonMargin = new Insets(0,0,0,0);

        UserID.setMargin(buttonMargin);
        addUser.setMargin(buttonMargin);
        GroupID.setMargin(buttonMargin);
        addGroup.setMargin(buttonMargin);
        openUserView.setMargin(buttonMargin);
        showUserTotal.setMargin(buttonMargin);
        showGroupTotal.setMargin(buttonMargin);
        showMessageTotal.setMargin(buttonMargin);
        showPositiveMessage.setMargin(buttonMargin);


        UserID.setPreferredSize(new Dimension(200, 50));
        addUser.setPreferredSize(new Dimension(200, 50));
        GroupID.setPreferredSize(new Dimension(200, 50));
        addGroup.setPreferredSize(new Dimension(200, 50));
        openUserView.setPreferredSize(new Dimension(200, 50));
        showUserTotal.setPreferredSize(new Dimension(200, 50));
        showGroupTotal.setPreferredSize(new Dimension(200, 50));
        showMessageTotal.setPreferredSize(new Dimension(200, 50));
        showPositiveMessage.setPreferredSize(new Dimension(200, 50));
        blankArea1.setPreferredSize(new Dimension(200, 50));
        blankArea2.setPreferredSize(new Dimension(200, 50));
        displayOfTotals.setPreferredSize(new Dimension(200, 50));

        addUser.addActionListener(new ButtonListener());
        addGroup.addActionListener(new ButtonListener());
        openUserView.addActionListener(new ButtonListener());
        showUserTotal.addActionListener(new ButtonListener());
        showGroupTotal.addActionListener(new ButtonListener());
        showMessageTotal.addActionListener(new ButtonListener());
        showPositiveMessage.addActionListener(new ButtonListener());

        controlSection.add(UserID);
        controlSection.add(addUser);
        controlSection.add(GroupID);
        controlSection.add(addGroup);
        controlSection.add(openUserView);
        controlSection.add(blankArea1);
        controlSection.add(displayOfTotals);
        controlSection.add(blankArea2);
        controlSection.add(showUserTotal);
        controlSection.add(showGroupTotal);
        controlSection.add(showMessageTotal);
        controlSection.add(showPositiveMessage);

    }
    // Initializes a specific user's UI
    public final void initializeUserEnd() {
        dlm = new DefaultListModel();
        dlm2 = new DefaultListModel();

        GridLayout layout = new GridLayout(0, 1);
        userControl.setLayout(layout);

        userSection = new JPanel(new GridLayout(4, 0));
        userSection.setBorder(new LineBorder(Color.BLACK));

        textUserFollow = new JTextField();
        followUser = new JButton("Follow User");
        listOfFollowers = new JList(dlm2);
        blankArea3 = new JPanel();
        textTweet = new JTextField();
        sendTweet = new JButton("Tweet");
        newsFeed = new JList(dlm);
        blankArea4 = new JPanel();
        followersLabel = new JLabel("Currently Following:");
        newsLabel = new JLabel("News Feed:");

        topButtons = new JPanel(new GridLayout(2,2));
        topList = new JPanel();
        midButtons = new JPanel(new GridLayout(2, 2));
        midList = new JPanel();

        Insets buttonMargin = new Insets(0,0,0,0);

        textUserFollow.setMargin(buttonMargin);
        followUser.setMargin(buttonMargin);
        textTweet.setMargin(buttonMargin);
        sendTweet.setMargin(buttonMargin);

        textUserFollow.setPreferredSize(new Dimension(200, 50));
        followUser.setPreferredSize(new Dimension(200, 50));
        listOfFollowers.setPreferredSize(new Dimension(400, 100));
        blankArea3.setPreferredSize(new Dimension(200, 50));
        textTweet.setPreferredSize(new Dimension(200, 50));
        sendTweet.setPreferredSize(new Dimension(200, 50));
        newsFeed.setPreferredSize(new Dimension(400, 100));
        blankArea4.setPreferredSize(new Dimension(200, 50));
        followersLabel.setPreferredSize(new Dimension(200, 50));
        newsLabel.setPreferredSize(new Dimension(200, 50));

        topButtons.setPreferredSize(new Dimension(400, 100));
        topList.setPreferredSize(new Dimension(400, 100));
        midButtons.setPreferredSize(new Dimension(400, 100));
        midList.setPreferredSize(new Dimension(400, 100));

        followUser.addActionListener(new ButtonListener());
        sendTweet.addActionListener(new ButtonListener());

        /* userSection.add(textUserFollow);
        userSection.add(followUser);
        userSection.add(listOfFollowers);
        userSection.add(blankArea3);
        userSection.add(textTweet);
        userSection.add(sendTweet);
        userSection.add(newsFeed);
        userSection.add(blankArea4); */

        topButtons.add(textUserFollow);
        topButtons.add(followUser);
        topButtons.add(followersLabel);
        topButtons.add(blankArea3);
        topList.add(listOfFollowers);
        midButtons.add(textTweet);
        midButtons.add(sendTweet);
        midButtons.add(newsLabel);
        midButtons.add(blankArea4);
        midList.add(newsFeed);

        userSection.add(topButtons);
        userSection.add(topList);
        userSection.add(midButtons);
        userSection.add(midList);


    }
    // uploads the user's followers and news feed
    public void getInfoUser(User user) {
        tempUser2 = user;



        dlm.clear();
        for(int i = tempUser2.getNewsFeed().size() - 1; i >= 0; i--) {
            dlm.addElement(tempUser2.getNewsFeed().get(i));
        }
        dlm2.clear();
        for(int i = tempUser2.getFollowing().size() - 1; i >= 0; i--) {
            dlm2.addElement(tempUser2.getFollowing().get(i));
        }


    }

    // Singleton pattern
    public static TwitterGUI getInstance() {
        if(cb == null) {
            cb = new TwitterGUI();
        }
        return cb;
    }




    public final JComponent getAdminControl() {
        return adminControl;
    }

    public final JComponent getUserControl() {
        return userControl;
    }

    public static void main(String[] args){
        Runnable r = new Runnable() {

            @Override
            public void run() {
                TwitterGUI cb = TwitterGUI.getInstance();

                JFrame f = new JFrame("Admin Twitter");
                f.add(cb.getAdminControl());
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);

                // ensures the frame is the minimum size it needs to be
                // in order display the components within it
                f.pack();
                // ensures the minimum size is enforced.
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);


    }

    class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == showUserTotal) {
                displayOfTotals.setText(ltp.getUserTotal());
            }
            if(e.getSource() == showGroupTotal) {
                displayOfTotals.setText(ltp.getGroupTotal());

            }
            if(e.getSource() == showMessageTotal){
                displayOfTotals.setText(ltp.getTotalMessages());
            }
            if(e.getSource() == showPositiveMessage){
                displayOfTotals.setText(ltp.getPositiveMessagePercent());
            }
            if(e.getSource() == openUserView) {
                highlighted = rp.getHighlighted();
                tempUser = ltp.requestUser(highlighted);
                if(!highlighted.equals("") || !tempUser.getID().equals("")) {

                    TwitterGUI userGUI = TwitterGUI.getInstance();
                    JFrame u = new JFrame(highlighted + "'s Twitter");

                    userGUI.getInfoUser(tempUser);

                    u.add(userGUI.getUserControl());

                    u.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    u.setLocationByPlatform(true);

                    // ensures the frame is the minimum size it needs to be
                    // in order display the components within it
                    u.pack();
                    // ensures the minimum size is enforced.
                    u.setMinimumSize(u.getSize());
                    u.setVisible(true);

                }
            }
            if(e.getSource() == addUser){

                highlighted = rp.getHighlighted();
                if(!highlighted.equals("")) {
                    tempUser = ltp.requestUser(highlighted);

                    StringBuilder tempSB = new StringBuilder(highlighted);
                    tempSB.deleteCharAt(0);
                    highlighted = tempSB.toString();
                    if (tempUser.getID().equals("") || !UserID.getText().equals("")) {
                        ltp.addUser(UserID.getText(), highlighted);
                    }
                }
                UserID.setText("");
            }
            if(e.getSource() == addGroup) {
                highlighted = rp.getHighlighted();
                if(!highlighted.equals("")) {

                    tempUser = ltp.requestUser(highlighted);

                    StringBuilder tempSB = new StringBuilder(highlighted);
                    tempSB.deleteCharAt(0);
                    highlighted = tempSB.toString();

                    if (tempUser.getID().equals("") || !GroupID.getText().equals("")) {
                        ltp.addUserGroup(GroupID.getText(), highlighted);
                    }
                }
                GroupID.setText("");

            }
            if(e.getSource() == sendTweet) {

                String temp = textTweet.getText();
                if(!temp.equals("")) {
                    tempUser2 = ltp.requestUser(tempUser2.getID());
                    tempUser2 = ltp.postTweet(tempUser2, temp);

                    dlm.clear();
                    for(int i = tempUser2.getNewsFeed().size() - 1; i >= 0; i--) {
                        dlm.addElement(tempUser2.getNewsFeed().get(i));
                    }


                }
                textTweet.setText("");
            }
            if(e.getSource() == followUser) {
                String temp = textUserFollow.getText();
                if(!temp.equals("")) {
                    tempUser2 = ltp.requestUser(tempUser2.getID());
                    tempUser2 = ltp.addFollowing(tempUser2, temp);

                    dlm2.clear();
                    for(int i = tempUser2.getFollowing().size() - 1; i >= 0; i--) {
                        dlm2.addElement(tempUser2.getFollowing().get(i));
                    }
                }
                textUserFollow.setText("");
            }
        }
    }
}
