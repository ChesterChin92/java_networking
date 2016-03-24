package java_lab_5_Sample;
/** dserver6
 *  work with dclient0.java
 */


import java.net.*;
import java.io.*;
import java.util.*;

public class dserver6 {
   
   public static void main( String[] args ) 
   {
      try {
         ServerSocket server = new ServerSocket(2016);
         System.out.println("Server: Socket created");
         while (true) {
         	Socket socket = server.accept();	
         	System.out.println("Server:Connection accepted");             
			SocketThread t = new SocketThread(socket);
			t.start();    	 
         }
      }
      catch (IOException x ) {
         System.out.println("Sockets problem: " + x );
      }
   }

   private static class SocketThread extends Thread {
        private Socket socket;
        public SocketThread( Socket s ) { 
            socket = s; 
        }
        public void run() {
	      try {
		 	OutputStream sout = socket.getOutputStream();
         	InputStream sin = socket.getInputStream();
         	DataInputStream din = new DataInputStream(sin);
	     	PrintStream pout = new PrintStream(sout);

         	pout.println("hello, \r\nGood morning");
         	String msg = din.readLine();
		 	System.out.println("Msg received:" + msg);             
		  }
      	  catch (IOException x ) {
         	System.out.println("Sockets problem: " + x );
      	  }
        }
   }

}

