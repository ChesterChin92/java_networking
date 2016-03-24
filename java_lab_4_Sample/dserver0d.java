package java_lab_4_Sample;



//** dserver0d
// *  work with dclient0d.java
// *


import java.net.*;
import java.io.*;
import java.util.*;

public class dserver0d {
   
   public static void main( String[] args ) 
   {
     
      try {
         ServerSocket server = new ServerSocket(2016);
         System.out.println("Server: Socket created"); 
         Socket socket = server.accept();	
         System.out.println("Server:Connection accepted");             
    	 byte [ ] buff = new byte[500];	
    	 int len;
    	 
		 OutputStream sout = socket.getOutputStream();
         InputStream sin = socket.getInputStream();
         String msg = new String("hello, Good morning");
         sout.write(msg.getBytes());
         System.out.println("Msg sent:" + msg);             
		 len = sin.read(buff);
		 msg = new String(buff, 0, len);
		 System.out.println("Msg received:" + msg);             

      }
      catch (IOException x ) {
         System.out.println("Sockets problem: " + x );
      }
   }
}

