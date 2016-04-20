

import java.net.Socket;


import javax.swing.SwingUtilities;


public abstract class User {
    public User() {
        connect();

        Runnable chatService = new Runnable() {
            @Override
            public void run() {
                new MainFrame(User.this);
            }
        };

        SwingUtilities.invokeLater(chatService);
    }

    abstract public void connect();
    abstract public void disconnect();
    abstract public Socket getSocket();
    abstract public String toString();
}