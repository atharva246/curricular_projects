import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import backend.Commons;
import backend.DbHelper;
import model.AppModel;
import model.ExpenseModel;
import model.IncomeModel;
import javax.swing.JSeparator;
import javax.swing.JCheckBox;

public class Spending extends JFrame {

	private JPanel contentPane;
	
	private JTable jTableExpenses;
	private JScrollPane jScrollPaneExpenses;
	
	private String[] colNamesExpense = {"Category", "Amount", "Expense Date"};
	
	private DbHelper dbHelper;
	
	private JButton buttonRefreshExpenseGrid;
	IncomeModel incomeModel;
	ExpenseModel expenseModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Spending frame = new Spending();
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
	public Spending() {
		dbHelper = new DbHelper();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 443, 371);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Spending");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_1.setBounds(0, 38, 136, 23);
		contentPane.add(btnNewButton_1);
		
		JButton buttonTransactions = new JButton("Transaction");
		buttonTransactions.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonTransactions.setBounds(133, 38, 161, 23);
		contentPane.add(buttonTransactions);
		
		JButton btnNewButton_4 = new JButton("Profile");
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_4.setBounds(291, 38, 136, 23);
		contentPane.add(btnNewButton_4);
		
		JPanel panelSpending = new JPanel();
		panelSpending.setBackground(new Color(192, 192, 192));
		panelSpending.setBounds(0, 73, 427, 270);
		contentPane.add(panelSpending);
		panelSpending.setLayout(null);
		
