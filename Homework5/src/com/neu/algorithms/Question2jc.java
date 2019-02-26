package com.neu.algorithms;

public class Question2jc {

	public static void main(String[] args) {
		
		int arr[] = { 3,7,9,23,45,1,5,14,55,24,13,11,8,19,4,31,35,56 }; 
		TwoThreeFourTree t = new TwoThreeFourTree();
    	
    	for(int elem : arr)	
    		t.insert(elem);
    	
        System.out.println(t.find(4));
        System.out.println(t.find(31));
        System.out.println(t.find(35));
        System.out.println(t.find(56));
		
	}
}

class TwoThreeFourTree {

	Node root = new Node(); 
	
    public int find(int key) {
        Node current = root;
        int pos;
        while(true) {
         if((pos = current.nodeSearch(key)) != -1) return pos;              
         else if(current.isLeaf()) return -1;                        
         else current = getNextChild(current, key);
        }  
    }

    public void insert(int key) {
        Node current = root;
        while(true){
            if(current.maxdataReached()){
                split(current);                  
                current = current.getParent();    
                current = getNextChild(current, key);
            }  
            else if(current.isLeaf()) break;                            
            else current = getNextChild(current, key);
        }  
        current.nodeInsert(key);      
    }  

    public void split(Node thisNode){
        int temp1, temp2;
        Node parent, c1, c2;
        int pos;
        temp1 = thisNode.removeItem();    
        temp2 = thisNode.removeItem();  
        c1 = thisNode.disconnect(2); 
        c2 = thisNode.disconnect(3); 

        Node childRight = new Node();       
        if(thisNode==root)                
        {
            root = new Node();                
            parent = root;                    
            root.connectChildtoParent(0, thisNode);   
        }
        else parent = thisNode.getParent();    
        
        pos = parent.nodeInsert(temp1); 
        int n = parent.getItemCount(); 
                
        for(int j=n-1; j>pos; j--)          
        {                                     
            Node temp = parent.disconnect(j); 
            parent.connectChildtoParent(j+1, temp);        
        }
        parent.connectChildtoParent(pos+1, childRight);

        childRight.nodeInsert(temp2);       
        childRight.connectChildtoParent(0, c1);
        childRight.connectChildtoParent(1, c2); 
    }  

     public Node getNextChild(Node theNode, int key){
            int j;
            int numItems = theNode.getItemCount();
            for(j=0; j<numItems; j++) {                               
                if( key < theNode.getItem(j)){
                    return theNode.getChild(j); 
                }
            }          
            return theNode.getChild(j);       
        }
	
    
	public class Node {
	
	    //data members for class Node
		static final int KEY_SIZE = 3;
		Node parent;
		int totalItemCount;		
		int data[] = {0,0,0};
		Node childPointers[] = new Node[KEY_SIZE+1];
		
	    	
		public int nodeSearch(int item){
			for(int i = 0; i <= totalItemCount; i++){
				if(data[i] == item) return i;
			}
			return -1;
		}
		
		public void connectChildtoParent(int childPos, Node n){
		    childPointers[childPos] = n;
		    if(n != null) n.parent = this;
		}
		
		public Node disconnect(int childPos){
		    Node temp = childPointers[childPos];
		    childPointers[childPos] = null;
		    return temp;
		}			
		
		public int removeItem(){
		    int temp = data[2];
		    data[2] = 0;
		    totalItemCount--;
		    return temp; 
		}
		
        public int nodeInsert(int key){
            totalItemCount++;
                                
            for(int j=KEY_SIZE-1; j>=0; j--)       
            {                                
                if(data[j] == 0) continue;                      
                else {
                    int keyPtr = data[j];
                    if(key < keyPtr) data[j+1] = data[j];
                    else {
                       data[j+1] = key;
                       return j+1;   
                    }
                }                           
            }                       
            data[0] = key;
            return 0;              
        }
		
		public Node getParent(){
			return parent;
		}
		
		public Node getChild(int childPos){
			return childPointers[childPos];
		}
		
		public int getItem(int itemPos){
		    return data[itemPos];
		}
		
		public int getItemCount(){
			return totalItemCount;
		}
		
        public boolean isLeaf(){
            return(empty() == true) ? true : false;
        }
		
		public boolean empty(){
            for(int i = 0; i < KEY_SIZE; i++){
                if(data[i] != 0) return false;
            }
            return true;
        }
		
		public boolean maxdataReached(){
			return(totalItemCount == KEY_SIZE) ? true : false;
		}

		
	} //end class Node

}
