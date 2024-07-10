package com.tap.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//DAO Design Pattern is used to separate the data persistence logic in a separate layer.
//This way, the service remains completely in dark about how the 
//low-level operations to access the database is done. 
//This is known as the principle of Separation of Logic. stands for ->data access object

import com.tap.modules.Employee;

public class EmployeeBoImpl implements EmployeeBo{

	public static Connection connection;
	public static PreparedStatement prepareStatement;
	public static Statement createStatement;
	public static ResultSet res;
	
	private final static String uq="UPDATE `employee` SET name=?,dept=?,salary=? WHERE id=?";
	private final static String query = "SELECT * from `employee`";
	private final static String dq = "DELETE from `employee` WHERE `id`=?";
	private final static String iq="INSERT into `employee` (`id`,`name`,`dept`,`salary`) values (?,?,?,?)";
	
	public EmployeeBoImpl() {  //colling construction
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","Passcode@123");
			
		} catch (ClassNotFoundException e1) {
			
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}
	
	
	

	@Override
	public int save(Employee e) {

		try {
			
			 prepareStatement  = connection.prepareStatement(iq);
			 prepareStatement.setInt(1,e.getId());      //here accepting object means we pass getid() 
			 prepareStatement.setString(2,e.getName());
			 prepareStatement.setString(3,e.getDept());
			 prepareStatement.setInt(4,e.getId());
			
			 return prepareStatement.executeUpdate();
		} catch (Exception e1) {
			
			e1.printStackTrace();
			
		}
		return 0;
		
	}

	@Override
	public int delete(int id) {
		
		try {
			 prepareStatement = connection.prepareStatement(dq);
			 prepareStatement.setInt(1, id);		//here accapting only id so we are using id only
			 return prepareStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Employee e) {
		// TODO Auto-generated method stub

		return delete(e.getId());
	}

	@Override
	public int update(Employee e) {

		try {
			 prepareStatement = connection.prepareStatement(uq);
			prepareStatement.setInt(1,getName());
			prepareStatement.setString(2,getDept());
			prepareStatement.setInt(3,getSalary());
			prepareStatement.setInt(4,getId());
			
			return prepareStatement.executeUpdate();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		return 0;
	}

	private int getId() {
		// TODO Auto-generated method stub
		return 0;
	}




	private String getDept() {
		// TODO Auto-generated method stub
		return null;
	}




	private int getSalary() {
		// TODO Auto-generated method stub
		return 0;
	}




	private int getName() {
		// TODO Auto-generated method stub
		return 0;
	}




	@Override
	public Employee get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> getAll() {
		
		ArrayList<Employee> list = new ArrayList<Employee>();
		 try {
			createStatement = connection.createStatement();
			res= createStatement.executeQuery(query);
			while(res.next()) {
				int id = res.getInt("id");
				String name = res.getString("name");
				String dept = res.getString("dept");
				int salary = res.getInt("salary");
				
				Employee e = new Employee(id, name, dept, salary);
				list.add(e);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	

}
