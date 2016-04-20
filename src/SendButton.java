

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintStream;


import javax.swing.JButton;
import javax.swing.JTextPane;


@SuppressWarnings("serial")
public class SendButton extends JButton {
    private JTextPane incomingMessages;
    private JTextPane messageToSend;
    private User user;

    public SendButton(User user, JTextPane incomingMessages, JTextPane messageToSend) {
        super("Send!");
        this.user = user;
        this.incomingMessages = incomingMessages;
        this.messageToSend = messageToSend;

        this.addActionListener(new SendListener());
    }

    public class Write {
        private PrintStream out;


        public Write() {
            try {
                out = new PrintStream(new BufferedOutputStream(
                        user.getSocket().getOutputStream(), 1024), false);
            } catch (IOException e) {
                System.err.println(e);
            }
        }


        public void send(String message) {
            if (message != null) {
                out.println(message);
                out.flush();


                incomingMessages.setText(new String(incomingMessages.getText() + "\nMe: " + message));
            }
        }
    }


    public class SendListener implements ActionListener {
        private Write write = new Write();
        private String toSend;


        @Override
        public void actionPerformed(ActionEvent event) {
            toSend = messageToSend.getText();


            if (toSend != null || event.getActionCommand() == "\n") {
                write.send(toSend);
            }


            messageToSend.setText(new String(""));
        }
    }
}