package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends JFrame {

	private JPanel contentPane;
	private JTextField txtNoiDung;
	public static DataOutputStream dout;
	public static DataInputStream din;
	public static Socket client = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client frame = new Client();
					try {
						client = new Socket("localhost",6543);
						dout = new DataOutputStream(client.getOutputStream());
					} catch (UnknownHostException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Client() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNiDungGi = new JLabel("N\u1ED9i dung g\u1EEDi \u0111i");
		GridBagConstraints gbc_lblNiDungGi = new GridBagConstraints();
		gbc_lblNiDungGi.insets = new Insets(0, 0, 5, 5);
		gbc_lblNiDungGi.anchor = GridBagConstraints.EAST;
		gbc_lblNiDungGi.gridx = 0;
		gbc_lblNiDungGi.gridy = 0;
		contentPane.add(lblNiDungGi, gbc_lblNiDungGi);
		
		txtNoiDung = new JTextField();
		GridBagConstraints gbc_txtNoiDung = new GridBagConstraints();
		gbc_txtNoiDung.insets = new Insets(0, 0, 5, 0);
		gbc_txtNoiDung.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNoiDung.gridx = 1;
		gbc_txtNoiDung.gridy = 0;
		contentPane.add(txtNoiDung, gbc_txtNoiDung);
		txtNoiDung.setColumns(10);
		
		JButton btnGui = new JButton("G\u1EEDi ");
		JLabel lbKq = new JLabel("K\u1EBFt qu\u1EA3 tr\u1EA3 v\u1EC1");
		GridBagConstraints gbc_lbKq = new GridBagConstraints();
		gbc_lbKq.gridx = 1;
		gbc_lbKq.gridy = 2;
		contentPane.add(lbKq, gbc_lbKq);
		
		// Tạo luồng gửi dữ liệu
		btnGui.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					String message = txtNoiDung.getText().toString();
					dout.writeUTF(message);
					din = new DataInputStream(client.getInputStream());
					String output = din.readUTF();
					lbKq.setText(output);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnGui = new GridBagConstraints();
		gbc_btnGui.insets = new Insets(0, 0, 5, 0);
		gbc_btnGui.gridx = 1;
		gbc_btnGui.gridy = 1;
		contentPane.add(btnGui, gbc_btnGui);
		
	}

}
