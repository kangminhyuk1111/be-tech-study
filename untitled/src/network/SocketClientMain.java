package network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketClientMain {

  public static void main(String[] args) {
    Socket socket = null;
    try {
      socket = new Socket("localhost", 9000);

      InputStream in = socket.getInputStream();
      OutputStream out = socket.getOutputStream();

      String message = "hello world!";

      out.write(message.getBytes());

      byte arr[] = new byte[100];
      in.read(arr);
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      try {
        socket.close();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
