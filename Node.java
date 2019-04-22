import java.util.List;
import java.util.ArrayList;

class Node{
	private List<Node> children = new ArrayList<Node>();
	private Node parent = null;
	private String data =null;
	private boolean product; // 0 for category 1 for product
	private int priceProduct;
	private int unitsProduct;

	public Node(){
		
	}
	
	public Node(String data){
		this.data =data;
		product=false;
	}

	public Node(Node parent,String data){
		this.parent= parent;
		this.data= data;
		product=false;
	}

	public String getData(){
		return this.data;
	}

	public boolean isProduct(){
		if(this.product)return true;
		else return false;
	}

	public void setParent(Node parent){
		this.parent= parent;
	}

	public Node getParent(){
		return this.parent;
	}

	public List<Node> getChildren(){
		return this.children;
	}

	public void setProduct(boolean product){
		this.product=product;
	}

	public void addChild(String data){
		this.product=false;
		Node newChild = new Node(this, data);
		this.children.add(newChild);
	}
	
	public void addChild(Node childNode){
		childNode.setParent(this);
		this.children.add(childNode);
		this.product=false;
	}

	public Node getChild(int index){
		return this.children.get(index);
    }
    
    public Node getChild(String childName){
        return this.getChild(this.isChild(childName));
    }

	public void setPrice(int price){
		if(product&&price>0)
		this.priceProduct=price;
		
	}
	
	public int getPrice(){
		if(product)
		return this.priceProduct;
		else return -1;
	}

	public void setUnits(int units){

		if(product&&units>0)
		this.unitsProduct=units;
	}

	public int getUnits(){
		if(product)
		return this.unitsProduct;
		else return -1;
	}

	public int numChild(){
		return children.size();
	}

	public int isChild(String childName){
		
		for(Node temp : children){
			if(temp.getData().equals(childName)){
				return children.indexOf(temp);
			}
		}
		return -1;
	}
}