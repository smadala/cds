package com.cds;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


class node
{
	int data;
	node left;
	node right;
	node()
	{
	left=null;
	right=null;
	}	
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public node getLeft() {
		return left;
	}
	public void setLeft(node left) {
		this.left = left;
	}
	public node getRight() {
		return right;
	}
	public void setRight(node right) {
		this.right = right;
	}
	
}
class BST
{
	node parent;
	int status;
	BST()
	{
	parent=null;	
	}	
	int height(node root)
	{
	  int hl,hr;	
	  if(root==null)
		  return 0;
	  else
	  {
		  hl=height(root.left);
		  hr=height(root.right);
		  if(hl>=hr)
			  return 1+hl;
		  else
			  return 1+hr;
	  }
	}
	int identifyViolation(node root)
	{
		int leftheight,rightheight;
		
			if(root.left==null)
				leftheight=0;
			else
				leftheight=height(root.left);
			if(root.right==null)
				rightheight=0;
			else
				rightheight=height(root.right);
			if(rightheight-leftheight>=2)
				return 2;
			else if(leftheight-rightheight>=2)
				return -2;
			else
				return leftheight-rightheight;
		
		
	}
	node postorder(node root)
	{
		int stat;
		node returnNodeL,returnNodeR;
		if(root==null)
			return null;
		else
		{
			returnNodeL=postorder(root.left);	
			returnNodeR=postorder(root.right);
			if(returnNodeL!=null || returnNodeR!=null)
				return returnNodeR==null?returnNodeL:returnNodeR;
			stat=identifyViolation(root);
			if(stat==2 || stat==-2)
			{	
				status=stat;
				return root;
			}
			else
			{
				status=stat;
				return null;
			}
		}
		
		
	}
	node findParent(node A)
	{
		node temp=parent;
		node tempParent=temp;
		if(temp==A)
			return null;
		else
		{
			while(true)
			{
				
				if(temp.data<A.data)
				{
					tempParent=temp;
					temp=temp.right;
				}
				else if(temp.data>A.data)
				{
					tempParent=temp;
					temp=temp.left;
				}
				else
					return tempParent;
			}
			
		}
	}
	void performLL(node A,node PA,int value)
	{
		node AC;
		AC=A.left;
		if(PA!=null)
		{
			if(A.data>PA.data)
				PA.right=A.left;
			else
				PA.left=A.left;
		}		
		else
			parent=AC;
		A.left=AC.right;
		AC.right=A;
	
	}
	void performLR(node A,node PA,int value)
	{
		node AC,tempo;
		tempo=PA;
		PA=A;
		A=A.left;
//LR first part
		AC=A.right;
		if(A.data>PA.data)
			PA.right=A.right;
		else
			PA.left=A.right;
		A.right=AC.left;
		AC.left=A;
//LR second part
		A=PA;
		PA=tempo;
		AC=A.left;
		if(PA!=null)
		{
			if(A.data>PA.data)
				
				PA.right=A.left;
			else
				PA.left=A.left;

		}
		else
			parent=AC;
		A.left=AC.right;
		AC.right=A;
	
	}
	void performRL(node A,node PA,int value)
	{
		node AC,tempo;
		tempo=PA;
		PA=A;
		A=A.right;
//RL first part				
		AC=A.left;
		if(A.data>PA.data)
			PA.right=A.left;
		else
			PA.left=A.left;
		A.left=AC.right;
		AC.right=A;
//RL second part
		A=PA;
		PA=tempo;
		AC=A.right;
		if(PA!=null)
		{
			if(A.data>PA.data)
				PA.right=A.right;
			else
				PA.left=A.right;
		}
		else
			parent=AC;
		A.right=AC.left;
		AC.left=A;

	}
	void performRR(node A,node PA,int value)
	{
		node AC;
		AC=A.right;
		if(PA!=null)
		{
			if(A.data>PA.data)
				PA.right=A.right;
			else
				PA.left=A.right;
		}
		else
			parent=AC;
		A.right=AC.left;
		AC.left=A;

	}
	void insert(node element)
	{
		node PA,A;
		while(true)
		{	
			A=postorder(parent);
			if(A==null)
			{
		//		flag=1;
				break;
			}
			PA=findParent(A);
			if(element.data>A.data)
			{
				if(element.data>A.right.data)
					performRR(A,PA,element.data);
				else
					performRL(A,PA,element.data);
			}
			else 
			{
				if(element.data<A.left.data)
					performLL(A,PA,element.data);
				else
					performLR(A,PA,element.data);
			}
		}
	}
	void insertNode(int key)throws Exception
	{
		int value,flag=0;
		node PA,AC,A,tempo;
//		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
//		noOfNodes=Integer.parseInt(reader.readLine());
	//	for (int i = 0; i < noOfNodes; i++) {
			node element=new node();
			element.setData(key);
			value=element.data;
			if(height(parent)==0)
			{	
				parent=new node();
				this.parent.data=value;
			}		
			else
			{
				node startPos=this.parent;		
				while(true)
				{
					while(startPos.left!=null &&value<=startPos.data)
					{
						startPos=startPos.left;
					}	
					if(startPos.left==null&& value<=startPos.data)
					{
						startPos.left=element;
			//			status=identifyViolation(parent);
						insert(element);
						break;
					}

					while(startPos.right!=null && value>startPos.data)
					{
						startPos=startPos.right;
					}
					if(startPos.right==null && value>startPos.data)
					{	
						startPos.right=element;
						insert(element);
						break;
					}
				}	
			}
			
//		}
	}
/*	node findNode(int data)
	{
		node temp=parent;
		while(true)
		{
			if(data<temp.data)
				temp=temp.left;
			else if(data>temp.data)
				temp=temp.right;
			else
				break;			
		}
		return temp;
	}
*/
	void delete(node temp,int key)
	{
		node parentNode=null,leftGreat,leftGreatParent;
		parentNode=findParent(temp);
		int status1,status2;
		if(temp!=null)
		{
			if(key<temp.data)
				delete(temp.left, key);
			else if(key>temp.data)
				delete(temp.right, key);
			else
			{
//			parentNode=findParent(temp);
				if(temp.left==null && temp.right==null)
				{
					if(parentNode==null)
					{
						parent=null;
					}
					else
					{	
						if(key>parentNode.data)
							parentNode.right=null;
						else
							parentNode.left=null;
					}
				}
				else if(temp.left==null || temp.right==null)
				{
					if(parentNode==null)
					{
						if(temp.left==null)
							parent=temp.right;
						else
							parent=temp.left;
					}
					else
					{	
						if(temp.left==null)
						{
							if(key>parentNode.data)
								parentNode.right=temp.right;
							else
								parentNode.left=temp.right;
						}
						else
						{
							if(key>parentNode.data)
								parentNode.right=temp.left;
							else
								parentNode.left=temp.left;
						}				
					}
				}	
				else
				{
					leftGreat=temp.left;
					while(leftGreat.right!=null)
						leftGreat=leftGreat.right;
					leftGreatParent=findParent(leftGreat);
					if(leftGreatParent==temp)
						leftGreatParent.left=leftGreat.left;
					else
						leftGreatParent.right=leftGreat.left;
					temp.data=leftGreat.data;
				}
			
			}
			status1=identifyViolation(temp);
			if(status1==-2)
			{	
				status2=identifyViolation(temp.left);
			
				if(status2>=0)
					performLL(temp, parentNode,temp.data);
				else
					performLR(temp, parentNode,temp.data);
			}
			else if(status1==2)
			{
				status2=identifyViolation(temp.right);
				if(status2<=0)
					performRR(temp, parentNode,temp.data);
				else
					performRL(temp, parentNode,temp.data);
			}	
		}	
	}
	public node getParent() {
		return parent;
	}
	public void setParent(node parent) {
		this.parent = parent;
	}
	void display(node root)
	{
		if(root!=null)
		{	
			System.out.println(root.data);
			display(root.left);
			display(root.right);
		}	
	}
	
