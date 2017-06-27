package final_project;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class SnutWayHandler implements Runnable {
   protected Socket socket;

   public SnutWayHandler(Socket socket) {
      this.socket = socket;
   }

   protected DataInputStream dataIn;
   protected DataOutputStream dataOut;
   protected Thread ClientHandleListener;

   public synchronized void init() { // initialization, open stream and data exchange.
      if (ClientHandleListener == null) {
         try {
            dataIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            dataOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            
            ClientHandleListener = new Thread(this);
            ClientHandleListener.start();
            
         } catch (IOException ioe) {

         }
      }
   }

   public synchronized void stop() {    // thread is already run, then stop
      if (ClientHandleListener != null) {
         try {
            if (ClientHandleListener != Thread.currentThread()) {
               ClientHandleListener.interrupt();
            }

            ClientHandleListener = null;
            dataOut.close();
            
         } catch (IOException ioe) {

         }
      }
   }

   private Date date;

   @Override
   public void run() {
      try{
         while(!Thread.interrupted()){
            String msg = dataIn.readUTF();
            
            try{    // get order by occupation
               StringTokenizer st = new StringTokenizer(msg, "|");
            
               String address = st.nextToken();           

               String main = st.nextToken();
               String bread = st.nextToken();
               String sauce = st.nextToken();
               String ingredient = st.nextToken();
               String sides = st.nextToken();
               
               
               date = new Date();
               
               System.out.println();
               System.out.println();
               System.out.println("주문 시각 : " + date.toString());
    
               System.out.println("배달 주소 : " + address);
               System.out.println();
               
               System.out.println("==================== 주문 내역 ====================");               
               System.out.println();
               
               System.out.print("main 재료 : " + main +"\n"+ "빵 종류 : " + bread +"\n"+ "소스 : "+ sauce +" \n"+"채소 또는 과일(추가) : " + ingredient
                              +"\n"+ "side 메뉴 : " + sides);
               //소스 파트 수정 바
               System.out.println(" 을(를) 주문 하셨습니다.");
               
               
            }
            catch(NoSuchElementException noElements){
               System.out.println("주문이 누락되었습니다.");
               stop();
            }
         }
      }
      catch(EOFException eof){
         System.out.println("접속 종료.");
      }
      
      catch(IOException ioe){
         if(ClientHandleListener == Thread.currentThread()){            
            ioe.printStackTrace();
         }
      }
      
      finally{
         stop();
         
      }
      
      
      
   }

}