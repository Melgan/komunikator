

import java.awt.Dimension;
import java.awt.GridLayout;


import javax.swing.JFrame;


@SuppressWarnings("serial")
public class MainFrame extends JFrame {
    private final static String title = "P2P Chat";
    private ConversationPanel mainPanel;

    public MainFrame(User user) {
        super(title + " - " + user.toString());

        mainPanel = new ConversationPanel(user);

        setFeatures();


        add(mainPanel);
    }


    private void setFeatures() {
        Dimension dimension = new Dimension(500, 400);
        this.setSize(dimension);
        this.setMinimumSize(dimension);
        this.setResizable(true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new GridLayout(1, 1));


        this.setVisible(true);
    }
}