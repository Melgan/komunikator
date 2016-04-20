

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;



public class Client extends User {
    private Socket socket;


    public Client() {
        super();
    }

    @Override
    public Socket getSocket() {
        return socket;
    }

    @Override
    public void connect() {
        try {
            InetAddress locIP = InetAddress.getByName("192.168.0.101");
          //  socket = new Socket(9999, 0, locIP);
          //  socket = new Socket("localhost", 9999);       oryginalny
              socket = new Socket(locIP, 9999);

        } catch (UnknownHostException e) {
            System.err.println("The host not found! " + e);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Can't find connection! " + e);
            System.exit(1);
        }
    }


    @Override
    public void disconnect() {
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @Override
    public String toString() {
        return new String("Client");
    }
}