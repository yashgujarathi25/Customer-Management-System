 package utils;
import static com.app.core.Customer.sdf;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.app.core.Customer;
import com.app.core.ServicePlan;

import customer_exception.CustomerHandlingException;

public class ValidationRules {
	public static Date checkDate;
	static {
		try {
			checkDate=sdf.parse("1/1/1995");
		} catch (ParseException e) {
			System.out.println("Err in static block...!!!!");
		}
	}
	
	public static String validateEmail(String email) throws CustomerHandlingException
	{
		String pattern = "^[\\w\\.-]+@[\\w\\.-]+(com|org|net)$";
		if(email.matches(pattern))
			return email;
		throw new CustomerHandlingException("Invalid Email....!!!!");
	}
	public static String validatePassword(String password) throws CustomerHandlingException
	{
		String pattern = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[#@$*%^!]).{5,10}";
		if(password.matches(pattern))
			return password;
		throw new CustomerHandlingException("Invalid Password...!!!!");
	}
	public static Date parseAndValidateDate(String date) throws ParseException,CustomerHandlingException
	{
		Date dob = sdf.parse(date);
		if(dob.before(checkDate))
			return dob;
		throw new CustomerHandlingException("Invalid Dob....!!!!");
	}
	
	public static String duplicateValues(String email, List<Customer> customerList) throws CustomerHandlingException
	{
		Customer cust = new Customer(email);
		if(customerList.contains(cust))
			throw new CustomerHandlingException("Duplicate Email id");
		return email;
	}
	public static ServicePlan validateServiceplan(String plan) throws CustomerHandlingException
	{
		try {
			return ServicePlan.valueOf(plan.toUpperCase());
		}catch(IllegalArgumentException e)
		{
			StringBuilder sb = new StringBuilder("Wrong Service Plan !!!");
			sb.append("\n Available Service Plan are : ");
			sb.append(Arrays.toString(ServicePlan.values())+"\n");
			throw new CustomerHandlingException(sb.toString());
		}
	}
	
//	public static Customer validateLogin(String mail, String pass, List<Customer> custList) throws CustomerHandlingException
//	{
//		Customer c1 = new Customer(mail);
//		for(Customer c : custList)
//		{
//			if(c.equals(c1) && c.getPassword().equals(pass))
//			{
//				return true;
//			}
//		}
//		throw new CustomerHandlingException("Invalid Email or Password...!!!");	}
	
	public static Customer findByEmail(String email, List<Customer> custList) throws CustomerHandlingException
	{
		Customer c = new Customer(email);
		int index = custList.indexOf(c);
		if(index == -1) {
			throw new CustomerHandlingException("Email ID not found...!!!!");
		}
		return custList.get(index);
		
	}
	public static int findByCustomerId(int id , List<Customer> cusList) throws CustomerHandlingException
	{
		for(Customer c : cusList)
		{
			if(c.getCustomeId()==id)
				return id;
		}
		throw new CustomerHandlingException("Customer Id not Found....!!1");
	}
	
	
	
	
	
//	public static void changePassword(String mail, String newpass, List<Customer> custList) throws CustomerHandlingException
//	{
//		Customer c1 = new Customer(mail);
//		for(Customer c: custList )
//		{
//			if(c.equals(c1))
//			{
//				c.setPassword(newpass);
//			}
//		}
//	}
}
