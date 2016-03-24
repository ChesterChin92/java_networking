package java_lab_4;
/** dclient2a.java
 * compatible with dserver2a.java
 * 
 *
 */

import java.net.*;
import java.io.*;
import java.util.*;

public class dclient2a {
	public static void main(String[] args) {
		byte[] buff = new byte[500];
		int len = 0;
		String message = null;
		String outbuff = null;
		String msg;

		// Uses in for keyboard input
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		try {
			Socket socket = new Socket("localhost", 2016);

			InputStream sin = socket.getInputStream();
			OutputStream sout = socket.getOutputStream();

			// msg = new String("hello, Good afternoon");
			msg = in.readLine();
			sout.write(msg.getBytes());
			System.out.println("Msg sent:" + msg);

			len = sin.read(buff);
			msg = new String(buff, 0, len);
			System.out.println("Msg received:" + msg);

			while (true) {
				// len = sin.read(buff);
				// msg = new String(buff, 0 , len);
				// System.out.println("Msg received:" + msg);
				msg = in.readLine(); // input from keyboard
				sout.write(msg.getBytes());
				System.out.println("Msg sent:" + msg);
				// FindUsers
				if (msg.equals("findUser")) {
					sout.write(msg.getBytes());
					len = sin.read(buff);
					msg = new String(buff, 0, len);
					System.out.println("Msg received:" + msg);
					msg = in.readLine(); // input from keyboard user input name
					sout.write(msg.getBytes());
					len = sin.read(buff);
					msg = new String(buff, 0, len);
					// View Users
				} else if (msg.equals("viewUsers")) {
					len = sin.read(buff);
					msg = new String(buff, 0, len);
					System.out.println("Data received:" + msg);
					len = sin.read(buff);
					msg = new String(buff, 0, len);
					System.out.println("Msg received:" + msg);
					// NewUsers
				} else if (msg.equals("newUser")) {
					len = sin.read(buff);
					msg = new String(buff, 0, len);
					System.out.println("Msg received:" + msg);
					msg = in.readLine(); // input from keyboard user input name
					sout.write(msg.getBytes());
					len = sin.read(buff);
					msg = new String(buff, 0, len);
					System.out.println("Msg received:" + msg);
					msg = in.readLine(); // input from keyboard user input
					// password
					sout.write(msg.getBytes());
					// Logout
				} else if (msg.equals("logout")) {
					len = sin.read(buff);
					msg = new String(buff, 0, len);
					System.out.println("Msg received:" + msg);
					break;
				}
			}

		} catch (IOException x) {
			System.out.println("Problem encountered -> " + x);
		}
	}
}
