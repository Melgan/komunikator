

import java.awt.Color;
import java.awt.GridLayout;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;


import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;



@SuppressWarnings("serial")
public class ConversationPanel extends JPanel {
    private JTextPane incomingMessages;
    private JScrollPane incoming;
    private JTextPane messageToSend;
    private User user;


    public ConversationPanel(User user) {
        super(new GridLayout(4, 1));


        this.user = user;


        initialize();
        setDetails();


        add(incoming);
        add(messageToSend);
        add(new SendButton(user, incomingMessages, messageToSend));
        add(new DisconnectButton(user));


        this.setVisible(true);


        new Read().start();
    }


    private void initialize() {
        incomingMessages = new JTextPane();
        incoming = new JScrollPane(incomingMessages);
        messageToSend = new JTextPane();
    }


    private void setDetails() {
        incomingMessages.setBackground(new Color(240, 240, 240));
        incomingMessages.setEditable(false);


        messageToSend.setBackground(new Color(255, 255, 255));


        incoming.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        incoming.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    }


    public class Read extends Thread {
        private DataInputStream in;
        private String message;
        private StringBuffer buffer;
        private boolean chatting = true;


        public Read() {
            try {
                in = new DataInputStream(new BufferedInputStream(
                        user.getSocket().getInputStream()));


            } catch (IOException e) {
                System.err.println(e);
            }
        }


        @SuppressWarnings("deprecation")
        private void receiveMessage() throws IOException {
            if ((message = in.readLine()) != null) {
                buffer = new StringBuffer(incomingMessages.getText()
                        + "\nCaller: " + message);


                incomingMessages.setText(new String(buffer));
            }
        }


        @Override
        public void run() {
            while (chatting) {
                try {
                    receiveMessage();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
    }
}