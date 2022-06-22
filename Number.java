package project3;
/**
 * The Number class is used to represent positive integers. 
 * It provides several operations to support number manipulation: addition, multiplication and comparisons.
 * 
 * There is no limit to the number of digits in a Number object.
 * The numbers are represented in the form of a double linked list. 
 * 
 * Each node in the list contains a single digit.
 * The least significant digit, or the ones digit, is stored in the very first node. The tens digit is stored in the second node. And so on .... 
 * 
 * It has a integer size that record the length of Number linked list.
 * Users can access the the head, tail, and size of the Number linked list in constant time.
 * 
 * @author Dingxin Zhang
 *
 */
public class Number{
	
	 private Node head;
	 private Node tail; 
	 private int size;
	 
	 /**
	  * The Number class has a private Node class inside it.
	  * The Node class stores a String data as digit in a number.
	  * Node next refers to the next element in the linked list.
	  * Node prev refers to the previous element in the linked list.
	  * It has Node next and Node prev to create a double linked list for Number class.
	  */	 
	private class Node
	{
		public String data; 
	    public Node next; 
	    public Node prev; 
	    
	    /**
	     * The Node class constructor.
	     * 
	     * It initializes a String data as digit in a number.
	     * 
	     * @param data assigned to individual Node
	     * 
	     * @throws NullPointerException if the data input is null
	     */
	    public Node (String data) 
	    {
	    	//check if data input is null, and throw exception
	    	if (data == null )throw new NullPointerException("list does not allow null elements");
	    	//set data, next, and prev 
	    	this.data = data;
	        this.next = null;
	        this.prev=null;
	        
	    }      
	}
	
	/**
	 * The default Number class constructor 
	 * set head and tail to null
	 * initialize the Number class size to 0
	 */
	public Number() {
        head = null;
        tail = null;
        size = 0; 
    }
	/**
	 * The Number class constructor 
	 * 
	 * @param the number assigned to the linked list
	 * 
	 * @throws IllegalArgumentException if assigned number contains any character other than digit
	 * 
	 * @throws NullPointerException if  assigned number is null
	 */
	public Number(String number) throws IllegalArgumentException, NullPointerException
	{
		//check if assigned number contains any character other than digit
		if(number==null)
		{
			throw new NullPointerException();
		}
		//check if number contains any character other than digit
		try 
		{  
		  Double.parseDouble(number);  
		} 
		catch(IllegalArgumentException  e)
		{
		    throw new IllegalArgumentException();
		}
		//create individual nodes for each digit
		//add these nodes to the Number linked list
		int numbern=number.length();
		this.size=numbern;
		head=tail=new Node(number.substring(numbern-1));
		for(int i=numbern-2; i>=0; i--)
		{
			Node n = new Node(String.valueOf((number.charAt(i))));
			tail.next=n;
			n.prev=tail;
			tail=n;
			tail.next=null;
		}			
	}
	
	/**
	 * A Number method that can return the size of Number linked list.
	 * 
	 * @return the number of digits in a Number.
	 */
	public int length()
	{
		return this.size;
		
	}
	/**
	 * A Number method that can return the string representation of Number linked list.
	 * 
	 * @return the string representation of a Number.
	 */
	public String toString()
	{
		String str="";
		Node current=tail;
		while(current!=null)
		{
			str=str+current.data;
			current=current.prev;
		}
		return str;
	}
	/**
	 * A helper method of Number class
	 * It can add a single Node to the end of a linked list
	 * It helps adding and multiplying numbers.
	 * 
	 * @param the node to be added at the end of a linked list
	 * 
	 * size of the linked list will +1 if this method is implemented
	 */
	public void addNode(Node n)
	{
		//check if the linked list is empty
		 if (head == null) 
		 {   
             head = tail=n;
             head.prev=null;
             tail.next=null;
         } 
		//if not, add this node to the end of the list.
		 else
		 {
			 tail.next=n;
			 n.prev=tail;
			 tail=n;
			 tail.next=null;
		 }
		 //the list's size is therefore increased after adding a node to it.
		this.size++;
		
	}
	
	/**
	 * Computes the sum of this object with other. 
	 * Returns the result in a new object. 
	 * This object is not modified by call to add.
	 * 
	 * @param the value to be added to this object
	 * 
	 * @return a Number object whose value is equal to the sum of this object and other
	 * 
	 * @throws NullPointerException - if other is null
	 * 
	 */
	public Number add​(Number other) throws NullPointerException
	{
		//check if other is null, and throw exception if it is.
		if(other==null)
		{
			throw new NullPointerException("Users cannot add nothing to a number");
		}
		//set a new number as the output of this method.
		//set 2 nodes as this.head and other.head as reference, so we can iterate through the list
		//set int tem as a counter to determine whether sum of 2 digits exceeds 10
		//set int sum to record individual digit sum
		Number outcome=new Number();
		Node current1=this.head;
		Node current2=other.head;
		int tem=0;
		int sum=0;
		
		//iterating the lists
		while(current1!=null||current2!=null)
		{
			sum=tem;
			//record individual digit sum
			if(current1!=null)
			{
				sum = sum+Integer.valueOf((String) current1.data);
				current1=current1.next;
			}
			if(current2!=null)
			{
				sum = sum+Integer.valueOf((String) current2.data);
				current2=current2.next;
			}
			//after iterating to the next digit, get the sum modulus 10 to set the digit at each place
			Node n=new Node(String.valueOf(sum%10));
			outcome.addNode(n);
			//check if digit sum exceeds 10
			if(sum>9)
			{
				tem=1;
			}
			else
			{
				tem=0;
			}
		}
		//if digit sum exceeds 10, add 1 to the next digit place
		if (tem>0)
		{
			outcome.addNode(new Node("1"));
		}
		//return the final result
		return outcome;
	}
	
