package java_lab_3;
/** dclient2a.java
 * compatible with dserver2a.java
 * 
 *
 */   

import java.net.*;
import java.io.*; 
import java.util.*; 

public class dclient2a {
   public static void main(String[] args) 
   {
      byte [] buff = new byte[100];	
      int len = 0;					
      String message = null;	
	  String outbuff = null;	

	
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
         		try {
			Socket socket = new Socket("localhost", 2016);
			
			InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();
            DataInputStream din = new DataInputStream(sin);
			PrintStream pout = new PrintStream(sout);
	         pout.println("hello, Good afternoon");
    	     String msg = din.readLine();
			 System.out.println("Msg received:" + msg);
			 
	while (true)	{
		msg = din.readLine();	// from srv: "Please enter your request"
		System.out.println("Msg received:" + msg);
		msg = in.readLine();	// input from keyboard
        pout.println(msg);
		if (msg.equals("findUser")) {
			msg = din.readLine();	// from srv" "Enter user name to search "
			System.out.println("Msg received:" + msg);
			msg = in.readLine();	// input from keyboard user input name
			pout.println(msg);
			msg = din.readLine();	// from srv" user found info
			System.out.println("Data received:" + msg);
		} else if (msg.equals("viewUsers")) {
			msg = din.readLine();	// from srv" all users info
			System.out.println("Data received:" + msg);
		} else if (msg.equals("newUser")) {
			msg = din.readLine();	// from srv" "Enter new user name: "
			System.out.println("Msg received:" + msg);
			msg = in.readLine();	// input from keyboard user input name
			pout.println(msg);
			msg = din.readLine();	// from srv" "Enter password:  "
			System.out.println("Msg received:" + msg);
			msg = in.readLine();	// input from keyboard user input password
			pout.println(msg);
		} else if (msg.equals("logout")) {
			msg = din.readLine();	// from srv" "Please come back again soon: "
			System.out.println("Msg received:" + msg);
			break;
		}
	}
                      
    		}
    		catch (IOException x ) {
            	System.out.println("Problem encountered -> " + x );
    		}
   	}
}





