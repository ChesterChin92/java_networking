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

public class dclient2a {
	public static void main(String[] args) {
		byte[] buff = new byte[2000]; //Original 500, increase to 2000 due to length of response is more than 500
		int len = 0;
		boolean loginStatus = false;
		
		String message = null; //Not used, kept for debug
		String outbuff = null; //Not used, kept for debug
		String tempHolder = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		try {
			Socket socket = new Socket("localhost", 2016);

			InputStream sin = socket.getInputStream();
			OutputStream sout = socket.getOutputStream();
			DataInputStream din = new DataInputStream(sin);
			PrintStream pout = new PrintStream(sout);
			pout.println("Hello Server, Msg from client!"); // Standard check
															// between client
															// and server

			len = sin.read(buff); //Response from server 
			String msg = new String(buff, 0, len);
			System.out.println("Info ::" + msg);

			while (true) {
				// Introduce a delay to give some buffer time for server, solves
				// msg off sync issues
				try {
					Thread.sleep(1000); // 1000 milliseconds is one second.
				} catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
				}

				len = sin.read(buff); // Read From server
				msg = new String(buff, 0, len);
				System.out.print("\nMain Menu -" + msg + '\n');
				System.out.print("Command ~");
				
				msg = in.readLine(); // input from keyboard
				
				//Add some logic here, if session fail, do not send msg to server.
				if (sessionChk(convert(msg),loginStatus) == false){
					//Do not send 
					tempHolder = msg;
					pout.println("NoSession");
					len = sin.read(buff); // Read From server
					msg = new String(buff, 0, len);
					System.out.print("Info ::" + convert(msg)+'\n'); //used for debug server response, right now it is suppressed
					msg = tempHolder;
				}
				else if (sessionChk(convert(msg),loginStatus) == true){
					pout.println(msg);	
				}
				
				
				System.out.print("Converted output " + "From:" + msg + " TO:" + convert(msg) + '\n');
				msg = convert(msg);// Converting from numbers to text for back
									// end
			
				
				
				if (msg.equals("searchPrice")) { //Search Price
					msg = din.readLine(); // from srv" "Enter ticket id to
											// search "
					System.out.print("Info ::" + msg);
					
					msg = in.readLine(); // input from keyboard user input
											// ticket id
					
					pout.println(msg);

					//Add validation and handling for errors.
					
					len = sin.read(buff);
					msg = new String(buff, 0, len);
					System.out.print("---Search Price LIST---" + '\n' + msg);

				} 
				else if (msg.equals("searchDate")) { //Search for date
					msg = din.readLine(); // from srv" "Enter ticket id to
											// search "
					System.out.print("Info ::" + msg);
					
					msg = in.readLine(); // input from keyboard user input
											// ticket id
					pout.println(msg);
					
					len = sin.read(buff);  //Response from server
					msg = new String(buff, 0, len);
					System.out.print("---Search Date LIST---" + '\n' + msg);
				} 
				else if (msg.equals("searchDateTime")) { //Search for date time
					msg = din.readLine(); // from srv" "Enter ticket id to
											// search "
					System.out.print("Info ::" + msg);
					
					msg = in.readLine(); // input from keyboard user input
											// ticket id
					pout.println(msg);
					
					len = sin.read(buff);
					msg = new String(buff, 0, len);
					System.out.print("---Search Date Time LIST---" + '\n' + msg);
					
				} 
				else if (msg.equals("viewAllTicket")) { //show all ticket
					
					len = sin.read(buff); //Response from server
					msg = new String(buff, 0, len);
					System.out.println(msg);

					
					try { //Delay before going back to menu
						Thread.sleep(1000); // 1000 milliseconds is one second.
					} catch (InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
				} 
				else if (msg.equals("newTicket") && (loginStatus == true)) { //Add new ticket 
					
					//TODO implement validation if possible
					msg = din.readLine(); // from srv" "Enter new ticket id: "
					System.out.println("Info ::" + msg);
					
					msg = in.readLine(); // input from keyboard ticket id
					pout.println(msg);

					msg = din.readLine(); // from srv" "Enter depart location: "
					System.out.println("Info ::" + msg);
					
					msg = in.readLine(); // input from keyboard depart location
					pout.println(msg);

					msg = din.readLine(); // from srv" "Enter destination: "
					System.out.println("Info ::" + msg);
					
					msg = in.readLine(); // input from keyboard destination
					pout.println(msg);

					msg = din.readLine(); // from srv" "Enter ticket date: "
					System.out.println("Info ::" + msg);
					
					msg = in.readLine(); // input from keyboard ticket date
					pout.println(msg);

					msg = din.readLine(); // from srv" "Enter new ticket time: "
					System.out.println("Info ::" + msg);
					
					msg = in.readLine(); // input from keyboard ticket time
					pout.println(msg);

					msg = din.readLine(); // from srv" "Enter price: "
					System.out.println("Info ::" + msg);
					
					msg = in.readLine(); // input from keyboard price
					pout.println(msg);
				} 
				else if (msg.equals("buyDateTicket")&& (loginStatus == true)) { //buy normal ticket
					
					msg = din.readLine(); // from srv" "Enter ticket ID: "
					System.out.print("Info ::" + msg);
					
					msg = in.readLine(); // input from keyboard ticket ID
					pout.println(msg);
					
					msg = din.readLine(); // from srv" "Enter date: "
					System.out.print("Info ::" + msg);
					
					msg = in.readLine(); // input from keyboard date
					pout.println(msg);
					
					
					msg = din.readLine(); // from server XX or --, PD
					
					if (msg.equals("--, PD")){
						System.out.print("Error ::" + convert(msg));	
					}
					else{
						System.out.print("Info :: Ticket number :" + msg.substring(4));
					}
					
				} 
				else if (msg.equals("buyDateTimeTicket")  && (loginStatus == true)) { // buy Saver ticket
					msg = din.readLine(); // from srv" "Enter ticket ID: "
					System.out.print("Info ::" + msg);
					
					msg = in.readLine(); // input from keyboard ticket ID
					pout.println(msg);
					
					msg = din.readLine(); // from srv" "Enter date: "
					System.out.print("Info ::" + msg);
					
					msg = in.readLine(); // input from keyboard date
					pout.println(msg);
					
					msg = din.readLine(); // from srv" "Enter time: "
					System.out.print("Info ::" + msg);
					
					msg = in.readLine(); // input from keyboard date
					pout.println(msg);
					
					msg = din.readLine(); // from server XX or --, PD
					
					if (msg.equals("--, PT")){
						System.out.print("Error ::" + convert(msg));	
					}
					else{
						System.out.print("Info :: Ticket number :" + msg.substring(4) + '\n');
					}
				}
				else if (msg.equals("login") && (loginStatus == false)) { //Login
					msg = din.readLine(); // from srv" "Enter user id: "
					System.out.print('\n' + "Info ::" + msg);
					msg = in.readLine(); // input from keyboard user id
					pout.println(msg);
					msg = din.readLine(); // from srv" "Enter password: "

					System.out.print("Info ::" + msg);
					msg = in.readLine(); // input from keyboard password
					pout.println(msg);

					msg = din.readLine(); // from srv" user found info or Error
											// Msg
					if (msg.equals("--, LP")) {
						System.out.println("Error ::" + convert(msg));
					}
					if (msg.equals("--, LU")) {
						System.out.println("Error ::" + convert(msg));
					} else if (msg.equals("++")) {
						System.out.println("Status ::" + convert(msg));
						loginStatus = true;// Implement a flag here.
					}
				} else if (msg.equals("viewAllUser")) { //View all user
					len = sin.read(buff);
					msg = new String(buff, 0, len);
					System.out.print("---USER LIST---" + '\n' + msg);

				} else if (msg.equals("LO") && (loginStatus == false)) {
					msg = din.readLine(); // from srvPlease come back again
											// soon:
					loginStatus = false;
					System.out.println("Info ::" + msg);
					
				} else if (msg.equals("--, QE")) {
					msg = din.readLine();
					System.out.println("\nError ::" + convert(msg) + '\n');
					// String empty_entry = din.readLine();
				} else{
					
					
					if (msg.equals("login") && loginStatus == true){
						System.out.println("You have already logged in.");	
					}
					else if (msg.equals("buyDateTicket") && loginStatus == false){
						System.out.println("You need to login first.");	
					}
					
					else if (msg.equals("buyDateTimeTicket")  && (loginStatus == false)){
						System.out.println("You need to login first.");	
					}
					else if (msg.equals("newTicket") && (loginStatus == false)) {
						System.out.println("You need to login first.");	
					}
					else{
						System.out.println("\nFatal Error"+'\n');	
					}
					
				} 
			} // end of while loop
		} catch (IOException x) {
			System.out.println("Problem encountered -> " + x);
		} // end of catch IO
	} // end of main

	
	public static boolean sessionChk(String input, boolean loginStatus){
		String output = "";
		if (input.equals("login") && loginStatus == true)
		{
			return false;
		}
		if (input.equals("newTicket") && loginStatus == false)
		{
			return false;
		}
		if (input.equals("buyDateTicket") && loginStatus == false)
		{
			return false;
		}
		if (input.equals("buyDateTimeTicket") && loginStatus == false)
		{
			return false;
		}
		else{
			return true;
		}
		
	}
	
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
		case "--, LN":
			output = "No Session";
			break;
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
		case "--, PD": //Used in buy ticket 
			output = "Ticket date not found";
			break;
		case "--, PT": //Used in but ticket (Saver)
			output = "Ticked time not found";
			break;
		case "--, QE":
			output = "Unrecognised Input, please enter again ";
			break;
		default:
			output = "--, QE"; // QE, catch all nature
			break;
		}//end of switch(input)
		return output;
	}// end of convert

}// end of class
