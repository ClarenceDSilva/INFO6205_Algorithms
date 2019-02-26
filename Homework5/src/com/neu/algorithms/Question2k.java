package com.neu.algorithms;

public class Question2k {
	public static Node root;

    public Question2k() {
        root = null;
    }

    public static class Node {

        public Node lChild;
        public Node rChild;
        public int value;
        public int size;

        public Node(int value, int size) {
            lChild = null;
            rChild = null;
            this.value = value;
            this.size = size;
        }
    }
    
    public static int size() {
        return size(root);
    }

    // return number of key-value pairs in BST rooted at x
    private static int size(Node x) {
        if (x == null) return 0;
        else return x.size;
    }
    
    public static void ins(int key) {
        root = insert(root, key);
    }

    public static Node insert(Node root, int key) {
        if (root == null) {
            root = new Node(key, 1);
            return root;
        }

        if (key < root.value) {
            root.lChild = insert(root.lChild, key);
        } else if (key > root.value) {
            root.rChild = insert(root.rChild, key);
        }

        return root;
    }

    public static void inorder(Node n) {
        if (n != null) {
            System.out.print(n.value + " ");
            inorder(n.lChild);
            inorder(n.rChild);
        }
    }

    public static void del(int key) {
        root = delete(root, key);
    }

    public static Node delete(Node root, int key) {
        if (root == null) {
            return root;
        }

        if (key < root.value) {
            root.lChild = delete(root.lChild, key);
        } else if (key > root.value) {
            root.rChild = delete(root.rChild, key);
        } else {
            if (root.lChild == null) {
                return root.rChild;
            } else if (root.rChild == null) {
                return root.lChild;
            }

            root.value = findMin(root.rChild);
            root.rChild = delete(root.rChild, root.value);
        }

        return root;
    }

    static int findMin(Node node) {
        if (node == null) {
            return Integer.MAX_VALUE;
        }

        int res = node.value;
        int lres = findMin(node.lChild);
        int rres = findMin(node.rChild);

        if (lres < res) {
            res = lres;
        }
        if (rres < res) {
            res = rres;
        }
        return res;
    }
   
    
    public static int rank(Node root, int val) {
        int rank = 0;
        while (root != null) {
            if (val < root.value) // move to left subtree
                root = root.lChild;
            else if (val > root.value) {
                rank += 1 + size(root.lChild);
                root = root.rChild;
            } else
                return rank + size(root.lChild);
        }
        return rank; // not found
        
    }
      

    public static void main(String[] args) {
        Question2k bt = new Question2k();
        ins(3);
        ins(7);
        ins(9);
        ins(23);
        ins(45);
        ins(1);
        ins(5);
        ins(14);
        ins(55);
        ins(24);
        ins(13);
        ins(11);
        ins(8);
        ins(19);
        ins(4);
        ins(31);
        ins(35);
        ins(36);
        System.out.println("Inorder traversal of the tree is :");
        inorder(root);
        System.out.println("\n");
        System.out.println("Minimum element is " + bt.findMin(bt.root));
        bt.del(bt.findMin(bt.root));
        inorder(root);
        System.out.println("\n");
        System.out.println("Rank is: " +rank(root, 24));
    }
}
