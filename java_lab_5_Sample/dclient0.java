package java_lab_5_Sample;
/** dclient0d.java
 * compatible with dserver0d.java
 * 
 *
 */   

import java.net.*;
import java.io.*; 
import java.util.*; 

public class dclient0 {
   public static void main(String[] args) 
   {
    	  
         try {
			 Socket socket = new Socket("localhost", 2016);
			
			 byte [] buff = new byte[100];	
      		 String msg;	
	  		 int len;					 			 
	  		 InputStream sin = socket.getInputStream();
             OutputStream sout = socket.getOutputStream();

	         msg = new String("hello, \r\nGood afternoon");
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