		JButton btnexpense = new JButton("+Expense");
		btnexpense.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Expenses frame = new Expenses();
				frame.setVisible(true);
			}
		});
		btnexpense.setBounds(147, 211, 121, 23);
		panelSpending.add(btnexpense);
		
		JButton btnincome = new JButton("+Income");
		btnincome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Income frame = new Income();
				frame.setVisible(true);
			}
		});
		btnincome.setBounds(12, 211, 106, 23);
		panelSpending.add(btnincome);
		
		JLabel lblIncome = new JLabel("Income");
		lblIncome.setBounds(90, 65, 74, 14);
		panelSpending.add(lblIncome);
		
		JLabel lblExpense = new JLabel("Expense");
		lblExpense.setBounds(90, 104, 60, 14);
		panelSpending.add(lblExpense);
		
		JLabel label = new JLabel("__________________________________");
		label.setBounds(90, 129, 296, 14);
		panelSpending.add(label);
		
		JLabel lblBalance = new JLabel("Balance");
		lblBalance.setBounds(90, 167, 89, 14);
		panelSpending.add(lblBalance);
		
		JButton btnReport = new JButton("Report");
		btnReport.setBounds(297, 211, 89, 23);
		panelSpending.add(btnReport);
		
		
		JLabel lblIncome2 = new JLabel(""+dbHelper.getIncome(Commons.currentLogin));
		lblIncome2.setBounds(239, 65, 46, 14);
		panelSpending.add(lblIncome2);
		
		
		JLabel lblExpense2 = new JLabel(""+dbHelper.getExpenseSum(Commons.currentLogin));
		lblExpense2.setBounds(239, 104, 46, 14);
		panelSpending.add(lblExpense2);
		
		
		int inc = dbHelper.getIncome(Commons.currentLogin);
		int exp = dbHelper.getExpenseSum(Commons.currentLogin);
		int bal = inc-exp;
		String bal1 = String.valueOf(bal);
		JLabel lblBalance2 = new JLabel(""+bal1);
		lblBalance2.setBounds(239, 167, 46, 14);
		panelSpending.add(lblBalance2);
		
		JPanel panelExpenses = new JPanel();
		panelExpenses.setLayout(new BorderLayout());
		panelExpenses.setBounds(450, 58, 427, 264);
		contentPane.add(panelExpenses);
		
		Object[][] rows =  dbHelper.getExpenses(Commons.currentLogin);
		jTableExpenses = new JTable(rows, colNamesExpense);
		jScrollPaneExpenses = new JScrollPane(jTableExpenses);
		panelExpenses.add(jScrollPaneExpenses, BorderLayout.CENTER);
		
		buttonRefreshExpenseGrid = new JButton("REFRESH GRID");
		panelExpenses.add(buttonRefreshExpenseGrid, BorderLayout.SOUTH);
		
		JLabel lblTrans = new JLabel("trans");
		lblTrans.setBounds(10, 0, 46, 14);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBounds(450, 62, 427, 270);
		contentPane.add(panel_3);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(35, 23, 93, 22);
		panel_3.add(lblUsername);
		
		JLabel lblCheckYourTransactions = new JLabel("Check your transactions here:");
		lblCheckYourTransactions.setBounds(12, 74, 214, 28);
		panel_3.add(lblCheckYourTransactions);
		
		JButton btnTransProf = new JButton("Transactions");
		btnTransProf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Movement.moveRight(panelSpending, panelSpending.getX(), 450);
				Movement.moveLeft(panelExpenses, panelExpenses.getX(), 0);
				Movement.moveRight(panel_3, panel_3.getX(), 450);
			}
		});
		btnTransProf.setBounds(252, 77, 144, 23);
		panel_3.add(btnTransProf);
		
		JLabel lblCheckYurReports = new JLabel("Check your budget reports here:");
		lblCheckYurReports.setBounds(12, 128, 253, 14);
		panel_3.add(lblCheckYurReports);
		
		JButton btnBudgetProf = new JButton("Budget");
		btnBudgetProf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BudgetStatus frame=new BudgetStatus();
				frame.setVisible(true);
			}
		});
		btnBudgetProf.setBounds(283, 124, 113, 23);
		panel_3.add(btnBudgetProf);
		
		JButton btnExpProf = new JButton("Expenses\r\n");
		btnExpProf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PieChart_AWT.main(null);
			}
		});
		btnExpProf.setBounds(283, 166, 113, 23);
		panel_3.add(btnExpProf);
		
		JLabel lblCheckYourExpenses = new JLabel("Check your expenses reports here:");
		lblCheckYourExpenses.setBounds(12, 170, 253, 14);
		panel_3.add(lblCheckYourExpenses);
		
		JLabel lblUserProf = new JLabel(""+ Commons.currentLogin);
		lblUserProf.setBounds(140, 27, 70, 14);
		panel_3.add(lblUserProf);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(245, 245, 220));
		panel_4.setBounds(0, 0, 427, 40);
		contentPane.add(panel_4);
	
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Movement.moveLeft(panelSpending, panelSpending.getX(), 0);
				Movement.moveRight(panelExpenses, panelExpenses.getX(), 450);
				Movement.moveRight(panel_3, panel_3.getX(), 450);
			}
		});
		
		buttonTransactions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Movement.moveRight(panelSpending, panelSpending.getX(), 450);
				Movement.moveLeft(panelExpenses, panelExpenses.getX(),0);
				Movement.moveRight(panel_3, panel_3.getX(), 450);
			}
		});
		
		
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Movement.moveRight(panelSpending, panelSpending.getX(), 450);
				Movement.moveRight(panelExpenses, panelExpenses.getX(), 450);
				Movement.moveLeft(panel_3, panel_3.getX(), 0);
			}
		});
		
		buttonRefreshExpenseGrid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				panelExpenses.remove(jScrollPaneExpenses);
				Object[][] rows =  dbHelper.getExpenses(Commons.currentLogin);
				jTableExpenses = new JTable(rows, colNamesExpense);
				jScrollPaneExpenses = new JScrollPane(jTableExpenses);
				panelExpenses.add(jScrollPaneExpenses, BorderLayout.CENTER);
			}
		});
		
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PieChart_AWT.main(null);
			}
		});
	}
}
