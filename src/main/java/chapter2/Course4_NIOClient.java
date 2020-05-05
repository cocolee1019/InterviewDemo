package chapter2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Course4_NIOClient {

	
	public static void main(String[] args) throws IOException {
		
		PrintWriter writer = null;
		BufferedReader reader = null;
		
		try(Socket client = new Socket();) {
			client.connect(new InetSocketAddress("localhost", 8000));
			writer = new PrintWriter(client.getOutputStream(), true);
			writer.println("Hello!");
			writer.flush();
			reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			System.out.println("from server: " + reader.readLine());
		}
	}
}
