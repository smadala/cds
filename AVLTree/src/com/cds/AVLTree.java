package com.cds;

import java.util.LinkedList;
import java.util.Queue;

public class AVLTree {
	private TreeNode rootNode;
	
	
	private static class TreeNode{
		private Integer data;
		private TreeNode left;
		private TreeNode right;
		private int height;
		public int getData() {
			return data;
		}
		public void setData(int data) {
			this.data = data;
		}
		public TreeNode getLeft() {
			return left;
		}
		public void setLeft(TreeNode left) {
			this.left = left;
		}
		public TreeNode getRight() {
			return right;
		}
		public void setRight(TreeNode right) {
			this.right = right;
		}
		public int getHeight() {
			return height;
		}
		public void setHeight(int height) {
			this.height = height;
		}
		
	}
   	public void insert(int data){
   		System.out.println("inserting: "+ data);
   		rootNode = insert(rootNode,data);
   	}
   	private int height(TreeNode node){
   		return node != null ? node.getHeight(): -1;
   	}
	
	private TreeNode insert(TreeNode rootNode,int data){
		if(rootNode == null){
			rootNode=new TreeNode();
			rootNode.setData(data);
			return rootNode;
		}
		if( rootNode.getData() > data ){
			rootNode.setLeft( insert(rootNode.getLeft(),data));
			if( height(rootNode.getLeft()) - height(rootNode.getRight()) >= 2){
				if (data > rootNode.getLeft().getData())
					rootNode=rotateRightLeft(rootNode);
				else
					rootNode=rotateLeft(rootNode);
			}
		}
		else if( rootNode.getData() < data){
			rootNode.setRight(insert(rootNode.getRight(),data));
			if( height(rootNode.getRight()) - height(rootNode.getLeft()) >= 2){
				if( data < rootNode.getRight().getData())
					rootNode=rotateLeftRight(rootNode);
				else
					rootNode=rotateRight(rootNode);
			}
			
		}
		else
			return rootNode;
		rootNode.setHeight(Math.max(height(rootNode.getLeft()),height(rootNode.getRight()) )+1);
		return rootNode;
	}
	private TreeNode rotateLeft(TreeNode node){
		TreeNode nodeLeft=node.getLeft();
		node.setLeft(nodeLeft.getRight());
		nodeLeft.setRight(node);
		node.setHeight(Math.max(height(node.getLeft()),height(node.getRight()) ) + 1);
		nodeLeft.setHeight(Math.max(height(nodeLeft.getLeft()),height(nodeLeft.getRight()) ) + 1);
		return nodeLeft;
	}
	private TreeNode rotateRight(TreeNode node){
		TreeNode nodeRight=node.getRight();
		node.setRight(nodeRight.getLeft());
		nodeRight.setLeft(node);
		node.setHeight(Math.max(height(node.getLeft()),height(node.getRight()) ) + 1);
		nodeRight.setHeight(Math.max(height(nodeRight.getLeft()),height(nodeRight.getRight()) ) + 1);
		return nodeRight;
	}
	private TreeNode rotateRightLeft(TreeNode node){
		node.setLeft(rotateRight(node.getLeft()));
		node=rotateLeft(node);
		return node;
	}
	
	private TreeNode rotateLeftRight(TreeNode node){
		node.setRight(rotateLeft(node.getRight()));
		node=rotateRight(node);
		return node;
	}
	
	public boolean find(int data){
		return find(rootNode,data) != null;
	}
	private TreeNode find(TreeNode node,int data){
		if(node == null)
			return null;
		if(node.getData() > data)
			return find(node.getLeft(),data);
		else if(node.getData() < data)
			return find(node.getRight(), data);
		return node;
	}
	public void printLevelOrder(){
		Queue<TreeNode> q = new  LinkedList<TreeNode>();
		if(rootNode == null)
			return;
		q.add(rootNode);
		
		while(!q.isEmpty()){
			TreeNode cur=q.remove();
			System.out.print(cur.getData() +" ");
			if(cur.getLeft() != null ) q.add(cur.getLeft());
			if(cur.getRight() != null ) q.add(cur.getRight());
		}
	}
	private String addSpaces(String s,int num){
		for(int i=0;i<num;i++){
			s+=" ";
		}
		return s;
	}
	public void printTree(){
		Queue<TreeNode> q = new  LinkedList<TreeNode>();
		int maxNumOfNodes=(int)Math.pow(2, rootNode.getHeight()+1);
		Integer elements[] =new Integer[maxNumOfNodes];
		TreeNode nodes[] = new TreeNode[maxNumOfNodes];
		
		int elePos=0;
		if(rootNode == null)
			return;
		elements[++elePos]=rootNode.getData();
		nodes[elePos]=rootNode;
		while( 2 * elePos < maxNumOfNodes){
			if(nodes[elePos] == null ){
				elements[2* elePos] = null;
				elements[2* elePos + 1]=null;
				nodes[2 * elePos] =null; 
				nodes[2* elePos +1]=null;
			}else {
				elements[2* elePos] = nodes[elePos].getLeft() == null?null:nodes[elePos].getLeft().getData();
				elements[2* elePos + 1]=nodes[elePos].getRight() == null ?null:nodes[elePos].getRight().getData();
				nodes[2 * elePos] =nodes[elePos].getLeft(); 
				nodes[2* elePos +1]=nodes[elePos].getRight();
			}
			elePos++;
		}
		int aux=maxNumOfNodes, start=maxNumOfNodes/2, prevStart=maxNumOfNodes,cur;
		int height=rootNode.getHeight();
		elePos=1;
		while(start > 0 ){
			
			String line="";
			line = addSpaces(line, start -1);
			line+= elements[elePos]==null?" ":elements[elePos];
			elePos++;
			cur=start;
			while(cur+prevStart < maxNumOfNodes){
				line = addSpaces(line, prevStart-1);
				line+=elements[elePos]==null?" ":elements[elePos];
				elePos++;
				cur=cur+prevStart;
			}
			System.out.println(line);
			for(int i=0;i<height;i++)
				System.out.println();
			height--;
			start/=2;
			prevStart/=2;
		}
		System.out.println();
	}
	public void delete(int data){
		System.out.println("deleting: "+ data);
		rootNode=delete(rootNode,data);
	}
	
