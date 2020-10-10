package poly; // do not delete

import java.io.IOException;
import java.util.Scanner;

/**
 * This class implements evaluate, add and multiply for polynomials.
 * 
 * @author runb-cs112
 *
 */
public class Polynomial {
	
	/**
	 * Reads a polynomial from an input stream (file or keyboard). The storage format
	 * of the polynomial is:
	 * <pre>
	 *     <coeff> <degree>
	 *     <coeff> <degree>
	 *     ...
	 *     <coeff> <degree>
	 * </pre>
	 * with the guarantee that degrees will be in descending order. For example:
	 * <pre>
	 *      4 5
	 *     -2 3
	 *      2 1
	 *      3 0
	 * </pre>
	 * which represents the polynomial:
	 * <pre>
	 *      4*x^5 - 2*x^3 + 2*x + 3 
	 * </pre>
	 * 
	 * @param sc Scanner from which a polynomial is to be read
	 * @throws IOException If there is any input error in reading the polynomial
	 * @return The polynomial linked list (front node) constructed from coefficients and
	 *         degrees read from scanner
	 */
	public static Node read(Scanner sc) 
	throws IOException {
		Node poly = null;
		while (sc.hasNextLine()) {
			Scanner scLine = new Scanner(sc.nextLine());
			poly = new Node(scLine.nextFloat(), scLine.nextInt(), poly);
			scLine.close();
		}
		return poly;
	}
	
	/**
	 * Returns the sum of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list
	 * @return A new polynomial which is the sum of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node add(Node poly1, Node poly2) { 
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		
		if(poly1==null && poly2==null ) {
			return null;}
			
			Node ptr1 = poly1;
			Node ptr2 = poly2; 
			Node front = null; //front of new LL 
			Node temp = null;
			float newCoeff; 
			
			while(ptr1 != null || ptr2 != null) {//as long as there is a term in either
				
			if(ptr1==null) {//assuming poly1 is empty
				if(temp==null) {
					temp = new Node(ptr2.term.coeff, ptr2.term.degree, null);
					front = temp;
				}else {
					front.next = new Node(ptr2.term.coeff, ptr2.term.degree, null);
					front=front.next; 
				}
				
				ptr2=ptr2.next; //move pointer                       
				
			}else if(ptr2==null) {//poly2 is empty
				if(temp==null) {
					temp = new Node(ptr1.term.coeff, ptr1.term.degree, null);
					front = temp;
				}else {
					front.next = new Node(ptr1.term.coeff, ptr1.term.degree, null);
					front=front.next; 
				}
				
				ptr1=ptr1.next; //move pointer
				
			}else{
				if(ptr1.term.degree==ptr2.term.degree) {                           //case 1
					newCoeff = ptr1.term.coeff + ptr2.term.coeff;
					
					if(newCoeff!=0) {//creates node if coeff isnt 0
						if(temp==null) { //if this is the first node
							temp = new Node(newCoeff, ptr2.term.degree, null);
							front = temp;
						}else {
							front.next = new Node(newCoeff, ptr2.term.degree, null);
							front = front.next;
						}
					}	
					
					ptr1 = ptr1.next;
					ptr2 = ptr2.next;
				}else if(ptr1.term.degree>ptr2.term.degree) {                      //case 2
					if(ptr2.term.coeff!=0) {
						
						if(temp==null) {//if first node
							temp = new Node(ptr2.term.coeff, ptr2.term.degree, null);
							front = temp;
						}else {
							front.next = new Node(ptr2.term.coeff, ptr2.term.degree, null);
							front=front.next; 
						}
					}
				
				ptr2 =ptr2.next; //moves pointer
				
			}else if(ptr1.term.degree<ptr2.term.degree) {                          //case 3
				
				if(ptr1.term.coeff!=0) {
					if(temp==null) {//if first node
						temp = new Node(ptr1.term.coeff, ptr1.term.degree, null);
						front = temp;
					}else {
						front.next = new Node(ptr1.term.coeff, ptr1.term.degree, null);
						front=front.next; 
					}
				}
				
				
				
				ptr1 = ptr1.next; //moves pointer
			}
		}
		}
			
			return temp; 
			
		
			
	}

	/**
	 * Returns the product of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list)
	 * @return A new polynomial which is the product of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node multiply(Node poly1, Node poly2) {
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		
		Node front2 = null; 
		Node p1 = poly1;
		Node p2 = poly2; 
		float coeffx = 0;
		int newdeg = 0;
		
		if(poly1==null|| poly2==null) {//checks to see if either polynomials are empty
			return null;
		}
		
		while(p1 != null) {
			while(p2 != null) {
				
				coeffx = p1.term.coeff*p2.term.coeff;
				newdeg = p1.term.degree + p2.term.degree;
				front2 = new Node(coeffx, newdeg, front2);
				
				p2=p2.next; 
			}
			p1=p1.next;
			p2= poly2; //resets p2
		}
		
		Node front3 = null; 
		int max = front2.term.degree;
		Node p3 = front2; 
		int newdeg2 = 0;
		float coeff3 = 0; 
		while(newdeg2<=max) {
			while(p3!= null) {
				if (p3.term.degree==newdeg2) {
					coeff3 += p3.term.coeff; //adds coeffs of same deg
			}
				p3=p3.next;
		}
			if(coeff3!=0) {//makes sure no coefficient is 0
				front3 = new Node(coeff3, newdeg2, front3);
			}
				coeff3=0;
				newdeg2++; 
				p3=front2; 
		}
		
		Node p4 = front3;
		Node front4= null; 
		
		while(p4!=null) {
			front4 = new Node(p4.term.coeff, p4.term.degree, front4);
			p4=p4.next;
		}
			
		return front4;	
		
	}
		
	/**
	 * Evaluates a polynomial at a given value.
	 * 
	 * @param poly Polynomial (front of linked list) to be evaluated
	 * @param x Value at which evaluation is to be done
	 * @return Value of polynomial p at x
	 */
	public static float evaluate(Node poly, float x) {
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		if(poly==null) {
			return 0;}

		return  evaluate(poly.next,x)+ (float)Math.pow(x, poly.term.degree)*poly.term.coeff ;
	}
	
	/**
	 * Returns string representation of a polynomial
	 * 
	 * @param poly Polynomial (front of linked list)
	 * @return String representation, in descending order of degrees
	 */
	public static String toString(Node poly) {
		if (poly == null) {
			return "0";
		} 
		
		String retval = poly.term.toString();
		for (Node current = poly.next ; current != null ;
		current = current.next) {
			retval = current.term.toString() + " + " + retval;
		}
		return retval;
	}	
}
