import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class MulticastSocketClient2 {
    
    final static String INET_ADDR = "224.0.0.5";
    final static int port = 5678;

    public static void main(String[] args) throws UnknownHostException {
        InetAddress address = InetAddress.getByName(INET_ADDR);
        
        byte[] buf = new byte[256];
        
        try (MulticastSocket clientSocket = new MulticastSocket(port)){		//multicast socket to allow multiple clients
            
            //Joint the multicast group.
            clientSocket.joinGroup(address);
            while (true) {
                DatagramPacket dataPacket = new DatagramPacket(buf, buf.length);
                clientSocket.receive(dataPacket);

                String msg = new String(buf, 0, buf.length);
                System.out.println("Received Message : " + msg);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
