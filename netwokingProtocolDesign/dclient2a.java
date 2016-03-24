/** dclient2a.java
 * compatible with dserver2a.java
 * 
 *
 */ 
// Type wrong soli. lazy modify liao
package netwokingProtocolDesign;

import java.net.*;
import java.io.*; 
import java.util.*;

import javax.swing.JOptionPane; 

public class dclient2a 
{
   public static void main(String[] args) 
   {
      byte [] buff = new byte[500];	
      int len = 0;					
      String message = null;	
	  String outbuff = null;	

	
	  BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
      try 
      {
    	  Socket socket = new Socket("localhost", 2016);
			
		  InputStream sin = socket.getInputStream();
          OutputStream sout = socket.getOutputStream();
          DataInputStream din = new DataInputStream(sin);
	      PrintStream pout = new PrintStream(sout);
	      pout.println("hello, Good afternoon");
    	  String msg = din.readLine();
	      System.out.println("Info ::" + msg);
			 			 					
		  while (true)	
		  {
			  msg = din.readLine();	// from srv: "Please enter your request"
			  System.out.print("Info ::" + msg+'\n');
			  System.out.print("Command ~");
			  
			  msg = in.readLine();	// input from keyboard
			  pout.println(msg);
			  
			  if (msg.equals("searchPrice")) 
			  {
				  msg = din.readLine();	// from srv" "Enter user name to search "
				  System.out.println("Info ::" + msg);
				  msg = in.readLine();	// input from keyboard user input name
				  pout.println(msg);
				  msg = din.readLine();	// from srv" user found info
				  System.out.println("Data ::" + msg);
			  }
			  else if (msg.equals("viewAllTicket")) 
			  {
				  msg = din.readLine();	// from srv" all users info
				  System.out.println("Data ::" + msg);
			  }
			  else if (msg.equals("newTicket")) 
			  {
				  msg = din.readLine();	// from srv" "Enter new ticket id: "
				  System.out.println("Info ::" + msg);
				  msg = in.readLine();	// input from keyboard ticket id
				  pout.println(msg);
				  msg = din.readLine();	// from srv" "Enter depart location:  "
				  System.out.println("Info ::" + msg);
				  msg = in.readLine();	// input from keyboard depart location
				  pout.println(msg);
				  msg = din.readLine();	// from srv" "Enter destination:  "
				  System.out.println("Info ::" + msg);
			      msg = in.readLine();	// input from keyboard destination
				  pout.println(msg);
				  msg = din.readLine();	// from srv" "Enter price:  "
				  System.out.println("Info ::" + msg);
				  msg = in.readLine();	// input from keyboard price
				  pout.println(msg);
				}
				else if(msg.equals("login"))
				{
					msg = din.readLine();	// from srv" "Enter user id: "
					System.out.print('\n'+"Info ::" + msg);
					msg = in.readLine();	// input from keyboard user id
					pout.println(msg);
					msg = din.readLine();	// from srv" "Enter password: "
					System.out.print("Info ::" + msg);
					msg = in.readLine();	// input from keyboard password
					pout.println(msg);
					
					msg = din.readLine();	// from srv" user found info or Error Msg
					System.out.println("Data ::" + msg);
					//break;
				}
				else if (msg.equals("viewAllUser")) 
				{
//					Standard method
//					msg = din.readLine();	// from srv" all users info
//					System.out.print("Data ::" + msg);
					
//				Multi line Method
					len = sin.read(buff);
					msg = new String(buff, 0, len);
					System.out.print("---USER LIST---"+ '\n' + msg);
				
				}
				else if (msg.equals("logout")) 
				{
					msg = din.readLine();	// from srv" "Please come back again soon: "
					System.out.println("Info ::" + msg);
					break;
				}
				else if (msg.equals("")){
					//Do something here
					 System.out.println("Empty Entry!" + msg);
					  msg = din.readLine();
					  System.out.println("Error ::" + msg+'\n');
					  String empty_entry = din.readLine();
				}
			}                    
		}
		catch (IOException x ) 
        {
        	System.out.println("Problem encountered -> " + x );
		}
   	}	
}





