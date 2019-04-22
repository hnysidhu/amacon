import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;


public class Customer{
    Database database;
    Scanner input = new Scanner(System.in);

    private int funds;
    
    List<Pair<Node,Integer>> itemsList= new ArrayList<Pair<Node,Integer>>();

    Customer(Database database){
        this.database=database;
        funds=0;        
    }

    public void addFunds(int funds){
        this.funds=this.funds+funds;
        
    }
    
    public void addProduct(String productName){
        Node productNode = database.searchProduct(productName);
        for (Pair<Node,Integer> iteratorNode:itemsList){
            if(iteratorNode.getKey()==productNode){
                System.out.println("Product Already In the Cart\nTo change Quantity Enter 1 else 0");
                int flag=input.nextInt();
                if(flag==1){
                    System.out.println("Please add new Quantity");
                    int quantity= input.nextInt();
                    while(quantity>productNode.getUnits()){
                        System.out.println("Quantity Exceeds Stock Amount\nPlease Add New Quantity, To cancel operation enter -1");
                        quantity=input.nextInt();
                        if(quantity<0){
                            System.out.println("Quantity undefiend, Operation Cancelled");
                            return;
                        }
                    }
                    iteratorNode.setValue(quantity);
                    System.out.println("Done");
                }
                return;
            }
        }

        if(productNode!=null){
            if(productNode.isProduct()){
                System.out.println("Please Specufy Quantity");
                int quantity= input.nextInt();
                if(quantity<0){
                    System.out.println("Quantity undefiend");
                    return;
                }
                while(quantity>productNode.getUnits()){
                    System.out.println("Quantity Exceeds Stock Amount\nPlease Add New quantity, To cancel operation enter -1");
                    quantity=input.nextInt();
                    if(quantity<0){
                        System.out.println("Quantity undefiend, Operation Cancelled");
                        return;
                    }
                }
                Pair<Node,Integer> listItem= new Pair<Node,Integer>(productNode,quantity);
                this.itemsList.add(listItem );

                
            }
            else System.out.println("Please choose a product");
        }
        else {System.out.println("No product found");}
    }
    public void removeProduct(String productName){
        for(Pair<Node,Integer> iteratorNode:itemsList){
            if(iteratorNode.getKey().getData().equals(productName)){
                itemsList.remove(iteratorNode);
                System.out.println("Done");

                return;
            }
        }
        System.out.println("No Product Found");
    }

    public int billAmount(){
        int cost=0;
        for(Pair<Node,Integer> iteratorNode:itemsList){
            cost+=iteratorNode.getKey().getPrice()*iteratorNode.getValue();
            
        }
        return cost;
    }

    public void viewCart(){
        System.out.println("ProductName\t\tQuantity\t\tProductPrice");
        for(Pair<Node,Integer> iteratorNode:itemsList){
            System.out.println(iteratorNode.getKey().getData()+"\t\t"+iteratorNode.getValue()+"\t\t"+iteratorNode.getKey().getPrice());
        }
        System.out.println("Total Amount:\t\t"+this.billAmount());

    }

    public void checkOut(){
        if(this.billAmount()>this.funds){
            System.out.println("Bill Amount("+this.billAmount()+") Exceeds Total Funds("+this.funds+") in the Account");
        }
        else{
            this.funds=this.funds-this.billAmount();
            if(itemsList.size()<=0){
                System.out.println("Cart is Empty!!");
                return;
            }
            for(Pair<Node,Integer> iteratorNode:itemsList){
                database.sale(iteratorNode.getKey(),iteratorNode.getValue());

            }
            System.out.println("Total Bill Amount =\t"+this.billAmount());
            System.out.println("Thankyou For Shopping With Amacon");

            itemsList.clear();
        }
    }   

    public int checkFunds(){
        return this.funds;
    }
}

class Pair<Key,Value>{
    Key key;
    Value value;
    Pair(Key key, Value value){
        this.key= key;
        this.value =value;
    }
    public Key getKey(){
        return this.key;
    }
    public Value getValue(){
        return this.value;
    }
    public void setValue(Value value){
        this.value=value;
    }
}
