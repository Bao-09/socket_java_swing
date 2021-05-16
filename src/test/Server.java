package test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static DataOutputStream dout;
	public static DataInputStream din;
	public static void main(String[] args) throws Exception{
		// Khởi tạo đối tượng Server socket cổng 6543
		ServerSocket server_socket = new ServerSocket(6543);
		/// Khởi tạo Socket và chấp nhận kết nối từ đối tượng Socket Server.
		Socket socket = server_socket.accept();
		// Tạo luồng in dữ liệu trả ra cho client
		dout = new DataOutputStream(socket.getOutputStream());
		// Tạo luồng đọc dữ liệu vào từ client
		din = new DataInputStream(socket.getInputStream());
		// Vòng lặp nghe dữ liệu từ client
		Boolean status = true;
		while (status) {
			try {
				String str_in = din.readUTF();
				System.out.println("client says: " + str_in);
				// Trả về kết quả
				dout.writeUTF("Server Received Data:"+str_in);
				dout.flush();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				status = false;
				e1.printStackTrace();
			}
		}		
	}
}
