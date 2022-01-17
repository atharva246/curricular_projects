import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import backend.DbHelper;
import model.AppModel;

public class SignUp extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUsername;
	private JTextField textFieldAge;
	private JTextField textFieldAddress;
	private JTextField textFieldOcc;
	private JTextField textFieldEmail;
	private JTextField textFieldPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
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
	public SignUp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 326);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 245, 220));
		panel.setBounds(0, 0, 434, 46);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblSignup = new JLabel("SignUp");
		lblSignup.setFont(new Font("DejaVu Serif", Font.BOLD | Font.ITALIC, 18));
		lblSignup.setBounds(10, 11, 97, 24);
		panel.add(lblSignup);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(192, 192, 192));
		panel_1.setBounds(0, 58, 438, 256);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("UserName:");
		label.setBounds(10, 14, 86, 14);
		panel_1.add(label);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setColumns(10);
		textFieldUsername.setBounds(106, 12, 86, 20);
		panel_1.add(textFieldUsername);
		
		JLabel label_1 = new JLabel("Age:");
		label_1.setBounds(31, 39, 50, 14);
		panel_1.add(label_1);
		
		textFieldAge = new JTextField();
		textFieldAge.setColumns(10);
		textFieldAge.setBounds(106, 37, 35, 20);
		panel_1.add(textFieldAge);
		
		JLabel label_2 = new JLabel("Gender:");
		label_2.setBounds(158, 39, 64, 14);
		panel_1.add(label_2);
		
		JRadioButton radioButtonMale = new JRadioButton("Male");
		radioButtonMale.setBounds(224, 35, 60, 23);
		panel_1.add(radioButtonMale);
		radioButtonMale.setSelected(true);
		
		JRadioButton radioButtonFemale = new JRadioButton("Female");
		radioButtonFemale.setBounds(285, 35, 77, 23);
		panel_1.add(radioButtonFemale);
		
		ButtonGroup group=new ButtonGroup();
		group.add(radioButtonMale);
		group.add(radioButtonFemale);
		
		JLabel label_3 = new JLabel("Address:");
		label_3.setBounds(21, 72, 89, 14);
		panel_1.add(label_3);
		
		textFieldAddress = new JTextField();
		textFieldAddress.setColumns(10);
		textFieldAddress.setBounds(105, 69, 321, 20);
		panel_1.add(textFieldAddress);
		
		JLabel label_4 = new JLabel("Occupation:");
		label_4.setBounds(10, 109, 100, 14);
		panel_1.add(label_4);
		
		textFieldOcc = new JTextField();
		textFieldOcc.setColumns(10);
		textFieldOcc.setBounds(106, 102, 120, 20);
		panel_1.add(textFieldOcc);
		
		JLabel label_5 = new JLabel("Email-ID:");
		label_5.setBounds(21, 144, 89, 14);
		panel_1.add(label_5);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(106, 142, 120, 20);
		panel_1.add(textFieldEmail);
		
		JButton button = new JButton("Submit");
		button.setBounds(175, 196, 89, 23);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DbHelper dbHelper= new DbHelper();
				
				int nextId = dbHelper.getNextId("app_user", "userid");
				
				AppModel appModel = new AppModel();
				
				appModel.setUserid(nextId);
				appModel.setUsername(textFieldUsername.getText());
				appModel.setPassword(textFieldPassword.getText());
				appModel.setAge(Integer.parseInt(textFieldAge.getText()));
				appModel.setOccupation(textFieldOcc.getText());
				if(radioButtonMale.isSelected()) {
					appModel.setGender("male");
				} else {
					appModel.setGender("female");
				}
				appModel.setEmailid(textFieldEmail.getText());
				appModel.setAddress(textFieldAddress.getText());
				
				if(dbHelper.saveUser(appModel)) {
					JOptionPane.showMessageDialog(SignUp.this, "Record saved.");
					new Example().setVisible(true);
					SignUp.this.dispose();
				} else {
					JOptionPane.showMessageDialog(SignUp.this, "Failed to complete this action.");
				}
			}
		});
		panel_1.add(button);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(232, 109, 94, 14);
		panel_1.add(lblPassword);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(308, 107, 118, 20);
		panel_1.add(textFieldPassword);
		textFieldPassword.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(0, 45, 434, 10);
		contentPane.add(panel_2);
	}
}
