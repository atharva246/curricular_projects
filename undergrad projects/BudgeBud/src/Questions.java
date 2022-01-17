import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import backend.Commons;
import backend.DbHelper;
import model.IncomeModel;
import model.ResponseModel;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

public class Questions extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	DbHelper dbHelper;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Questions frame = new Questions();
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
	public Questions() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 447, 515);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 245, 220));
		panel.setBounds(0, 0, 434, 64);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblEnterApproxPercentage = new JLabel("  Enter approx percentage you would spend");
		lblEnterApproxPercentage.setFont(new Font("DejaVu Serif", Font.BOLD | Font.ITALIC, 12));
		lblEnterApproxPercentage.setBounds(0, 23, 422, 15);
		panel.add(lblEnterApproxPercentage);
		
		JLabel lblOnTheFollowing = new JLabel("for the following questions");
		lblOnTheFollowing.setFont(new Font("DejaVu Serif", Font.BOLD | Font.ITALIC, 12));
		lblOnTheFollowing.setBounds(10, 49, 412, 15);
		panel.add(lblOnTheFollowing);
		
		JPanel panelQuestion = new JPanel();
		panelQuestion.setBackground(new Color(192, 192, 192));
		panelQuestion.setBounds(0, 75, 434, 403);
		contentPane.add(panelQuestion);
		panelQuestion.setLayout(null);
		
		JLabel lblQHowOften = new JLabel("Q1. How often do you rely on private transport?");
		lblQHowOften.setBounds(10, 34, 377, 14);
		panelQuestion.add(lblQHowOften);
		
		textField = new JTextField();
		textField.setBounds(10, 59, 86, 20);
		panelQuestion.add(textField);
		textField.setColumns(10);
		
		JLabel lblQHowOften_1 = new JLabel("Q2. How often do you eat outside?");
		lblQHowOften_1.setBounds(10, 90, 325, 14);
		panelQuestion.add(lblQHowOften_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(10, 115, 86, 20);
		panelQuestion.add(textField_1);
		
		JLabel lblQHowOften_2 = new JLabel("Q3. How often do you visit the theatres?");
		lblQHowOften_2.setBounds(10, 146, 325, 14);
		panelQuestion.add(lblQHowOften_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(10, 171, 86, 20);
		panelQuestion.add(textField_2);
		
		JLabel lblQHowOften_3 = new JLabel("Q4. How often do you vacay?");
		lblQHowOften_3.setBounds(10, 202, 251, 14);
		panelQuestion.add(lblQHowOften_3);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(10, 227, 86, 20);
		panelQuestion.add(textField_3);
		
		JLabel lblQHowOften_4 = new JLabel("Q5. How often do you go shopping?");
		lblQHowOften_4.setBounds(10, 258, 251, 14);
		panelQuestion.add(lblQHowOften_4);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(10, 283, 86, 20);
		panelQuestion.add(textField_4);
		
		JLabel lblQHowMuch = new JLabel("Q6. How much do you think you spend on paying your bills?");
		lblQHowMuch.setBounds(0, 315, 443, 14);
		panelQuestion.add(lblQHowMuch);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(10, 339, 86, 20);
		panelQuestion.add(textField_5);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ResponseModel responseModel = new ResponseModel();
			    dbHelper= new DbHelper();
				int iid = dbHelper.getNextId("questions", "id");
				responseModel.setId(iid);
				responseModel.setUsername(Commons.currentLogin);
				
				responseModel.setCategory("Fuel");
				responseModel.setPercentage(Float.parseFloat(textField.getText()));
				int iid1 =dbHelper.getNextId("questions", "id");
				responseModel.setId(iid1);
				dbHelper.saveResponse(responseModel);
				
				responseModel.setCategory("Food");
				responseModel.setPercentage(Float.parseFloat(textField_1.getText()));
				int iid2 =dbHelper.getNextId("questions", "id");
				responseModel.setId(iid2);
				dbHelper.saveResponse(responseModel);
				
				responseModel.setCategory("Entertainment");
				responseModel.setPercentage(Float.parseFloat(textField_2.getText()));
				int iid3 =dbHelper.getNextId("questions", "id");
				responseModel.setId(iid3);
				dbHelper.saveResponse(responseModel);
				
				responseModel.setCategory("Holiday");
				responseModel.setPercentage(Float.parseFloat(textField_3.getText()));
				int iid4 =dbHelper.getNextId("questions", "id");
				responseModel.setId(iid4);
				dbHelper.saveResponse(responseModel);
				
				responseModel.setCategory("Shopping");
				responseModel.setPercentage(Float.parseFloat(textField_4.getText()));
				int iid5 =dbHelper.getNextId("questions", "id");
				responseModel.setId(iid5);
				dbHelper.saveResponse(responseModel);
				
				responseModel.setCategory("Bills");
				responseModel.setPercentage(Float.parseFloat(textField_5.getText()));
				int iid6 =dbHelper.getNextId("questions", "id");
				responseModel.setId(iid6);
				dbHelper.saveResponse(responseModel);
				
				JOptionPane.showMessageDialog(Questions.this, "record saved.");
				
				Question1 frame=new Question1();
				frame.setVisible(true);
				
			
			}
		});
		btnSave.setBounds(65, 370, 89, 23);
		panelQuestion.add(btnSave);
		
		JButton btnReports = new JButton("Report");
		btnReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PieChart_AWTBudget.main(null);
			}
		});
		btnReports.setBounds(214, 370, 89, 23);
		panelQuestion.add(btnReports);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(0, 63, 431, 10);
		contentPane.add(panel_2);
		
				
	}
}