	void getLevelOrderdata(node root,int level,int depth)
	{
		if(level==0)
		{
			if(root==null)
				System.out.print(" ");
			else
				System.out.print(root.data);
			for(int j=0;j<Math.pow(2,depth+1)-1;j++)
				System.out.print(" ");
			
		}
		else
		{
			getLevelOrderdata(root.left, level-1, depth);
			getLevelOrderdata(root.right, level-1, depth);
		}
	}
	void displayTree()
	{
		int i,h;
		h=height(parent);
		for(i=0;i<h;i++)
		{
			for(int m=0;m<Math.pow(2, h-i)-1;m++)
				System.out.print(" ");
			getLevelOrderdata(parent,i,h-i);
			System.out.println();
		}
	}
	
}
class prg2
{
	public static void main(String[] args)throws Exception 
	{
		int key,choice,data;
		BST obj=new BST();
		while(true)
		{
			System.out.println("Select the Possible choices");
			System.out.println("1.Insert a node");
			System.out.println("2. Delete a node");
			System.out.println("3.exit");
			BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
			choice=Integer.parseInt(reader.readLine());
			if(choice==1)
			{
				data=Integer.parseInt(reader.readLine());
				obj.insertNode(data);
				System.out.println("Start");
				System.out.println("################################");
				obj.displayTree();
				System.out.println("################################");
				System.out.println("END");
			}
			else if(choice==2)
			{
				data=Integer.parseInt(reader.readLine());
				obj.delete(obj.getParent(),data);
				System.out.println("Start");
				System.out.println("################################");
				obj.displayTree();
				System.out.println("################################");
				System.out.println("END");
			}
			else
			{
				break;
			}
		}
	//	obj.create();
		
		
	
		/*System.out.println("Enter the key to delete");
		while(true)
		{
		key=Integer.parseInt(reader.readLine());
		if(key==-1)
			break;
		obj.delete(obj.getParent(),key);
		obj.display(obj.getParent());
		}*/
		
//		obj.delete(obj.getParent(),key);
		
	}
}
