import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class Start extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start frame = new Start();
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
	public Start() {
		getContentPane().setBackground(new Color(192, 192, 192));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 443, 372);
		getContentPane().setLayout(null);
		
		
		JButton btnNewButton_4 = new JButton("Set Budget");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Questions frame = new Questions();
				frame.setVisible(true);
			}
		});
		btnNewButton_4.setBounds(119, 117, 188, 34);
		getContentPane().add(btnNewButton_4);
		JButton button = new JButton("Manage Expense");
		button.setBounds(119, 197, 188, 34);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Spending frame = new Spending();
				frame.setVisible(true);
			}
		});
		getContentPane().add(button);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 245, 220));
		panel.setBounds(0, 0, 427, 62);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblLetsGetStarted = new JLabel("Lets Get Started");
		lblLetsGetStarted.setFont(new Font("DejaVu Serif", Font.BOLD | Font.ITALIC, 18));
		lblLetsGetStarted.setBounds(10, 11, 170, 40);
		panel.add(lblLetsGetStarted);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 62, 427, 10);
		getContentPane().add(panel_1);
	}
}
