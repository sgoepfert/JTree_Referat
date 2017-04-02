package com.sabel.mytree;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Steve on 02.04.2017.
 */
public class MyTreeWindow extends JFrame implements ActionListener{
    private int nodeIndex =1;
    private static final String ADD_NODE = "add";
    private static final String REMOVE_NODE = "remove";
    private static final String CLEAR_NODE = "clear";
    private Container c;
    private MyTreePanel myTreePanel;
    DefaultMutableTreeNode p1, p2;
    private JPanel jpSouth;
    private JButton jbAdd, jbRemove, jbClear;

    public MyTreeWindow() throws HeadlessException {
        this.setTitle("MyTree");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        initComponents();
        initTree(myTreePanel);
        initEvents();
        pack();
        this.setVisible(true);
    }

    private void initTree(MyTreePanel myTreePanel) {
        String parent1Name = new String("Parent 1");
        String parent2Name = new String("Parent 2");
        String child1Name = new String("Child 1");
        String child2Name = new String("Child 2");

        p1 = myTreePanel.addNode(null, parent1Name);
        p2 = myTreePanel.addNode(null, parent2Name);

        myTreePanel.addNode(p1, child1Name);
        myTreePanel.addNode(p1, child2Name);
        myTreePanel.addNode(p2, child1Name);
        myTreePanel.addNode(p2, child2Name);
    }

    private void initComponents() {
        c = getContentPane();
        myTreePanel = new MyTreePanel();
        jpSouth = new JPanel(new GridLayout(0, 3));
        jbAdd = new JButton("<html>Knoten <u>hinzufügen</u></html>");
        jbRemove = new JButton("<html>Knoten <u>entfernen</u></html>");
        jbClear = new JButton("<html>Baum <u>leeren</u></html>");
        jpSouth.add(jbAdd);
        jpSouth.add(jbRemove);
        jpSouth.add(jbClear);
        c.add(myTreePanel, BorderLayout.NORTH);
        c.add(jpSouth, BorderLayout.SOUTH);
    }

    private void initEvents() {
        jbAdd.setActionCommand(ADD_NODE);
        jbAdd.addActionListener(this);

        jbRemove.setActionCommand(REMOVE_NODE);
        jbRemove.addActionListener(this);

        jbClear.setActionCommand(CLEAR_NODE);
        jbClear.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case ADD_NODE:
                myTreePanel.addNode("Neuer Knoten " + nodeIndex++);
                break;
            case REMOVE_NODE:
                myTreePanel.removeCurrentNode();
                break;
            case CLEAR_NODE:
                myTreePanel.clear();
                nodeIndex=1;
                break;
            default: return;
        }
    }

    public static void main(String[] args) {
        new MyTreeWindow();
    }
}
