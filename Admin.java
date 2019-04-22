import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Admin{

    Database database;

    static Scanner input = new Scanner(System.in);

    Admin(Database database){
        this.database=database;
    }

    public void insert(String category, String productName) throws ProductNameAlreadyExistsException,CategoryNameException{
		ArrayList<String> newPath = new ArrayList<String>();

		for(int i=0;i<category.length();i++){
			StringBuffer j = new StringBuffer();

			while(i<category.length()&&category.charAt(i)!='>'){
				j.append(category.charAt(i));
				i++;				
			}
			if(j.length()>0){
                if(newPath.contains(j.toString())){
                    throw new CategoryNameException("Category Names Match, Invalid Path Name");
                }
                newPath.add(j.toString());

            }
        }
        if(newPath.contains(productName)){
            throw new CategoryNameException("Category Names Match, Invalid Product Name");
        }
        else
        newPath.add(productName);
        if(database.searchProduct(productName)==null){
            Node newNode = database.insert(newPath);
            if(newNode!=null){
                System.out.println("\nProdcut Added\nPlease Enter the price and the quantity");
                int price = input.nextInt();
                int units = input.nextInt();
                while(price<0){
                    System.out.println("Enter Positive price");
                    price=input.nextInt();
                }
                while(units<0){
                    System.out.println("Enter Positive quantity");
                    units=input.nextInt();
                }
                newNode.setPrice(price);
                newNode.setUnits(units);
                System.out.println("Price and Quantity Updated Successfully");
            }
            else {
                throw new ProductNameAlreadyExistsException("Product Name ALready Exists1");            
            }
        }
        else {
            throw new ProductNameAlreadyExistsException("Product Name ALready Exists2");
        }
        
	}


    
	public void searchProduct(String productName) throws ProductNotFoundException{
		Node foundNode=database.searchProduct(productName);
        if(foundNode!=null){
            database.printPath(foundNode);
            database.printDetails(foundNode);
        }
        else {
            throw new ProductNotFoundException("Product Not Found");
        }			
    }
    
    public void modifyProduct(String productName)throws ProductNotFoundException{
		Node foundNode=database.searchProduct(productName);

        if(foundNode!=null){
            database.printDetails(foundNode);
            if(foundNode.isProduct()){
                System.out.println("\n1)To change price enter\n2)To Change number of units");
                int priceORunits = input.nextInt();
                if(priceORunits==1){
                    System.out.println("Please Enter new price");
                    int price= input.nextInt();
                    while(price<0){
                        System.out.println("Enter Positive price");
                        price=input.nextInt();
                    }
                    foundNode.setPrice(price);
                }
                else if(priceORunits==2){
                    System.out.println("Please Enter new num of units");
                    int units= input.nextInt();
                    while(units<0){
                        System.out.println("Enter Positive quantity");
                        units=input.nextInt();
                    }
                    foundNode.setUnits(units);
                }
                System.out.println("Done");
            }
            else System.out.println("Its a category");
        }
        else {
            throw new ProductNotFoundException("Product Not Found");
        }	
    }

    public void deleteNode(String nodeName)throws InvalidPathException{
        List<String> newPath = new ArrayList<String>();

		for(int i=0;i<nodeName.length();i++){
			StringBuffer j = new StringBuffer();

			while(i<nodeName.length()&&nodeName.charAt(i)!='>'){
				j.append(nodeName.charAt(i));
				i++;				
			}
			if(j.length()>0)
			newPath.add(j.toString());
        }
        if(database.deleteNode(newPath)){
            System.out.println("Deleted Successfully");
        }
        else {
            throw new InvalidPathException("Invalid Path");
        }	
    }
}