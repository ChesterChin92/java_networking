package java_lab_4;
/** dserver2a
 *  work with dclient2a.java, 
 *  note UserRec2a.class must be in same folder as this file
 *           or
 *       class UserRec2a must be copied to this file
 */

import java.net.*;
import java.io.*;
import java.util.*;

public class dserver2a {

	public static void main(String[] args) {

		UserRec2a userdb = new UserRec2a();
		BookRec3 bookdb = new BookRec3();

		BookRec3 myfirstHouse = new BookRec3();
		
		myfirstHouse.addBook(1000, "Alex","Emo" , "Depression");
				
		bookdb.showbooks();
		
		bookdb.addBook(1, "KDU, PG", "K-title", "education");
		bookdb.addBook(2, "KDU, PG", "K-title", "education");
		bookdb.addBook(3, "KDU, PG", "K-title", "education");
		
		
		bookdb.showbooks();

		userdb.showRecs();

		userdb.readUsers();
		userdb.addRec(888, "FredNurg", "fnurg4");
		userdb.addRec(555, "JohnPolan", "jpolan8");
		userdb.addRec(333, "RonMulan", "rmulan5");

		userdb.showRecs();

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		try {
			ServerSocket server = new ServerSocket(2016);
			System.out.println("Server: Socket created");
			Socket socket = server.accept();
			System.out.println("Server:Connection accepted");

			byte[] buff = new byte[500];
			int len;

			/**
			 * Setup sout as output stream.
			 */
			OutputStream sout = socket.getOutputStream();
			InputStream sin = socket.getInputStream();

			/**
			 * Define msg and use sout.write to sent the message. Notice the
			 * getBytes() converts string messages to bytes.
			 */
			String msg = new String("hello, Good morning");
			sout.write(msg.getBytes());

			System.out.println("Msg sent:" + msg);
			len = sin.read(buff);
			msg = new String(buff, 0, len);
			System.out.println("Msg received:" + msg);

			while (true) {
				sout.write("Please enter your request: findUser/viewUsers/newUser/logout".getBytes());
				len = sin.read(buff);
				msg = new String(buff, 0, len);
				System.out.println("Msg received:" + msg);
				if (msg.equals("findUser")) {
					sout.write("Enter user name to search ".getBytes());
					len = sin.read(buff);
					msg = new String(buff, 0, len);
					sout.write(userdb.findPassword(msg).getBytes());
				} else if (msg.equals("viewUsers")) {
					sout.write(userdb.showRecs().getBytes());

				} else if (msg.equals("newUser")) {
					sout.write("Enter new user name: ".getBytes());
					len = sin.read(buff);
					msg = new String(buff, 0, len);
					String n = msg;

					sout.write("Enter password: ".getBytes());
					len = sin.read(buff);
					msg = new String(buff, 0, len);
					String p = msg;
					userdb.addRec(888, n, p);
				} else if (msg.equals("logout")) {
					sout.write("Please come back again soon ".getBytes());
					userdb.saveUser();
					break;
				} else {
					sout.write("Unrecognised Input, please enter again ".getBytes());
				}
			}

		} catch (IOException x) {
			System.out.println("Sockets problem: " + x);
		}
	}
}
