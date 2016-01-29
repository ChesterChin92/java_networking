
/** dclient0.java
 * compatible with dserver0.java
 * 
 *
 */

import java.net.*;
import java.io.*;




public class dclient0 {
	public static void main(String[] args) {
		byte[] buff = new byte[100];
		int len = 0;
		String message = null;
		String outbuff = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		//Establish Connection.
		try {
			Socket socket = new Socket("localhost", 2019);
			InputStream sin = socket.getInputStream();
			OutputStream sout = socket.getOutputStream();
			DataInputStream din = new DataInputStream(sin);
			PrintStream pout = new PrintStream(sout);

			while(true){
				String msg = din.readLine();		 //Read msg from server
				System.out.println( msg); //Print it	
				System.out.print("Command : "); //Ask user enter command

				String request = in.readLine(); //Accepts keystroke in client ..cin
				//System.out.println("Content" + request); ////Use this to study ways to handle empty entry

				//request.equals("null") is a bad way of checking
				String msg_to_server = (request.isEmpty() ? "Empty command":request); //Handles blank entry + shortcut way for if else statement
				//System.out.println("Msg To server" + msg_to_server); //Use this to study ways to handle empty entry

				pout.println(msg_to_server); //Send it to server

				msg =""; } } 
		catch (IOException x)
		{ 
			System.out.println("Problem encountered -> " + x); 
		} 
	} 
}
