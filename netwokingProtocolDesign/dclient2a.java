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
		byte[] buff = new byte[2000];
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
			pout.println("Hello Server, Msg from client!"); // Standard check
															// between client
															// and server

			len = sin.read(buff);
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
				pout.println(msg);
				System.out.print("Converted output " + "From:" + msg + " TO:" + convert(msg)+'\n');
				msg = convert(msg);// Converting from numbers to text for back
									// end

				if (msg.equals("searchPrice")) {
					msg = din.readLine(); // from srv" "Enter ticket id to
											// search "
					System.out.print("Info ::" + msg);
					msg = in.readLine(); // input from keyboard user input
											// ticket id
					pout.println(msg);

					len = sin.read(buff);
					msg = new String(buff, 0, len);
					System.out.print("---Search Price LIST---" + '\n' + msg);

				} else if (msg.equals("searchDate")) {
					msg = din.readLine(); // from srv" "Enter ticket id to
											// search "
					System.out.println("Info ::" + msg);
					msg = in.readLine(); // input from keyboard user input
											// ticket id
					pout.println(msg);
					/*
					 * msg = din.readLine(); // from srv" ticket found info
					 * System.out.println("Data ::" + msg);
					 */

					len = sin.read(buff);
					msg = new String(buff, 0, len);
					System.out.print("---Search Date LIST---" + '\n' + msg);
				} else if (msg.equals("searchDateTime")) {
					msg = din.readLine(); // from srv" "Enter ticket id to
											// search "
					System.out.println("Info ::" + msg);
					msg = in.readLine(); // input from keyboard user input
											// ticket id
					pout.println(msg);
					/*
					 * msg = din.readLine(); // from srv" ticket found info
					 * System.out.println("Data ::" + msg);
					 */

					len = sin.read(buff);
					msg = new String(buff, 0, len);
					System.out.print("---Search Date Time LIST---" + '\n' + msg);
				} else if (msg.equals("viewAllTicket")) {
					// msg = din.readLine(); // from srv" all users info
					// System.out.print("\nData ::" + msg);
					len = sin.read(buff);
					msg = new String(buff, 0, len);
					System.out.println(msg);

					try {
						Thread.sleep(1500); // 1000 milliseconds is one second.
					} catch (InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
				} else if (msg.equals("newTicket")) {
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
				} else if (msg.equals("BuyDateTicket")) {
					msg = din.readLine(); // from srv" "Enter ticket ID: "
					System.out.println("Info ::" + msg);
					msg = in.readLine(); // input from keyboard ticket ID
					pout.println(msg);
					msg = din.readLine(); // from srv" "Enter date: "
					System.out.println("Info ::" + msg);
					msg = in.readLine(); // input from keyboard date
					pout.println(msg);
				} else if (msg.equals("login")) {
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
					if (msg.equals("--, LP")){
					System.out.println("Error ::" + convert(msg));
					}
					else if (msg.equals("++")){
					System.out.println("Status ::" + convert(msg));	
					}
				} else if (msg.equals("viewAllUser")) {
					// Multi line Method
					len = sin.read(buff);
					msg = new String(buff, 0, len);
					System.out.print("---USER LIST---" + '\n' + msg);

				} else if (msg.equals("LO")) {
					msg = din.readLine(); // from srv" "Please come back again
											// soon: "
					System.out.println("Info ::" + msg);
					break;
				} else if (msg.equals("")) {
					msg = din.readLine();
					System.out.println("\nError ::" + convert(msg) + '\n');
					//String empty_entry = din.readLine();
				}
			} // end of while loop
		} catch (IOException x) {
			System.out.println("Problem encountered -> " + x);
		} // end of catch IO
	} // end of main

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
			output = "buyDateTImeTicket"; // BT
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

}// end of class
