package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.AppModel;
import model.ExpenseModel;
import model.IncomeModel;
import model.ResponseModel;

public class DbHelper {
	private Connection connection;

	public DbHelper() {
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost/expensedb", "postgres", "atharva1234");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public boolean checkUser(String userName, String password){
		try {
			PreparedStatement preparedStatement= connection.prepareStatement("select * from app_user where username=? and password=?");

			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, password);

			ResultSet resultSet = preparedStatement.executeQuery();

			boolean success = false;

			if(resultSet.next()) {
				success = true;
			}

			resultSet.close();
			preparedStatement.close();

			return success;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public int getNextId(String tableName, String colName) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select MAX("+colName+") from " + tableName);

			ResultSet resultSet = preparedStatement.executeQuery();

			int nextId = 1;

			if(resultSet.next()) {
				try {
					nextId = resultSet.getInt(1) + 1;
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			} 

			resultSet.close();
			preparedStatement.close();

			return nextId;
		} catch(Exception ex) {
			ex.printStackTrace();
		}

		return 1;
	}

	public boolean saveUser(AppModel appModel) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("insert into app_user values(?, ?, ?, ?, ?, ?, ?, ?)");

			preparedStatement.setInt(1, appModel.getUserid());
			preparedStatement.setString(2, appModel.getUsername());
			preparedStatement.setInt(3, appModel.getAge());
			preparedStatement.setString(4, appModel.getAddress());
			preparedStatement.setString(5, appModel.getOccupation());
			preparedStatement.setString(6, appModel.getEmailid());
			preparedStatement.setString(7, appModel.getPassword());
			preparedStatement.setString(8, appModel.getGender());
			preparedStatement.executeUpdate();

			preparedStatement.close();

			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean saveExpense(ExpenseModel expenseModel) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("insert into expense values(?, ?, ?, ?, ?)");

			preparedStatement.setInt(1, expenseModel.getEid());
			preparedStatement.setString(2, expenseModel.getExpenseDate());
			preparedStatement.setString(3, expenseModel.getCategory());
			preparedStatement.setInt(4, expenseModel.getAmount());
			preparedStatement.setString(5, expenseModel.getUsername());

			preparedStatement.executeUpdate();

			preparedStatement.close();

			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	public boolean saveIncome(IncomeModel incomeModel) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("insert into income values(?, ?, ?, ?, ?)");

			preparedStatement.setInt(1, incomeModel.getInid());
			preparedStatement.setString(2, incomeModel.getIncomeDate());
			preparedStatement.setString(3, incomeModel.getCategory());
			preparedStatement.setInt(4, incomeModel.getAmount());
			preparedStatement.setString(5, incomeModel.getUsername());

			preparedStatement.executeUpdate();

			preparedStatement.close();

			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public String getExpense(String userName){
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select category, amount from expense where username= ? ");

			ResultSet resultSet = preparedStatement.executeQuery();

			String trans="";
			if(resultSet.next()) {
				try {
					trans = resultSet.getString(1);
					System.out.println(trans + "\n");
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			} 

			resultSet.close();
			preparedStatement.close();

			return trans;
		} catch(Exception ex) {
			ex.printStackTrace();
		}

		return null; 
	}
	
	
	public int getExpenseSum(String userName){
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select SUM(amount) from expense where username= ? ");
			preparedStatement.setString(1, userName);
			
			ResultSet resultSet = preparedStatement.executeQuery();

			int sum = 0;
			
			if(resultSet.next()) {
				sum = resultSet.getInt(1);
			} 

			resultSet.close();
			preparedStatement.close();

			return sum;
		} catch(Exception ex) {
			ex.printStackTrace();
		}

		return 0; 
	}

	public int getIncome(String userName){
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select amount from income where username = ?");
			preparedStatement.setString(1, userName);
			
			ResultSet resultSet = preparedStatement.executeQuery();

			int value = 0;

			if(resultSet.next()) {
				value = resultSet.getInt(1);
			} 

			resultSet.close();
			preparedStatement.close();

			return value;
		} catch(Exception ex) {
			ex.printStackTrace();
		}

		return 0; 
	}

	/*public ExpenseModel getExpenses(String username) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from expense where username = ?");

			preparedStatement.setString(1, username);

			ResultSet resultSet = preparedStatement.executeQuery();

			ExpenseModel expenseModel = null;

			if(resultSet.next()) {
				expenseModel = new ExpenseModel();

			}

			resultSet.close();
			preparedStatement.close();

			return expenseModel;
		} catch(Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public i
}*/

	public boolean saveResponse(ResponseModel responseModel) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("insert into questions values(?, ?, ?, ?)");

			preparedStatement.setInt(1, responseModel.getId());
			preparedStatement.setString(2, responseModel.getUsername());
			preparedStatement.setString(3, responseModel.getCategory());
			preparedStatement.setFloat(4, responseModel.getPercentage());

			preparedStatement.executeUpdate();

			preparedStatement.close();

			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	
	public Object[][] getExpenses(String username) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select category, amount, expdate from expense where username = ?");
			
			preparedStatement.setString(1, username);
			
			ResultSet resultSet = preparedStatement.executeQuery();

			Object[][] rows = new Object[20][3];
			
			for(int i=0;resultSet.next();i++) {
				rows[i][0] = resultSet.getString(1);
				rows[i][1] = resultSet.getString(2);
				rows[i][2] = resultSet.getString(3);
			}
			
			resultSet.close();
			preparedStatement.close();
			
			return rows;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	
	public List<String> getExpenseCategoryTitles(String username) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select distinct category from expense where username = ?");
			preparedStatement.setString(1, username);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			List<String> categoryTitlesList = new ArrayList<>();
			
			while(resultSet.next()) {
				categoryTitlesList.add(resultSet.getString(1));
			}
			
			resultSet.close();
			preparedStatement.close();
			
			return categoryTitlesList;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	
	public double getExpenseForCategory(String category, String username) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select SUM(amount) from expense where username = ? and category = ?");
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, category);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			double amount = 0;
			
			if(resultSet.next()) {
				amount = resultSet.getDouble(1);
			}
			
			resultSet.close();
			preparedStatement.close();
			
			return amount;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return 0;
	}
	
	public double getExpenseForPercentage(String category, String username) {
		try {//why aren't you comparing category too in the where condition? output is what you are selecting ree... and you are selecting percentage. so compare category too.yesterday, we talked about this and we came to a conclusion to keep it this way, so i did this. no re... yesterday I told you not to compare what you are selecting..you are selecting percentage and hence you should't compare percentage. which you did it correctly but you arren't comparing category so the query will always give you the first record.
			PreparedStatement preparedStatement = connection.prepareStatement("select percentage from questions where username = ? and category=?");// but apla ekach textbox ahee, tyacha ithe nahie kahi sambandha. oh! lemme try
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, category);
			
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			double amount = 0;
			
			if(resultSet.next()) {
				amount = resultSet.getDouble(1);
			}
			
			resultSet.close();
			preparedStatement.close();
			
			return amount;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return 0;
	}
	
	public List<String> getExpensePercentageTitles(String username) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select distinct category from questions where username = ?");
			preparedStatement.setString(1, username);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			List<String> categoryTitlesList = new ArrayList<>();
			
			while(resultSet.next()) {
				categoryTitlesList.add(resultSet.getString(1));
			}
			
			resultSet.close();
			preparedStatement.close();
			
			return categoryTitlesList;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
}
