/**
 * Created by Melgan on 2016-04-20.
 */

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
abstract class Runnable extends Server {

    private void run() {
        try {
            while (true) new Thread(new ConnectionHandler(serverSocket.accept()) {
                @Override
                public void run() {

                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private abstract class ConnectionHandler implements java.lang.Runnable {
        public ConnectionHandler(Socket accept) {
        }
    }
}