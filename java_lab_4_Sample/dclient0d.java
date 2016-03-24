package java_lab_4_Sample;

import java.net.*;
import java.io.*; 
import java.util.*; 

public class dclient0d {
   public static void main(String[] args) 
   {
    	  
         try {
			 Socket socket = new Socket("127.0.0.1", 2016); //Socket socket = new Socket("ip address", 2016);
			 			 
			 byte [] buff = new byte[500];	
      		 String msg;	
	  		 int len;					 			 
	  		 InputStream sin = socket.getInputStream();
             OutputStream sout = socket.getOutputStream();

	         msg = new String("hello, Good afternoon");
	         sout.write(msg.getBytes());
	     	 System.out.println("Msg sent:" + msg);                     
             
   	      	 len = sin.read(buff);
   	      	 msg = new String(buff, 0 , len);
			 System.out.println("Msg received:" + msg);                     
         }
         catch (IOException x ) {
            System.out.println("Problem encountered -> " + x );
         }
   	}
}





