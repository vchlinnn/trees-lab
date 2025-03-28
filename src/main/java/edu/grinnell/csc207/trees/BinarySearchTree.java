package edu.grinnell.csc207.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * A binary tree that satisifies the binary search tree invariant.
 */
public class BinarySearchTree<T extends Comparable<? super T>> {

    ///// From the reading

    /**
     * A node of the binary search tree.
     */
    private static class Node<T extends Comparable<? super T>> {
        T value;
        Node<T> left;
        Node<T> right;

        /**
         * @param value the value of the node
         * @param left the left child of the node
         * @param right the right child of the node
         */
        Node(T value, Node<T> left, Node<T> right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        /**
         * @param value the value of the node
         */
        Node(T value) {
            this(value, null, null);
        }
    }

    private Node<T> root;

    /**
     * Constructs a new empty binary search tree.
     */
    public BinarySearchTree() { }

    private int sizeH(Node<T> node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + sizeH(node.left) + sizeH(node.right);
        }
    }

    /**
     * @return the number of elements in the tree
     */
    public int size() {
        return sizeH(root);
    }

    private Node<T> insertH(T value, Node<T> root) {
        if (root == null) {
            return new Node<T>(value);
        } else {
            if (value.compareTo(root.value) < 0) {
                root.left = insertH(value, root.left);
            } else {
                root.right = insertH(value, root.right);
            }
            return root;
        }
    }

    /**
     * @param value the value to add to the tree
     */
    public void insert(T value) {
        root = insertH(value, root);
    }

    ///// Part 1: Traversals

    /**
     * @return the elements of this tree collected via an in-order traversal
     */
    public List<T> toListInorder() {
        List<T> treeList = new ArrayList<>();
        listIOH(treeList, root);
        return treeList;
    }


    private void listIOH(List<T> treeList, Node<T> root){
        if (root == null) {
            return;
        } else {
            listIOH(treeList, root.left);
            treeList.add(root.value);
            listIOH(treeList, root.right);
            return;
        }
    }
    /**
     * @return the elements of this tree collected via a pre-order traversal
     */
    public List<T> toListPreorder() {
        List<T> treeList = new ArrayList<>();
        listH(treeList, root);
        return treeList;
    }
    


    private void listH(List<T> treeList, Node<T> root){
        if (root == null) {
            return;
        } else {
            treeList.add(root.value);
            listH(treeList, root.left);
            listH(treeList, root.right);
            return;
        }
    }

    /**
     * @return the elements of this tree collected via a post-order traversal
     */
    public List<T> toListPostorder() {
        List<T> treeList = new ArrayList<>();
        listPOH(treeList, root);
        return treeList;
    }

    private void listPOH(List<T> treeList, Node<T> root){
        if (root == null) {
            return;
        } else {
            listPOH(treeList, root.left);
            listPOH(treeList, root.right);
            treeList.add(root.value);
            return;
        }
    }

    ///// Part 2: Contains

    /**
     * @param value the value to search for
     * @return true iff the tree contains <code>value</code>
     */
    public boolean contains(T value) {
        Node<T> searchnode = this.root;
        for (;searchnode != null;){
            if (searchnode.value.compareTo(value) == 0){
                return true;
            }
            if ((searchnode.value).compareTo(value) > 0){
                searchnode = searchnode.left;
            }
            else{
                searchnode = searchnode.right;
            }
        }
        return false;
    }

    ///// Part 3: Pretty Printing

    /**
     * @return a string representation of the tree obtained via an pre-order traversal in the
     *         form: "[v0, v1, ..., vn]"
     */
    public String toStringPreorder() {
        List treelist = toListPreorder();
        StringBuffer buf = new StringBuffer("[");
        if (treelist.size() > 0) {
            buf.append(treelist.get(0));
            for (int i = 1; i < treelist.size(); i++) {
                buf.append(", ");
                buf.append(treelist.get(i));
            }
        }
        buf.append("]");
        return buf.toString();
    }

    ///// Part 4: Deletion
  
    /*
     * The three cases of deletion are:
     * 1. (TODO: fill me in!)
     * 2. (TODO: fill me in!)
     * 3. (TOOD: fill me in!)
     */

    /**
     * Modifies the tree by deleting the first occurrence of <code>value</code> found
     * in the tree.
     *
     * @param value the value to delete
     */
    public void delete(T value) {
        Node<T> deleteNode = this.root;
        Node<T> parentNode = this.root;
        boolean direction = true; // left: true, right: false
        for (;deleteNode != null;){
            if (deleteNode.value.compareTo(value) == 0){
                break;
            }
            if ((deleteNode.value).compareTo(value) > 0){
                parentNode = deleteNode;
                deleteNode = deleteNode.left;
                direction = true;
            }
            else {
                parentNode = deleteNode;
                deleteNode = deleteNode.right;
                direction = false;
            }
        }


        Node<T> leftPart = deleteNode.left;
        Node<T> rightPart = deleteNode.right;

        if (leftPart == null) {
            if (direction == true) {
                parentNode.left = rightPart;
            } else {
                parentNode.right = rightPart;
            }
            return;
        } else if (rightPart == null) {
            if (direction == true) {
                parentNode.left = leftPart;
            } else {
                parentNode.right = leftPart;
            }
            return;
        }

        if (direction == true) {
            parentNode.left = leftPart;
        } else {
            parentNode.right = leftPart;
        }

        Node<T> currNode = leftPart;

        for (;currNode.right != null;){
            currNode = currNode.right;
        }
        currNode.right = rightPart;
    }

}
