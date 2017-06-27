package final_project;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SnutWayServer {

   public static void main(String[] args) throws IOException {
      
      
      if (args.length != 1) {
         throw new IllegalArgumentException("SnutWayServer port 번호 미입력.");
      }
      
      int port = Integer.parseInt(args[0]); //run configuration을 통한 포트 번호 전달.

      ServerSocket sWayServer = new ServerSocket(port);
      
      System.out.println("SnutWay 서버 on");

      while (true) {            // 여러명의 client 접속 처리.
         
         Socket clientServer = sWayServer.accept();
         System.out.println("IP Address : " + clientServer.getInetAddress() + "에서 접속!");

         SnutWayHandler SWHandler = new SnutWayHandler(clientServer);

         SWHandler.init();

      }
   }

}