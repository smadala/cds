package com.cds;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class AVLTreeDemo {

	/**
	 * @param args
	 */
	private static int readInput(){
		int val=0;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		try {
			val = Integer.parseInt(br.readLine());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("please enter number");
			readInput();
		}
		return val;
	}
	private static int getOperation(){
		System.out.println("1.insert element");
		System.out.println("2.delete element");
		System.out.println("3.exit");
		System.out.println("choose option...");
		return readInput();
	}
	public static void main(String[] args) {
		AVLTree tree=new AVLTree();
		
		int op,val;
		boolean stop=false;
		while(!stop){
			op = getOperation();
			
			switch(op){
			case 1: System.out.println("enter element to insert");
					val=readInput();
					tree.insert(val);
					break;
			case 2: System.out.println(" enter element to delete ");
					val=readInput();
					tree.delete(val);
					break;
					
			case 3:
			default:
				stop = true;
					break;
			}
			tree.printTree();
			System.out.println();
		}
		/*tree.insert(1);
		tree.printTree();
		tree.insert(2);
		tree.printTree();
		tree.insert(3);
		tree.printTree();
		tree.insert(4);
		tree.printTree();
		tree.insert(5);
		tree.printTree();
		
		tree.insert(6);
		tree.printTree();
		tree.insert(7);
		tree.printTree();
		tree.delete(4);
		tree.printTree();
		tree.delete(3);
		tree.printTree();
		tree.delete(7);
		tree.printTree();*/	
	}
}
