/** dserver2a
 *  work with dclient2a.java, 
 *  note UserRec2a.class must be in same folder as this file
 *           or
 *       class UserRec2a must be copied to this file
 */

// Type wrong soli. lazy modify liao
package netwokingProtocolDesign;

import java.net.*;
import java.io.*;
import java.util.*;

//import java_lab_5_Sample.dserver6.SocketThread;

public class dserver2a {

	public static void main(String[] args) {
		Trains ticket = new Trains();
		Users user = new Users();

		byte[] buff = new byte[2000]; //Original 500, increase to 2000 due to length of response is more than 500
		int len = 0;

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	    
		//Multi thread concept
		try {
	         ServerSocket server = new ServerSocket(2016);
	         System.out.println("Server: Socket created");
	         while (true) {
	         	Socket socket = server.accept();	
	         	System.out.println("Server:Connection accepted");             
				SocketThread t = new SocketThread(socket);
				t.start();    	 
	         }
	      }//end of try
	    catch (IOException x ) {
	         System.out.println("Sockets problem: " + x );
	      }//end of catch
		
		

	} // end of main

	 private static class SocketThread extends Thread {
	        private Socket socket;
	        public SocketThread( Socket s ) { 
	            socket = s; 
	        }
	        public void run() {
		      try {
		    	  Trains ticket = new Trains();
		  		Users user = new Users();

		  		byte[] buff = new byte[2000];
		  		int len = 0;

		  		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		    	  
		  	
		  			OutputStream sout = socket.getOutputStream();
					InputStream sin = socket.getInputStream();
					DataInputStream din = new DataInputStream(sin);
					PrintStream pout = new PrintStream(sout);

					pout.println("Hello client, msg from server.");
					
					
					String msg = din.readLine();
					System.out.println("Msg received:" + msg);
					// pout.println("Please enter your request:
					// login/viewAllUser/searchPrice/viewAllTicket/newTicket/logout");
					// sout.write(user.showUser().toString().getBytes());
					// /searchPrice/searchDate/searchDateTime/viewAllTicket/newTicket/newDateTicket/
					while (true) {	
					sout.write(
							"\n1.login \n2.viewAllUser \n3.searchPrice \n4.searchDate \n5.searchDateTime \n6.viewAllTicket \n7.newTicket \n8.buyDateTicket \n9.buyDateTimeTicket(Saver)\n10.logout"
									.toString().getBytes());
					msg = din.readLine();
					System.out.println("Msg received:" + msg);

					msg = convert(msg); // Convert from numbers to text or protocol

					if (msg.equals("searchPrice")) { //Search for Price
						
						//System.out.println("Msg received - Inside search Price"); //For debug purpose
						pout.println("Enter ticket id to search ");
						
						msg = din.readLine(); 
						System.out.println("Msg typed by user:" + msg);
						// pout.println(ticket.searchPrice(Integer.parseInt(msg)));
						sout.write(ticket.searchPrice(Integer.parseInt(msg)).toString().getBytes());
					} 
					else if (msg.equals("viewAllTicket")) { //View All Ticket

						sout.write(ticket.showTicket().toString().getBytes()); 
						System.out.println("\nMsg sent:" + ticket.showTicket().toString());
					}

					else if (msg.equals("searchDate")) { //Search for date
						
						pout.println("Enter ticket id to search :");
						msg = din.readLine();
						// pout.println(ticket.searchDate(Integer.parseInt(msg)));
						sout.write(ticket.searchDate(Integer.parseInt(msg)).toString().getBytes());
					} 
					else if (msg.equals("searchDateTime")) { //Search for date and time
						
						pout.println("Enter ticket id to search :");
						msg = din.readLine();
						// pout.println(ticket.searchDateTime(Integer.parseInt(msg)));
						sout.write(ticket.searchDateTime(Integer.parseInt(msg)).toString().getBytes());
					} 
					else if (msg.equals("newTicket")) { // New Ticket to database
						
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
					else if (msg.equals("buyDateTicket")) { //buy Ticket
						
						pout.println("Enter ticket ID: ");
						String x = din.readLine();
						pout.println("Enter ticket Date: ");
						String y = din.readLine();
						pout.println(ticket.addDateTicket(Integer.parseInt(x), y)); //Add and send response back to client XX or --, PD
					} 
					else if (msg.equals("buyDateTimeTicket")) { //buy Ticket Savers
						
						pout.println("Enter ticket ID: ");
						String x = din.readLine();
						
						pout.println("Enter ticket Date: ");
						String y = din.readLine();
						
						pout.println("Enter ticket Time: ");
						String z = din.readLine();
						
						pout.println(ticket.addDateTimeTicket(Integer.parseInt(x), y, z)); //Add and send response back to client XX or --, PT
					} 
					else if (msg.equals("login")) {
						pout.println("Enter user id: ");
						String a = din.readLine();
						pout.println("Enter password: ");
						String b = din.readLine();

						// Need to do some validation here before passing data to
						// user class.

						if (a.equals("") || b.equals("")) {
							pout.println("Error in ID or Password, cannot enter empty entry.");

						} else {
							// temporary measure fix later.
							if (isInteger(a)) {
								pout.println(user.checkLogin(Integer.parseInt(a), b));
							} else {
								pout.println("No User Found.");
							}

						}

						// String result = ;
						// pout.println(ticket.checkLogin(Integer.parseInt(a), b));
						// pout.println(user.checkLogin(Integer.parseInt(a), b));
					} 
					else if (msg.equals("viewAllUser")) { //View All User
						sout.write(user.showUser().toString().getBytes());
					} 
					else if (msg.equals("LO")) { //Logout
						pout.println("Please come back again soon ");
					
					} 
					else {
						pout.println("--, QE"); // QE, catch all nature
					}
				}//end of while
			  }// end of try
	      	  catch (IOException x ) {
	         	System.out.println("Sockets problem: " + x );
	      	  }// end of catch
		      
	        }//end of run
	   }//end of socket thread
	
	
	
	
	// A function to convert from user input to protocol code
	public static String convert(String input) {
		String output = "";
		// \n1.login \n2.viewAllUser \n3.searchPrice \n4.searchDate
		// \n5.searchDateTime \n6.viewAllTicket \n7.newTicket \n8.buyDateTicket
		// \n9.buyDateTimeTicket(Saver)\n10.logout
		switch (input) {
		// Main Menu
		case "1":
			output = "login"; // LG
			break;
		case "2":
			output = "viewAllUser"; // Not in protocol
			break;
		case "3":
			output = "searchPrice"; // CI
			break;
		case "4":
			output = "searchDate"; // WH
			break;
		case "5":
			output = "searchDateTime"; // WI
			break;
		case "6":
			output = "viewAllTicket"; // VT
			break;
		case "7":
			output = "newTicket"; // Not in protocol
			break;
		case "8":
			output = "buyDateTicket"; // BD
			break;
		case "9":
			output = "buyDateTimeTicket"; // BT
			break;
		case "10":
			output = "LO"; // LO
			break;
		// Success Messages
		case "++":
			output = "OK";
			break;
		// Error Messages
		case "--, LU":
			output = "Invalid user ID in login";
			break;
		case "--, LP":
			output = "Invalid user password in login";
			break;
		case "--, QN":
			output = "Unrecognised Input, please enter again ";
			break;
		case "--, TN":
			output = "Ticket ID not found";
			break;
		case "--, PS":
			output = "Ticket ID sold out";
			break;
		case "--, PD":
			output = "Ticket date not found";
			break;
		case "--, PT":
			output = "Tcked ID time not found";
			break;
		case "--, QE":
			output = "Unrecognised Input, please enter again ";
			break;
		default:
			output = "General Query Error."; // QE, catch all nature
			break;
		}
		return output;
	}// end of convert

	public static boolean isInteger(String s) {
		boolean isValidInteger = false;
		try {
			Integer.parseInt(s);

			// s is a valid integer

			isValidInteger = true;
		} catch (NumberFormatException ex) {
			// s is not an integer
		}

		return isValidInteger;
	}// end of isInteger
}