	/**
	 * Computes the product of this object and a single digit digit. 
	 * Returns the result in a new object. 
	 * This object is not modified by call to multiplyByDigit.
	 * 
	 * @param digit - a single positive digit to be used for multiplication
	 * 
	 * @return a Number object whose value is equal to the product of this object and digit
	 * 
	 * @throws IllegalArgumentException -  when digit is invalid (i.e., not a single digit or negative)
	 */
	public Number multiplyByDigit​(int digit) throws IllegalArgumentException
	{
		//check if digit is invalid
		if(digit<0||digit>9)
		{
			throw new IllegalArgumentException();
		}
		//set a new node current as this.head to iterate the list
		//set a new number outcome to record the result
		//set a int tem to record product digits that exceeds 10
		//set a int pro to record 2 digits product
		Node current=this.head;
		Number outcome=new Number();
		int tem=0;
		int pro=0;
		//iterating the number lists
		while(current!=null)
		{
			pro=tem;
			pro=pro+Integer.valueOf((String)current.data)*digit;
			current=current.next;
			Node n=new Node(pro%10+"");
			tem=Integer.valueOf(pro/10);
			outcome.addNode(n);		
		}
		//if product digits exceeds 10, it adds to the result node in the next digit place
		if(tem>0)
		{
			outcome.addNode(new Node(String.valueOf(tem)));	
		}
		//return the result
		return outcome;
		
	}
	
	/**
	 * Computes the product of this object and other. 
	 * Returns the result in a new object. 
	 * This object is not modified by call to multiply.
	 * 
	 * @param other- the value to be multiplied by this object
	 * 
	 * @return a Number object whose value is equal to the product of this object and the other object
	 * 
	 * @throws NullPointerException - if other is null
	 */
	public Number multiply​(Number other) throws NullPointerException
	{
		//set a new node current as other.head to iterate the list
		//set a new number outcome to record the result
		//set a new number pro to record individual digit products
		//set int pow as a counter to determine how many times the product should be multiplied by 10
		//set int counter to not change pow value as pow increases for the while loop
		Node current=other.head;
		Number outcome=new Number();
		Number pro=new Number();
		int pow=0;
		int counter=0;
		//iterating the number lists
		while(current!=null)
		{
			counter=pow;
			pro=this.multiplyByDigit​(Integer.valueOf((String)current.data));
			//determine how many times the product should be multiplied by 10
			while(counter!=0)
			{
				//add a "0" in front of the list to make "multiplied by 10" function
				Node o =new Node("0");
				o.next=pro.head;
				pro.head=o;
				counter--;
			}
			//record product sum, and count pow
			outcome=outcome.add​(pro);
			pow++;
			current=current.next;
			
		}
		//return the result
		return outcome;
		
	}
	
	/**
	 * Determines if this object is equal to obj.
	 * Two Number objects are equal if all of their digits are the same and in the same order, and if they have the same number of digits. 
	 * In other words, if the values represented by the two objects are the same.
	 * 
	 * @override equals in class Object
	 * 
	 * @param other - the object to be compared to this object
	 * 
	 * @return true if two objects are equal, false otherwise
	 */
	 public boolean equals(Number other)
	 {
		 //check if other is null
		if(other==null)
		{
			return false;
		}
		 //check if other is not a Number class
		if(!(other instanceof Number))
		{
			return false;
		}
		//check if they have the same length
		int len1=this.length();
		int len2=other.length();	
		
		if(len1!=len2)
		{
			return false;
		}
		//check if each node is the same for both numbers
		Node current1=this.head;
		Node current2=other.head;
		while(current1!=null&&current2!=null)
		{
			if(Integer.valueOf((String)current1.data)!=Integer.valueOf((String)current2.data))
			{
				return false;
			}
			else
			{
				current1=current1.next;
				current2=current2.next;
			}
		}
		return true;
	}
	 
	 /**
	  * Compares this object with other for order. 
	  * Returns a negative integer if this object's value is strictly smaller than the value of other. 
	  * Returns a positive integer if this object's value is strictly greater than the value of other. 
	  * Returns zero if two values are the same.
	  * 
	  * compareTo in interface Comparable<Number>
	  * 
	  * @param other - the object to be compared with this object
	  * 
	  * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than other
	  * 
	  * @throws NullPointerException - if other is null
	  */
	public int compareTo​(Number other) throws NullPointerException
	{
		//check if other is null
		if(other==null)
		{
			throw new NullPointerException("Users cannot compare to a null object");
		}
		//check if this object's value is strictly greater than the value of other. 
		if(this.length()>other.length())
		{
			return 1;
		}
		//check if this object's value is strictly smaller than the value of other. 
		if(this.length()<other.length())
		{
			return -1;
		}
		//when two numbers have the same number of digits, check if this object's value is strictly smaller/smaller than the value of other.
		if(this.length()==other.length())
		{
			Node current1=this.tail;
			Node current2=other.tail;
			//iterating through the list
			while(current1!=null&&current2!=null)
			{
				if(Integer.valueOf(current1.data)>Integer.valueOf(current2.data))
				{
					return 1;
				}
				if(Integer.valueOf(current1.data)<Integer.valueOf(current2.data))
				{
					return -1;
				}
				current1=current1.prev;
				current2=current2.prev;
			}
		}
		//if two numbers are equal
		return 0;
	}

    

	

}
