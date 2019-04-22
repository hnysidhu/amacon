import java.util.List;
import java.util.ArrayList;

public class Database{

	private Node head;
	private	Node currNode;
	private  int revenue;

	Database(){
		revenue=0;
		head = new Node("$root$");
	}
	
	public Node insert (ArrayList<String> path) throws ProductNameAlreadyExistsException{
		currNode =head;
		for(int i=0;i<path.size();i++){
			int index=currNode.isChild(path.get(i));
			if(index==-1){
				Node newNode = new Node(currNode,path.get(i));
				currNode.addChild(newNode);
				currNode=newNode;
				if(i==path.size()-1) {newNode.setProduct(true);return currNode;}	
			}
			else {
				if(i==path.size()-1) {
					throw new ProductNameAlreadyExistsException("Product Already Exists");
				}; 
				currNode= currNode.getChild(index);
			}

		}
		throw new ProductNameAlreadyExistsException("Product Already Exists");
		
	}


	public void setProductPrice(int price){
		currNode.setPrice(price);
	}

	public void setProductUnits(int units){
		currNode.setUnits(units);
	}

	public Node search(Node presNode,String nodeData){
		
		int index = presNode.isChild(nodeData);
		
		if(index!=-1){
			currNode= presNode.getChildren().get(index);
			return currNode;

		}
		
		else if(presNode.numChild()==0){
			return null; 	
		}

		else {
			for(Node temp: presNode.getChildren()){
				currNode= search(temp, nodeData);
				if(currNode!= null) return currNode;
			}
			return null;
		}
		
	}

	public Node searchProduct(String productName){
		Node foundNode=search(head,productName);

		return foundNode;		
	}

	public boolean deleteNode(List<String> deletePath){
		Node tempNode= searchProduct(deletePath.get(0));
		if(tempNode!=null)	{
			for(int i=1;i<deletePath.size();i++){
				if(tempNode.isChild(deletePath.get(i))==-1){
					return false;
					
				}
				else{
					tempNode=tempNode.getChild(deletePath.get(i));
					continue;
				}
			}
			Node parentNode=tempNode.getParent();
			parentNode.getChildren().remove(tempNode);
			return true;
		}
		else return false;
	}


	public void printPath(Node presNode){
		
		if(presNode==head){
			System.out.print("root");
		}
		if(presNode!=head){
			printPath(presNode.getParent());
			System.out.print(">"+presNode.getData());
			return;
		}
	}

	public void printDetails(Node presNode){
		if(presNode.isProduct()){
			System.out.println("\nProduct Name:\t"+presNode.getData()+"\nProduct Price:\t"+presNode.getPrice()+"\nProduct Units:\t"+presNode.getUnits());
		}
		else {
			System.out.println("\nCategory Name:\t"+presNode.getData()+"\nParent Category:\t"+presNode.getParent().getData()+"\nCategory Items:\t"+presNode.numChild());
		}
	}

	public void sale(Node productNode, int quantity){
		if(productNode.isProduct()){
			productNode.setUnits(productNode.getUnits()-quantity);
			revenue+= productNode.getPrice()*quantity;
		}
		else System.out.println("Not A Product");
	}
	public int getRevenue(){
		return this.revenue;
	}

}