	private TreeNode findMin(TreeNode node){
		if(node == null)
			return null;
		TreeNode temp=node;
		while(temp.getLeft() != null){
			temp=temp.getLeft();
		}
		return temp;
	}
	private TreeNode findMax(TreeNode node){
		if(node == null)
			return null;
		TreeNode temp=node;
		while(temp.getRight() != null){
			temp=temp.getRight();
		}
		return temp;
	}
	private TreeNode delete(TreeNode rootNode,int data){
		if(rootNode == null){
			//System.out.println("element  "+data+" doesn't exist ");
			return null;
			
		}
		if(rootNode.getData() > data){
			rootNode.setLeft(delete(rootNode.getLeft(),data));
			int h= rootNode.getLeft() == null?0:rootNode.getLeft().getHeight();
			if(rootNode.getRight()!=null && rootNode.getRight().getHeight() - h >=2){
				int rh = rootNode.getRight().getRight() != null ? rootNode.getRight().getRight().getHeight() : 0;
	            int lh = rootNode.getRight().getLeft()!= null ? rootNode.getRight().getLeft().getHeight()  : 0;
	  
	              if(rh >= lh)
	                  rootNode = rotateLeft(rootNode);            
	              else
	                  rootNode = rotateLeftRight(rootNode);
			}
		}
		else if(rootNode.getData() < data){
			rootNode.setRight(delete(rootNode.getRight(),data));
			int h= rootNode.getRight() == null?0:rootNode.getRight().getHeight();
			if(rootNode.getLeft()!=null && rootNode.getLeft().getHeight() - h >=2){
				int lh = rootNode.getLeft().getLeft() != null ? rootNode.getLeft().getLeft().getHeight() : 0;
	              int rh = rootNode.getLeft().getRight()!= null ? rootNode.getLeft().getRight().getHeight()  : 0;
	  
	              if(lh >= rh)
	                  rootNode = rotateRight(rootNode);            
	              else
	                  rootNode = rotateRightLeft(rootNode);
			}
		}
		
		 else if(rootNode.left != null) {
	          rootNode.setData(findMax(rootNode.getLeft()).getData());
	          rootNode.setLeft(delete(rootNode.getLeft(), rootNode.getData()));
	          int h=rootNode.getLeft() != null?rootNode.getLeft().getHeight():0; 
	       
	          if((rootNode.getRight() != null) && (rootNode.getRight().getHeight() - h >= 2)) {
	              int rh = rootNode.getRight().getRight() != null ? rootNode.getRight().getRight().getHeight(): 0;
	              int lh= rootNode.getRight().getLeft() != null ? rootNode.getRight().getLeft().getHeight() : 0;
	       
	              if(rh >= lh)
	            	  rootNode = rotateLeft(rootNode);            
	              else
	            	  rootNode= rotateLeftRight(rootNode);
	          }
	      }
	       
	      else{
	          rootNode = rootNode.getRight();
	          if(rootNode != null){
	        	  
	          }
	      }
		
		
			if(rootNode  != null) {
		          int lh = rootNode .getLeft() != null ? rootNode .getLeft().getHeight() : 0;
		          int rh= rootNode .getRight()!= null ? rootNode .getRight().getHeight() : 0;
		          rootNode.setHeight( Math.max(lh,rh) + 1);
		      }
		      return rootNode;
		/*TreeNode nodeToDelete=find(rootNode,data);
		if(nodeToDelete == null) {
			System.out.println("element "+data+" not found");
			return ;
		}
		if(nodeToDelete.getLeft() == null && nodeToDelete.getRight() == null ){
			nodeToDelete=null;
		}
		else if(nodeToDelete.getRight() != null){
			 TreeNode next=findMin(nodeToDelete.getRight());
			 nodeToDelete.setData(next.getData());
			 delete(nodeToDelete.getRight(),next.getData());
		}else{
			TreeNode prev=findMax(nodeToDelete.getLeft());
			nodeToDelete.setData(prev.getData());
			delete(nodeToDelete.getLeft(),prev.getData());
		}*/
	}	
}
