package com.sabel.booktree;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;

/**
 * Created by Steve on 01.04.2017.
 */
public class BookTree extends JFrame implements TreeSelectionListener {
    private Container c;
    private JScrollPane jScrollPane;
    private JTree jTree;
    private DefaultMutableTreeNode top;

    public BookTree() throws HeadlessException {
        setTitle("BookTree");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        initComponents();
        initEvents();
        setVisible(true);
    }

    private void initComponents() {
        c=getContentPane();
        top = new DefaultMutableTreeNode("<html><font color=red size=+2><b><u>W</u>urzel</b></font></html>");
        createNodes(top);
        jTree = new JTree(top);
        //respond
        jTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        jScrollPane = new JScrollPane(jTree);
        c.add(jScrollPane);
    }

    private void initEvents() {
        jTree.addTreeSelectionListener(this);
    }

    private void createNodes(DefaultMutableTreeNode top) {
        DefaultMutableTreeNode category = null;
        DefaultMutableTreeNode book = null;

        category = new DefaultMutableTreeNode("Bücher für Programmierer");
        top.add(category);

        book = new DefaultMutableTreeNode(new Book("Java Tuorial", "Niemand"));
        category.add(book);
        book = new DefaultMutableTreeNode(new Book("Java Tuorial Continued", "Keiner"));
        category.add(book);
        book = new DefaultMutableTreeNode(new Book("Swing Tutorial", "Jemand"));
        category.add(book);
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree.getLastSelectedPathComponent();
        if (node == null) {
            return;
        }
        Object nodeInfo = node.getUserObject();
        if (node.isLeaf()) {
            Book book = (Book) nodeInfo;
            JOptionPane.showMessageDialog(null, "Buch: "+book.getTitle()+" von "+book.getAuthor(),"Gewähltes Element",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new BookTree();
    }
}
