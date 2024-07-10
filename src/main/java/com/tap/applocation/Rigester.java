package com.tap.applocation;

import java.io.IOException;

import com.tap.dao.EmployeeBoImpl;
import com.tap.modules.Employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/rigester")
public class Rigester extends HttpServlet {
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	int id = Integer.parseInt(request.getParameter("id"));
	String name = request.getParameter("name");
	String dept = request.getParameter("dept");
	int salary = Integer.parseInt(request.getParameter("salary"));
	System.out.println("wiehgfwi");
	
	Employee e = new Employee(id, name, dept, salary);
	EmployeeBoImpl eb = new EmployeeBoImpl();
	System.out.println("wiehgfwi");
	eb.save(e);
	
	
	}

}
