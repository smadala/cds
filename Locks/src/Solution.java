import java.util.LinkedList;
import java.util.Queue;


public class Solution {
	public static class Node{
	    int val;
	    Node left;
	    Node right;
	    public Node(int val){
	        if(val == 1){
	            this.val=1;
	            left=new Node(0);
	            right=new Node(0);
	        }else{
	            this.val=0;
	        }
	    }
	}
		static String post="";
	    static String postOrder(String T) {
	    	char ch ;
	    	Node root=null;
	    	post="";
	    	Queue<Node> q=new LinkedList<Node>();
	    	if(T.length()>0){
	    		root=new Node(Integer.parseInt(T.charAt(0)+""));
	    		q.offer(root);
	    	}else{
	    		return "";
	    	}
	    	Node cur=null,temp;
	        for(int i=1;i<T.length();i++){
	        	if(i%2 == 1){
	        		cur=q.poll();
	        	}
	        	ch=T.charAt(i);
	        	temp=new Node(Integer.parseInt(ch+""));
	        	if(ch == '1'){
	        		q.offer(temp);
	        	}
	        	if(i%2==1){
	        		cur.left=temp;
	        	}else{
	        		cur.right=temp;
	        	}
	        }
	        toPostOrder(root);
	        return post;
	    }
	    static void toPostOrder(Node root){
	    	if(root.val == 1){
	    		toPostOrder(root.left);
	    		toPostOrder(root.right);
	    		post=post+"1";
	    	}else{
	    		post=post+"0";
	    	}
	    }


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(postOrder("000000"));
		System.out.println(postOrder("1"));
	}

}
