import java.util.Scanner;

public class RunApp{

	static	Scanner input= new Scanner(System.in);
	static Database database = new Database();
	static Admin admin= new Admin(database);	
	static Customer customer = new Customer(database);

	private static void administrator(){
		System.out.println("Logged In As Admininstrator \nChoose from the following options: \n");
		int choose=10;
		do{
			System.out.println("\n1)Insert Product/Category\n2)Delete Product/Category\n3)Search Product\n4)Modify Product\n0)Exit As Administrator\n");
			choose=input.nextInt();
			switch(choose){
				case 1: {
					System.out.println("\nPlease Enter Path");
					String path = input.next();
					System.out.println("\nPlease Enter Product Name");
					String product = input.next();
					try{
						admin.insert(path,product);
					}
					catch(Exception e){
						System.out.println("Exception Catched:"+ e.getMessage());
					}
					break;
					
				}
				case 2: {
					System.out.println("\nPlease Enter Path Name");
					String nodeName= input.next();
					try{
						admin.deleteNode(nodeName);
					}
					catch(Exception e){
						System.out.println("Exception Catched:"+ e.getMessage());						
					}
					break;
				}
				case 3: {
					System.out.println("\nPlease Enter Product");
					String productName= input.next();
					try{
						admin.searchProduct(productName);
					}
					catch(Exception e)
					{
						System.out.println("Exception Catched:"+ e.getMessage());						
					}
					break;
				}
				case 4: {
					System.out.println("\nPlease Enter product Name");
					String productName=input.next();
					try{
						admin.modifyProduct(productName);
					}
					catch(Exception e)
					{
						System.out.println("Exception Catched:"+ e.getMessage());						
					}
					break;
				}
				case 0: {break;}
			}
		}while(choose!=0);

		
	}

	private static void customer(){
		System.out.println("Logged In As Customer \nChosse from the following options: \n");
		int choose=10;
		do{
			System.out.println("\n1)Add Funds\n2)Add Product To Cart\n3)Remove Product from Cart\n4)Check-Out Cart\n5)Check Funds\n6)View Cart\n0)Exit As Customer\n");
			choose=input.nextInt();
			switch(choose){
				case 1: {
					System.out.println("Please enter the amount of funds");
					int funds = input.nextInt();
					try{
						customer.addFunds(funds);
					}
					catch(Exception e){
						System.out.println("Exception Catched:"+ e.getMessage());						
					
					}
					System.out.println("Funds added successfully");	
					break;

				}
				case 2: {
					System.out.println("Please Enter Product Name");
					String productName= input.next();
					try{
						customer.addProduct(productName);
					}
					catch(Exception e){
						System.out.println("Exception Catched:"+ e.getMessage());						
					
					}
					break;
				}
				case 3: {
					System.out.println("Please Enter Product Name");
					String productName= input.next();
					customer.removeProduct(productName);
					break;
				}
				case 4:{
					try{
						customer.checkOut();
					}
					catch(Exception e){
						System.out.println("Exception Catched:"+ e.getMessage());						

					}
					break;
				}
				case 5:{
					System.out.println("Balance:\t"+customer.checkFunds());
					break;
				}
				case 6:{
					customer.viewCart();
				}
				case 0: {break;}
			}
		}while(choose!=0);

	
	}

	public static void main(String [] args){
		
		System.out.println("Welcome to Amacon");
		System.out.println("Log in as: \n1)Administrator\n2)Customer\n");
		int loginId= input.nextInt();
		while(loginId!=0){
			if(loginId==1) administrator();
			else customer();
			System.out.println("Log in as: \n1)Administrator\n2)Customer\nTO EXIT : 0 \n");
		 	loginId= input.nextInt();
		}
		
		System.out.println("Total Revenue = " + database.getRevenue());	

	}



}
