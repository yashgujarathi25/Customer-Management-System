package customerutils;
import static utils.ValidationRules.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static com.app.core.Customer.*;
import com.app.core.Customer;
import com.app.core.ServicePlan;

import customer_exception.CustomerHandlingException;

public class PopulateCustomer {
	public static List<Customer> populateCustomerList() throws ParseException, CustomerHandlingException {
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		customerList.add(new Customer("Yash", "yash123@gmail.com", "Yash@123", 500, sdf.parse("25/12/1994"),
				ServicePlan.DIAMOND));
		customerList.add(new Customer("Tanmay", "tanmay3@gmail.com", "Tanmay#123", 300, sdf.parse("19/01/1994"),
				ServicePlan.GOLD));
		customerList.add(new Customer("Abhinav", "abhina564@gmail.com", "Abhi$123", 300, sdf.parse("19/09/1993"),
				ServicePlan.PLATINUM));
		return customerList;
	}
	public static Customer customerLogin(String email, String pwd, List<Customer> custList) throws CustomerHandlingException
	{
		validateEmail(email);
		validatePassword(pwd);
		Customer c = findByEmail(email, custList);
		if(c.getPassword().equals(pwd))
			return c;
		throw new CustomerHandlingException("Invalid Login: Wrong Password..!!!");
	}
	
	public static void customerDetailsByDuration(Date begindate,Date endDate, List<Customer> cusList) throws CustomerHandlingException
	{
		int count = 0;
		for(Customer c : cusList) {
			if(c.getDob().before(endDate) && c.getDob().after(begindate)) {
				System.out.println(c);
				count++;}
		}
		if(count==0)
		{
			throw new CustomerHandlingException("No record Found");
		}
		
	}
	public static void detailsBySpecificPlan(String sp, List<Customer> cusList) throws CustomerHandlingException
	{    int count=0;
		ServicePlan serplan = validateServiceplan(sp);
		for(Customer c : cusList) {
			if(c.getPlan()==serplan) {
				System.out.println(c);
				count++;}
		}
		if(count==0) {
			throw new CustomerHandlingException("No one Having this Plan!!");
			
		}
			
	}
}
