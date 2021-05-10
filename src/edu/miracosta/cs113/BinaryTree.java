/**
 * Generic BinaryTree class
 */

package edu.miracosta.cs113;

import java.io.Serializable;
import java.util.Scanner;

public class BinaryTree<E> implements Serializable
{
    protected static class Node<E> implements Serializable  //inner class Node 'is' the data structure
    {
        protected final E data ;    //data being stored
        protected Node<E> left ;    //a reference to another node, or null if end of tree (leaf)
        protected Node<E> right ;
        public Node(E data)     //constructor
        {
            this.data = data ;
            this.left = null ;
            this.right = null ;
        }

        public String toString()        //printing a node prints the data
        {
            return data.toString() ;
        }

    }

    //member variable
    protected Node<E> root ;    //the reference connecting to everything in the data structure

    //constructors
    public BinaryTree()
    {        root = null ;    }
    public BinaryTree(Node<E> root)
    {        this.root = root ;    }
    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree)
    {
        root = new Node<E>(data) ;
        if (leftTree != null)
            root.left = leftTree.root ;
        else
            root.left = null ;

        if (rightTree != null)
            root.right = rightTree.root ;
        else
            root.right = null ;
    }

    public BinaryTree<E> getLeftSubtree()
    {
        if (root != null && root.left != null)
            return new BinaryTree<E>(root.left) ;
        else
            return null ;
    }

    public BinaryTree<E> getRightSubtree()
    {
        if (root != null && root.right != null)
            return new BinaryTree<E>(root.right) ;
        else
            return null ;
    }
    public E getData()  //returns the data stored in the Node
    {
        return this.root.data;
    }

    public boolean isLeaf()     //returns true if Node has no more child Nodes
    {
        return (root.left == null && root.right == null) ;
    }
    public String toString()                       //prints contents of BinaryTree with preorder traversal order
    {
        StringBuilder sb = new StringBuilder() ;
        preOrderTraverse(root, 1, sb) ;
        return sb.toString() ;
    }
    private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb)
    {
        for (int i = 1; i < depth ; i++)
            sb.append(" ") ;
        if (node == null)
            sb.append("null\n") ;
        else
        {
            sb.append(node.toString() + "\n") ;
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth + 1, sb);
        }
    }

    public static BinaryTree<String> readBinaryTree(Scanner scan)
    {
        String data = scan.next() ;
        if (data.equals("null"))
            return null ;
        else
        {
            BinaryTree<String> leftTree = readBinaryTree(scan) ;
            BinaryTree<String> rightTree = readBinaryTree(scan) ;
            return new BinaryTree<String>(data, leftTree, rightTree) ;
        }
    }
}
