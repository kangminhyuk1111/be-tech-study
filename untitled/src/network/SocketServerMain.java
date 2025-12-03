package network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerMain {

  public static void main(String[] args) {
    Socket socket = null;

    try {
      ServerSocket serverSocket = new ServerSocket(9000);
      socket = serverSocket.accept();

      InputStream in = socket.getInputStream();
      OutputStream out = socket.getOutputStream();

      byte[] arr = new byte[100];
      int readBytes = in.read(arr);

      String receivedMessage = new String(arr, 0, readBytes);
      System.out.println("받은 메시지: " + receivedMessage);

      String responseMessage = receivedMessage + " - from server";

      out.write(responseMessage.getBytes());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
