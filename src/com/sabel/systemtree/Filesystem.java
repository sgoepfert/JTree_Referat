package com.sabel.systemtree;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import java.awt.*;
import java.io.File;

/**
 * Created by Steve on 01.04.2017.
 */
public class Filesystem extends JFrame {
    private JTree jTree;
    private JScrollPane jScrollPane;
    private Container c;

    public Filesystem() throws HeadlessException {
        setTitle("Filesystem");
        //setSize(500, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        setVisible(true);
        pack();
    }

    private void initComponents() {
        c = getContentPane();
        jTree = new JTree();
        jScrollPane = new JScrollPane(jTree);
        buildTreeModel(new File("e:/"),jTree);
        c.add(jScrollPane, BorderLayout.CENTER);
    }
    
    public void buildTreeModel(File file, JTree jTree) {
        jTree.setModel(new DefaultTreeModel(new FileSystemTreeNode(file)));
    }

    private class FileSystemTreeNode extends DefaultMutableTreeNode {
        public FileSystemTreeNode(File file) {
            setUserObject(file);
        }

        public int getChildCount() {
            return ((File) getUserObject()).list().length;
        }

        public TreeNode getChildAt(int index) {
            return new FileSystemTreeNode(((File) getUserObject()).listFiles()[index]);
        }

        public boolean isLeaf() {
            return !((File) getUserObject()).isDirectory();
        }

        public String toString() {
            return ((File) getUserObject()).getName();
        }
    }

    public static void main(String[] args) {
        new Filesystem();
    }
}
