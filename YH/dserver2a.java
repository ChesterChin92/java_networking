/** dserver2a
 *  work with dclient2a.java, 
 *  note UserRec2a.class must be in same folder as this file
 *           or
 *       class UserRec2a must be copied to this file
 */


// Type wrong soli. lazy modify liao
//package NetworkFinal;
package YH;
import java.net.*;
import java.io.*;
import java.util.*;

public class dserver2a {

   
   public static void main( String[] args ) 
   {
    Trains ticket = new Trains();
    Users user = new Users();
    //ticket.showTicket();

    //ticket.addTicket(7101, "Hexham", "Poland", 11);
    //ticket.addTicket(7102, "Hexham", "Malaysia", 10.50);
    //ticket.addTicket(7103, "Hexham", "London", 2);

    //ticket.showTicket();
	
	
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
     
   try {
         ServerSocket server = new ServerSocket(2016);
         System.out.println("Server: Socket created"); 
         Socket socket = server.accept();	
         System.out.println("Server:Connection accepted");             
    	 
		 OutputStream sout = socket.getOutputStream();
         InputStream sin = socket.getInputStream();
         DataInputStream din = new DataInputStream(sin);
	     PrintStream pout = new PrintStream(sout);

         pout.println("hello, Good morning");
         String msg = din.readLine();
		 System.out.println("Msg received:" + msg);
		 
		 
		while(true) 
		{
			pout.println("Please enter your request: login/viewAllUser/searchPrice/searchDate/searchDateTime/viewAllTicket/newTicket/newDateTicket/logout");
			msg = din.readLine();
			 System.out.println("Msg received:" + msg);
			if (msg.equals("searchPrice")) 
			{
				pout.println("Enter ticket id to search ");
	    		msg = din.readLine();
	    		//pout.println(ticket.searchPrice(Integer.parseInt(msg)));
				sout.write(ticket.searchPrice(Integer.parseInt(msg)).toString().getBytes());
			}
			else if (msg.equals("searchDate")) 
			{
				pout.println("Enter ticket id to search ");
	    		msg = din.readLine();
	    		//pout.println(ticket.searchDate(Integer.parseInt(msg)));	    		
				sout.write(ticket.searchDate(Integer.parseInt(msg)).toString().getBytes());
			}
			else if (msg.equals("searchDateTime")) 
			{
				pout.println("Enter ticket id to search ");
	    		msg = din.readLine();
	    		//pout.println(ticket.searchDateTime(Integer.parseInt(msg)));
				sout.write(ticket.searchDateTime(Integer.parseInt(msg)).toString().getBytes());
			}
			else if (msg.equals("viewAllTicket")) 
			{
				sout.write(ticket.showTicket().toString().getBytes());
				//pout.println(ticket.showTicket());
				
			} 
			else if (msg.equals("newTicket")) 
			{
				pout.println("Enter new ticket id: ");
	    		String l = din.readLine();
				pout.println("Enter new ticket depart location: ");
	    		String n = din.readLine();
	    		pout.println("Enter destination: ");
	    		String p = din.readLine();
	    		pout.println("Enter date: ");
	    		String q = din.readLine();
	    		pout.println("Enter time: ");
	    		String r = din.readLine();
	    		pout.println("Enter price: ");
	    		String s = din.readLine();
	    		ticket.addTicket(Integer.parseInt(l), n, p, q, r, Double.parseDouble(s));
			}
			else if(msg.equals("newDateTicket"))
			{								
	    		pout.println("Enter ticket ID: ");
	    		String x = din.readLine();
	    		pout.println("Enter ticket Date: ");
	    		String y = din.readLine();
	    		pout.println(ticket.addDateTicket(Integer.parseInt(x), y));
	    		
			}
			else if(msg.equals("login"))
			{
				pout.println("Enter user id: ");
				String a = din.readLine();
				pout.println("Enter password: ");
				String b = din.readLine();
				
				//Need to do some validation here before sending data to server. 
				
				if (a.equals("") || b.equals("")) {
					pout.println("Error in ID or Password, cannot enter empty entry.");
						
				}
				else{
					if (!a.equals("0.0")){
						pout.println(user.checkLogin(Integer.parseInt(a), b));	
					}
					else{
						pout.println("No User Found.");
					}
					
				}
				
				//String result = ;
				//pout.println(ticket.checkLogin(Integer.parseInt(a), b));
//				pout.println(user.checkLogin(Integer.parseInt(a), b));
			}
			else if (msg.equals("viewAllUser")) 
			{
				sout.write(user.showUser().toString().getBytes());
			} 
			else if (msg.equals("logout")) 
			{
				pout.println("Please come back again soon ");
				break;
			} 
			else 
			{
				pout.println("Unrecognised Input, please enter again "+ '\n');
			}
		}
   }
   catch (IOException x ) {
         System.out.println("Sockets problem: " + x );
   }
  }
}

