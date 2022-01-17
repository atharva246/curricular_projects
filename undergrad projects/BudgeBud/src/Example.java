import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import backend.Commons;
import backend.DbHelper;

public class Example extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUserName;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Example frame = new Example();
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
	public Example() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 245, 220));
		panel.setBounds(0, 0, 434, 51);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("   BUDGEBUD");
		label.setForeground(new Color(0, 0, 0));
		label.setFont(new Font("Tempus Sans ITC", Font.BOLD | Font.ITALIC, 18));
		label.setBackground(Color.BLUE);
		label.setBounds(0, 0, 219, 39);
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(192, 192, 192));
		panel_1.setBounds(0, 62, 434, 199);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label_1 = new JLabel("UserName");
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("DejaVu Serif", Font.BOLD | Font.ITALIC, 12));
		label_1.setBounds(150, 34, 73, 39);
		panel_1.add(label_1);
		
		textFieldUserName = new JTextField();
		textFieldUserName.setColumns(10);
		textFieldUserName.setBounds(256, 44, 168, 20);
		panel_1.add(textFieldUserName);
		
		JLabel label_2 = new JLabel("Password");
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("DejaVu Serif", Font.BOLD | Font.ITALIC, 12));
		label_2.setBounds(150, 86, 66, 23);
		panel_1.add(label_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(256, 88, 168, 20);
		panel_1.add(passwordField);
		
		JButton button = new JButton("Login");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DbHelper dbHelper = new DbHelper();
				if(dbHelper.checkUser(textFieldUserName.getText(),passwordField.getText())) {
					JOptionPane.showMessageDialog(Example.this, "Welcome to BudgeBud");
					
					Commons.currentLogin = textFieldUserName.getText();
					
					Start frame = new Start();
					frame.setVisible(true);
					Example.this.dispose();
				} 
				else {
					JOptionPane.showMessageDialog(Example.this, "User Unavailable. Please try again");
				}
			}
		});
		button.setBounds(209, 139, 89, 23);
		panel_1.add(button);
		
		JButton button_1 = new JButton("Sign Up");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUp frame = new SignUp();
				frame.setVisible(true);
				Example.this.dispose();
			}
		});
		button_1.setBounds(335, 139, 89, 23);
		panel_1.add(button_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(0, 51, 434, 10);
		contentPane.add(panel_2);
		
		
	}
}
