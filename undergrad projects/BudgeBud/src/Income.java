import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.Component;
import javax.swing.JSlider;
import javax.swing.JComboBox;
import javax.swing.DropMode;
import java.awt.Rectangle;
import javax.swing.UIManager;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import backend.Commons;
import backend.DbHelper;
import model.ExpenseModel;

import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import backend.Commons;
import backend.DbHelper;
import model.IncomeModel;


public class Income extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldAmount;
	private JComboBox comboBoxDate;
	
	private DbHelper dbHelper;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Income frame = new Income();
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
	public Income() {
		
		dbHelper = new DbHelper();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 443, 371);
		contentPane = new JPanel();
		contentPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 245, 220));
		panel.setBounds(0, 0, 427, 64);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblIncome = new JLabel("INCOME");
		lblIncome.setFont(new Font("DejaVu Serif", Font.BOLD | Font.ITALIC, 18));
		lblIncome.setBounds(21, 0, 155, 64);
		lblIncome.setForeground(new Color(0, 0, 0));
		panel.add(lblIncome);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(192, 192, 192));
		panel_1.setBounds(0, 75, 427, 257);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(55, 127, 67, 14);
		panel_1.add(lblAmount);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(56, 63, 46, 14);
		panel_1.add(lblDate);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setBounds(55, 97, 89, 14);
		panel_1.add(lblCategory);
		
		textFieldAmount = new JTextField();
		textFieldAmount.setBounds(194, 127, 129, 20);
		panel_1.add(textFieldAmount);
		textFieldAmount.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(162, 190, 89, 23);
		panel_1.add(btnSubmit);
		
		String[] messageStrings={"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31",};
		comboBoxDate = new JComboBox(messageStrings);
		comboBoxDate.setBounds(194, 62, 42, 20);
		panel_1.add(comboBoxDate);
		
		String[] messageStrings2={"01","02","03","04","05","06","07","08","09","10","11","12"};
		JComboBox comboBoxMonth = new JComboBox(messageStrings2);
		comboBoxMonth.setBounds(246, 62, 77, 20);
		panel_1.add(comboBoxMonth);
		
		String[] messageStrings3={"2017","2018","2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030","2031","2032","2033","2034","2035","2036","2037","2038","2039","2040","2041","2042","2043","2044","2045","2046","2047","2048","2049","2050"};
		JComboBox comboBoxYear = new JComboBox(messageStrings3);
		comboBoxYear.setBounds(336, 62, 61, 20);
		panel_1.add(comboBoxYear);
		
		String[] messageStrings4={"Salary","Other"};
		JComboBox comboBoxCat = new JComboBox(messageStrings4);
		comboBoxCat.setBounds(194, 94, 129, 20);
		panel_1.add(comboBoxCat);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Spending frame=new Spending();
				frame.setVisible(true);
				
			}
		});
		btnBack.setBounds(0, 0, 77, 32);
		panel_1.add(btnBack);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(0, 65, 427, 10);
		contentPane.add(panel_2);
		
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int inid = dbHelper.getNextId("income", "inid");
				
				String incomeDateString = comboBoxYear.getSelectedItem().toString() + "-" + comboBoxMonth.getSelectedItem().toString() + "-" + comboBoxDate.getSelectedItem().toString();
				
				IncomeModel incomeModel = new IncomeModel();
				
				incomeModel.setInid(inid);
				incomeModel.setAmount(Integer.parseInt(textFieldAmount.getText()));
				incomeModel.setCategory(comboBoxCat.getSelectedItem().toString());
				incomeModel.setIncomeDate(incomeDateString);
				incomeModel.setUsername(Commons.currentLogin);
				
				if(dbHelper.saveIncome(incomeModel)) {
					JOptionPane.showMessageDialog(Income.this, "Income saved.");
					Spending frame=new Spending();
					frame.setVisible(true);
					
					
				} else {
					JOptionPane.showMessageDialog(Income.this, "Failed.");
				}
			}
		});
		
		}
}
