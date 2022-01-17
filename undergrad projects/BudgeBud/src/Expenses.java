import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import backend.Commons;
import backend.DbHelper;
import model.ExpenseModel;

public class Expenses extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldAmount;
	
	private DbHelper dbHelper;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Expenses frame = new Expenses();
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
	public Expenses() {
		dbHelper = new DbHelper();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 443, 371);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 75, 427, 257);
		contentPane.add(panel);
		
		JLabel label = new JLabel("Amount");
		label.setBounds(55, 127, 67, 14);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Date");
		label_1.setBounds(55, 63, 46, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Category");
		label_2.setBounds(55, 97, 67, 14);
		panel.add(label_2);
		
		textFieldAmount = new JTextField();
		textFieldAmount.setColumns(10);
		textFieldAmount.setBounds(194, 127, 127, 20);
		panel.add(textFieldAmount);
		
		JButton button_2 = new JButton("Submit");
		
		button_2.setBounds(147, 188, 89, 23);
		panel.add(button_2);
		
		String[] messageStrings={"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31",};
		JComboBox comboBoxDate = new JComboBox(messageStrings);
		comboBoxDate.setBounds(194, 60, 42, 23);
		panel.add(comboBoxDate);
		
		String[] messageStrings2={"01","02","03","04","05","06","07","08","09","10","11","12"};
		JComboBox comboBoxMonth = new JComboBox(messageStrings2);
		comboBoxMonth.setBounds(246, 60, 75, 23);
		panel.add(comboBoxMonth);
		
		String[] messageStrings3={"2017","2018","2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030","2031","2032","2033","2034","2035","2036","2037","2038","2039","2040","2041","2042","2043","2044","2045","2046","2047","2048","2049","2050"};
		JComboBox comboBoxYear = new JComboBox(messageStrings3);
		comboBoxYear.setBounds(331, 59, 67, 23);
		panel.add(comboBoxYear);
		
		String[] messageStrings4={"Fuel","Clothes","Eating Out","Entertainment","General","Gifts","Holidays","Kids","Stationary","Shopping","Sports","Travel"};
		JComboBox comboBoxCat = new JComboBox(messageStrings4);
		comboBoxCat.setBounds(194, 96, 127, 20);
		panel.add(comboBoxCat);
		
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Spending frame=new Spending();
				frame.setVisible(true);
			}
		});
		button.setBounds(0, 0, 77, 32);
		panel.add(button);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(245, 245, 220));
		panel_1.setBounds(0, 0, 427, 64);
		contentPane.add(panel_1);
		
		JLabel lblExpense = new JLabel("EXPENSE");
		lblExpense.setForeground(Color.BLACK);
		lblExpense.setFont(new Font("DejaVu Serif", Font.BOLD | Font.ITALIC, 18));
		lblExpense.setBounds(12, 12, 155, 64);
		panel_1.add(lblExpense);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(0, 65, 427, 10);
		contentPane.add(panel_2);
		
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int eid = dbHelper.getNextId("expense", "eid");
				
				String expenseDateString = comboBoxYear.getSelectedItem().toString() + "-" + comboBoxMonth.getSelectedItem().toString() + "-" + comboBoxDate.getSelectedItem().toString();
				
				ExpenseModel expenseModel = new ExpenseModel();
				
				expenseModel.setEid(eid);
				expenseModel.setAmount(Integer.parseInt(textFieldAmount.getText()));
				expenseModel.setCategory(comboBoxCat.getSelectedItem().toString());
				expenseModel.setExpenseDate(expenseDateString);
				expenseModel.setUsername(Commons.currentLogin);
				
				if(dbHelper.saveExpense(expenseModel)) {
					JOptionPane.showMessageDialog(Expenses.this, "Expense saved.");
					Spending frame=new Spending();
					frame.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(Expenses.this, "Failed.");
				}
			}
		});
	}

}
