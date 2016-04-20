

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;


@SuppressWarnings("serial")
public class DisconnectButton extends JButton {
    private User user;


    public DisconnectButton(User user) {
        super("Disconnect!");
        this.user = user;

        this.addActionListener(new DisconnectListener());
        this.setVisible(true);
    }


    public class DisconnectListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            user.disconnect();
            System.exit(1);
        }
    }
}