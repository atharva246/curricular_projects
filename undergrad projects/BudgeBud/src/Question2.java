import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import backend.Commons;
import backend.DbHelper;
import model.ExpenseModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class Question2 extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldAmt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Question2 frame = new Question2();
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
	public Question2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 245, 220));
		panel.setBounds(0, 0, 434, 54);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblExpense = new JLabel("EXPENSE");
		lblExpense.setForeground(Color.BLACK);
		lblExpense.setFont(new Font("DejaVu Serif", Font.BOLD | Font.ITALIC, 18));
		lblExpense.setBounds(12, 12, 155, 64);
		panel.add(lblExpense);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(0, 55, 434, 10);
		contentPane.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(192, 192, 192));
		panel_2.setBounds(0, 65, 434, 196);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel labelDate = new JLabel("Date");
		labelDate.setBounds(46, 25, 46, 14);
		panel_2.add(labelDate);
		
		String[] messageStrings={"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31",};
		JComboBox comboBoxDate = new JComboBox(messageStrings);
		comboBoxDate.setBounds(185, 22, 42, 23);
		panel_2.add(comboBoxDate);
		
		String[] messageStrings1={"01","02","03","04","05","06","07","08","09","10","11","12"};
		JComboBox comboBoxMonth = new JComboBox(messageStrings1);
		comboBoxMonth.setBounds(237, 22, 75, 23);
		panel_2.add(comboBoxMonth);
		
		String[] messageStrings3={"2017","2018","2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030","2031","2032","2033","2034","2035","2036","2037","2038","2039","2040","2041","2042","2043","2044","2045","2046","2047","2048","2049","2050"};
		JComboBox comboBoxYear = new JComboBox(messageStrings3);
		comboBoxYear.setBounds(322, 21, 67, 23);
		panel_2.add(comboBoxYear);
		
		JLabel labelCat = new JLabel("Category");
		labelCat.setBounds(46, 59, 67, 14);
		panel_2.add(labelCat);
		
		String[] messageStrings4={"Fuel","Food","Entertainment","Holidays","Shopping","Bills"};
		JComboBox comboBoxCat = new JComboBox(messageStrings4);
		comboBoxCat.setBounds(185, 58, 127, 20);
		panel_2.add(comboBoxCat);
		
		JLabel labelAmt = new JLabel("Amount");
		labelAmt.setBounds(46, 89, 67, 14);
		panel_2.add(labelAmt);
		
		textFieldAmt = new JTextField();
		textFieldAmt.setColumns(10);
		textFieldAmt.setBounds(185, 89, 127, 20);
		panel_2.add(textFieldAmt);
		
		DbHelper dbHelper=new DbHelper();
		JButton button = new JButton("Submit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int eid = dbHelper.getNextId("expense", "eid");
				
				String expenseDateString = comboBoxYear.getSelectedItem().toString() + "-" + comboBoxMonth.getSelectedItem().toString() + "-" + comboBoxDate.getSelectedItem().toString();
				
				ExpenseModel expenseModel = new ExpenseModel();
				
				expenseModel.setEid(eid);
				expenseModel.setAmount(Integer.parseInt(textFieldAmt.getText()));
				expenseModel.setCategory(comboBoxCat.getSelectedItem().toString());
				expenseModel.setExpenseDate(expenseDateString);
				expenseModel.setUsername(Commons.currentLogin);
				
				if(dbHelper.saveExpense(expenseModel)) {
					JOptionPane.showMessageDialog(Question2.this, "Expense saved.");
					BudgetStatus frame=new BudgetStatus();
					frame.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(Question2.this, "Failed.");
				}
			}
		});
		button.setBounds(156, 150, 89, 23);
		panel_2.add(button);
	}

}
