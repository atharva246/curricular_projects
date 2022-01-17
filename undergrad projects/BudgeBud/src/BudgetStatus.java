import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BudgetStatus extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BudgetStatus frame = new BudgetStatus();
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
	public BudgetStatus() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JProgressBar progressBarFuel = new JProgressBar();
		progressBarFuel.setBounds(205, 34, 146, 14);
		progressBarFuel.setValue(80);
		contentPane.add(progressBarFuel);
		
		JProgressBar progressBarFood = new JProgressBar();
		progressBarFood.setBounds(205, 71, 146, 14);
		contentPane.add(progressBarFood);
		
		JProgressBar progressBarEnt = new JProgressBar();
		progressBarEnt.setBounds(205, 106, 146, 14);
		progressBarEnt.setValue(50);
		contentPane.add(progressBarEnt);
		
		JProgressBar progressBarHoli = new JProgressBar();
		progressBarHoli.setBounds(205, 144, 146, 14);
		contentPane.add(progressBarHoli);
		
		JProgressBar progressBarShop = new JProgressBar();
		progressBarShop.setBounds(205, 177, 146, 14);
		contentPane.add(progressBarShop);
		
		JProgressBar progressBarBills = new JProgressBar();
		progressBarBills.setBounds(205, 212, 146, 14);
		progressBarBills.setValue(100);
		JOptionPane.showMessageDialog(BudgetStatus.this, "Your expenses are exceeding your spending limit.");
		contentPane.add(progressBarBills);
		
		JLabel lblNewLabel = new JLabel("Fuel");
		lblNewLabel.setBounds(56, 34, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Food");
		lblNewLabel_1.setBounds(56, 71, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Entertainment");
		lblNewLabel_2.setBounds(56, 106, 102, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Holiday");
		lblNewLabel_3.setBounds(56, 144, 102, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Shopping");
		lblNewLabel_4.setBounds(56, 177, 102, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Bills");
		lblNewLabel_5.setBounds(56, 212, 102, 14);
		contentPane.add(lblNewLabel_5);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Start frame=new Start();
				frame.setVisible(true);
			}
		});
		btnHome.setBounds(91, 237, 89, 23);
		contentPane.add(btnHome);
		
		JButton btnAddMoreExpenses = new JButton("Add More Expenses ");
		btnAddMoreExpenses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Question2 frame=new Question2();
				frame.setVisible(true);
				}
		});
		btnAddMoreExpenses.setBounds(215, 237, 192, 23);
		contentPane.add(btnAddMoreExpenses);
	}
}
