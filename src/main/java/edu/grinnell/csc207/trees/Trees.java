package edu.grinnell.csc207.trees;

import java.util.ArrayList;

/*
 * Description of the preorder traversal:
 * 
+ Leaf: 
+ A node: 1 + size(left) + size(right)
 */
/**
 * The driver for the Trees program.
 */
public class Trees<T extends Comparable<T>>{
    private class Node<T>{
        private T data;
        private Node left;
        private Node right;

        // Method overloading 
        public Node(T data, Node left, Node right){
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public Node(T data){
            this(data, null, null);
        }

        public void changeLeft(Node left){
            this.left = left;
        }

        public void changeRight(Node right){
            this.right = right;
        }
    }

    // Fields 
    private Node root;

    public Trees(){ } // this.root = null;

    public Trees(T data){
        this.root = new Node<>(data);
    }

    public int size(){
        return sizeH(root);
    }

    private int sizeH(Node root){
        if (root == null){
            return 0;
        } else {
            return 1 + sizeH(root.left) + sizeH(root.right);
        }
    }

    /**
     * The main entry point of the program.
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }


}
