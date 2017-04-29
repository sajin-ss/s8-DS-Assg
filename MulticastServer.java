import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MulticastServer {
    
    final static String INET_ADDR = "224.0.0.5";
    final static int port = 5678;

    public static void main(String[] args) throws UnknownHostException, InterruptedException{
        InetAddress addr = InetAddress.getByName(INET_ADDR);	//address to bind to
     
        try (DatagramSocket serverSocket = new DatagramSocket()) {
            for (int i = 0; i < 5; i++) {
                String msg = "msg # " + i;

                DatagramPacket dataPacket = new DatagramPacket(msg.getBytes(), msg.getBytes().length, addr, port);	//data packet to send
                serverSocket.send(dataPacket);
     
                System.out.println("Message sent :-> " + msg);
                Thread.sleep(500);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
