import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import backend.Commons;
import backend.DbHelper;
import model.IncomeModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;

public class Question1 extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldAmt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Question1 frame = new Question1();
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
	public Question1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 245, 220));
		panel.setForeground(new Color(245, 245, 220));
		panel.setBounds(0, 0, 434, 53);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("INCOME");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("DejaVu Serif", Font.BOLD | Font.ITALIC, 18));
		label.setBounds(22, 0, 155, 64);
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(0, 54, 434, 10);
		contentPane.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(192, 192, 192));
		panel_2.setBounds(0, 64, 434, 197);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel labelDate = new JLabel("Date");
		labelDate.setBounds(38, 23, 46, 14);
		panel_2.add(labelDate);
		
		String[] messageStrings={"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31",};
		JComboBox comboBoxDate = new JComboBox(messageStrings);
		comboBoxDate.setBounds(176, 22, 42, 20);
		panel_2.add(comboBoxDate);
		
		String[] messageStrings2={"01","02","03","04","05","06","07","08","09","10","11","12"};
		JComboBox comboBoxMonth = new JComboBox(messageStrings2);
		comboBoxMonth.setBounds(228, 22, 77, 20);
		panel_2.add(comboBoxMonth);
		
		String[] messageStrings3={"2017","2018","2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030","2031","2032","2033","2034","2035","2036","2037","2038","2039","2040","2041","2042","2043","2044","2045","2046","2047","2048","2049","2050"};
		JComboBox comboBoxYear = new JComboBox(messageStrings3);
		comboBoxYear.setBounds(318, 22, 61, 20);
		panel_2.add(comboBoxYear);
		
		
		JLabel labelCat = new JLabel("Category");
		labelCat.setBounds(37, 57, 89, 14);
		panel_2.add(labelCat);
		
		String[] messageStrings4={"Salary","Other"};
		JComboBox comboBoxCat = new JComboBox(messageStrings4);
		comboBoxCat.setBounds(176, 54, 129, 20);
		panel_2.add(comboBoxCat);
		
		JLabel labelAmt = new JLabel("Amount");
		labelAmt.setBounds(37, 87, 67, 14);
		panel_2.add(labelAmt);
		
		textFieldAmt = new JTextField();
		textFieldAmt.setColumns(10);
		textFieldAmt.setBounds(176, 87, 129, 20);
		panel_2.add(textFieldAmt);
		
		DbHelper dbHelper= new DbHelper();
		JButton buttonSubmit = new JButton("Submit");
		buttonSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int inid = dbHelper.getNextId("income", "inid");
				
				String incomeDateString = comboBoxYear.getSelectedItem().toString() + "-" + comboBoxMonth.getSelectedItem().toString() + "-" + comboBoxDate.getSelectedItem().toString();
				
				IncomeModel incomeModel = new IncomeModel();
				
				incomeModel.setInid(inid);
				incomeModel.setAmount(Integer.parseInt(textFieldAmt.getText()));
				incomeModel.setCategory(comboBoxCat.getSelectedItem().toString());
				incomeModel.setIncomeDate(incomeDateString);
				incomeModel.setUsername(Commons.currentLogin);
				
				if(dbHelper.saveIncome(incomeModel)) {
					JOptionPane.showMessageDialog(Question1.this, "Income saved.");
					Question2 frame=new Question2();
					frame.setVisible(true);
					
				} else {
					JOptionPane.showMessageDialog(Question1.this, "Failed.");
				}
			}
		});
		buttonSubmit.setBounds(156, 149, 89, 23);
		panel_2.add(buttonSubmit);
	}
}
