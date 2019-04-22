import java.util.*;
// import java.util.Pair;

import java.io.FileOutputStream;
import java.io.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
 
class rec implements Serializable{
    public int lec ;
    public int wid;
    public ArrayList<Customer> arl = new ArrayList<Customer>(); 
    private static final long serialVersionUID = 1L;
    rec(int lec, int wid){
        this.lec=lec;
        this.wid= wid;
        
    }

}

public class test{
    public static void main(String[] args){

    List<rec> listrec = new ArrayList<rec>();
    
    rec r1 = new rec(1,2);
    rec r2 = new rec(2,3);
    rec r3 =r1;
    listrec.add(r1);
    listrec.add(r2);
    listrec.add(r3);

    System.out.println(r3.lec+" "+r3.wid);
    r3.lec=6;
    System.out.println(r1.lec+" "+r1.wid);
    

    String k= "hello";
    if(r3!=r1)
    System.out.println(k.charAt(0));


    try {
 
        FileOutputStream fileOut = new FileOutputStream("objectfile");
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(r1);
        objectOut.close();
        fileOut.close();
        System.out.println("The Object  was succesfully written to a file");

        FileInputStream fi = new FileInputStream(new File("objectfile"));
        ObjectInputStream oi = new ObjectInputStream(fi);
    
        // ArrayList<rec> reclist= (ArrayList<rec>) oi.readObject();
        rec r4= (rec) oi.readObject();
        System.out.println(r4.arl.get(0));
    } catch (Exception ex) {
        ex.printStackTrace();
    }



    // Pair<Integer,Integer> mn= new Pair(1,2);
    // mn.setkey(3);

    }
}