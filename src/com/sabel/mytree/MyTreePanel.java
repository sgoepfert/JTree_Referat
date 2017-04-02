package com.sabel.mytree;

import javax.swing.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.*;
import java.awt.*;

/**
 * Created by Steve on 01.04.2017.
 */
public class MyTreePanel extends JPanel {
    private DefaultMutableTreeNode rootNode;
    private DefaultTreeModel treeModel;
    private JTree jTree;
    private JScrollPane jScrollPane;
    private Toolkit toolkit = Toolkit.getDefaultToolkit();

    public MyTreePanel() {
        this.setLayout(new GridLayout(1, 0));
        initComponents();
        initEvents();
    }

    private void initEvents() {
        treeModel.addTreeModelListener(new MyTreeModelListener());
    }

    private void initComponents() {
        rootNode = new DefaultMutableTreeNode("Root-Knoten");
        treeModel = new DefaultTreeModel(rootNode);
        jTree = new JTree(treeModel);
        jTree.setEditable(true);
        jTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        //Zeigt Ausklappsymbol am Root-Knoten
        jTree.setShowsRootHandles(true);
        jScrollPane = new JScrollPane(jTree);
        this.add(jScrollPane);
    }

    public void clear() {
        rootNode.removeAllChildren();
        treeModel.reload();
    }

    public void removeCurrentNode() {
        TreePath selected = jTree.getSelectionPath();
        if (selected != null) {
            DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) selected.getLastPathComponent();
            MutableTreeNode parent = (MutableTreeNode) currentNode.getParent();
            if (parent != null) {
                treeModel.removeNodeFromParent(currentNode);
                return;
            }
        }
        toolkit.beep();
    }

    public DefaultMutableTreeNode addNode(DefaultMutableTreeNode parent, Object child, boolean shouldBeVisible) {
        DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);
        if (parent == null) {
            parent = rootNode;
        }
        treeModel.insertNodeInto(childNode, parent, parent.getChildCount());
        System.out.println(parent.getChildCount());
        if (shouldBeVisible) {
            jTree.scrollPathToVisible(new TreePath(childNode.getPath()));
        }
        return childNode;
    }

    public DefaultMutableTreeNode addNode(DefaultMutableTreeNode parent, Object child) {
        return addNode(parent, child, false);
    }

    public DefaultMutableTreeNode addNode(Object child) {
        DefaultMutableTreeNode parent = null;
        TreePath parentPath = jTree.getSelectionPath();
        if (parentPath == null) {
            parent = rootNode;
        } else {
            parent = (DefaultMutableTreeNode) parentPath.getLastPathComponent();
        }
        return addNode(parent, child, true);
    }

    private class MyTreeModelListener implements TreeModelListener {
        @Override
        public void treeNodesChanged(TreeModelEvent e) {
            DefaultMutableTreeNode node;
            node = (DefaultMutableTreeNode) e.getTreePath().getLastPathComponent();
            int index = e.getChildIndices()[0];
            node = (DefaultMutableTreeNode) node.getChildAt(index);

            System.out.println("Bearbeitung abgeschlossen.");
            System.out.println("Neuer Wert: "+node.getUserObject());
        }

        @Override
        public void treeNodesInserted(TreeModelEvent e) {

        }

        @Override
        public void treeNodesRemoved(TreeModelEvent e) {

        }

        @Override
        public void treeStructureChanged(TreeModelEvent e) {

        }
    }
}
