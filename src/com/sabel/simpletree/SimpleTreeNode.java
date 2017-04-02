package com.sabel.simpletree;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.awt.*;

/**
 * Created by Steve on 01.04.2017.
 */
public class SimpleTreeNode extends JFrame {
    private Container c;
    private JScrollPane jScrollPane;
    private JTree jTree;
    private TreeNode root;

    public SimpleTreeNode() throws HeadlessException {
        setTitle("SimpleTreeNode");
        setLocationRelativeTo(null);
        setSize(500, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initComponents();
        setVisible(true);

    }

    private static TreeNode createTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode( "Wurzel" );

        DefaultMutableTreeNode letters = new DefaultMutableTreeNode( "Buchstaben" );
        DefaultMutableTreeNode numbers = new DefaultMutableTreeNode( "Zahlen" );

        DefaultMutableTreeNode letterA = new DefaultMutableTreeNode( "A" );
        DefaultMutableTreeNode letterB = new DefaultMutableTreeNode( "B" );
        DefaultMutableTreeNode letterC = new DefaultMutableTreeNode( "C" );

        DefaultMutableTreeNode number1 = new DefaultMutableTreeNode( "1" );
        DefaultMutableTreeNode number2 = new DefaultMutableTreeNode( "2" );
        DefaultMutableTreeNode number3 = new DefaultMutableTreeNode( "3" );

        letters.add( letterA );
        letters.add( letterB );
        letters.add( letterC );

        numbers.add( number1 );
        numbers.add( number2 );
        numbers.add( number3 );

        root.add( letters );
        root.add( numbers );

        return root;
    }

    private void initComponents() {
        c = getContentPane();
        root = createTree();
        jTree = new JTree(root);
        jScrollPane = new JScrollPane(jTree);
        c.add(jScrollPane);

    }

    public static void main(String[] args) {
        new SimpleTreeNode();
    }
}
