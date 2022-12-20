package tester;

import static customerutils.PopulateCustomer.*;
import static utils.ValidationRules.*;
import static com.app.core.Customer.sdf;

import java.util.List;
import java.util.Scanner;

import com.app.core.Customer;

public class TestCMS {

	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in)) {
			List<Customer> customerList = populateCustomerList();
			boolean exit = false;
			while (!exit) {
				System.out.println("-------------CMS-----------------");
				System.out.println("1. Customer Registration\n2. Display all Customer\n3. Customer login"
						+ "\n4. Change Password\n5. Un subscribe customer\n6. Display names of the customers born between 2 specified dates"
						+ "\n7. Display all the customer details ,who have chosen specific plan\n10. Exit");
				try {
					switch (sc.nextInt()) {
					case 1:
						System.out.println(
								"Enter Customer Details : Name, Email, password, registrationAmount, dob, ServicePlan");
						Customer c1 = new Customer(sc.next(), duplicateValues(validateEmail(sc.next()), customerList),
								validatePassword(sc.next()), sc.nextDouble(), parseAndValidateDate(sc.next()),
								validateServiceplan(sc.next()));
						customerList.add(c1);
						break;
					case 2:
						for (Customer c : customerList) {
							System.out.println(c);
						}
						break;
					case 3:
//						System.out.println("Enter Email: ");
//						String mail = validateEmail(sc.next());
//						System.out.println("Enter Password : ");
//						String pass = validatePassword(sc.next());
//						System.out.println(validateLogin(mail, pass, customerList) ? "Login Successfull..!!" : "");
						
						System.out.println("Enter Email and Password for Sign : ");
						Customer cust = customerLogin(sc.next(),sc.next(),customerList);
						System.out.println("Successfull Login: \n Details : "+cust);
						break;
					case 4:
						System.out.println("Enter Email and Password for Sign : ");
						cust = customerLogin(sc.next(),sc.next(),customerList);
						System.out.println("Login Successfull...!! \nEnter New Password : ");
						cust.setPassword(sc.next());
						System.out.println("Paasword Changed....!!!!!!!");
						
//						System.out.println("Enter Email : ");
//						mail = validateEmail(sc.next());
//						System.out.println("Enter Old password : ");
//						pass= validatePassword(sc.next());
//						if(validateLogin(mail, pass, customerList))
//						{
//							System.out.println("Enter New Password : ");
//							String newpass = validatePassword(sc.next());
//							changePassword(mail, newpass, customerList);
//							System.out.println("Password Changed Successfully..!!");
//						}
						break;
					case 5:
						System.out.println("Enter the Customer ID : ");
						int id = findByCustomerId(sc.nextInt(), customerList);
						customerList.remove(id-1);
						System.out.println("Unsuscribed...!!!!");
						break;
					
					case 6:
						System.out.println("Enter Start and End date : ");
						customerDetailsByDuration(sdf.parse(sc.next()), sdf.parse(sc.next()), customerList);
						break; 
						
					case 7:
						System.out.println("Enter the specific Plan name : ");
						detailsBySpecificPlan(sc.next(), customerList);
						break;
					case 10:
						exit = true;
						break;
					}

				} catch (Exception e) {
					e.printStackTrace();
					sc.nextLine();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
