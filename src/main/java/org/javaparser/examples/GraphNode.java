package org.javaparser.examples;

import com.github.javaparser.ast.Node;

public class GraphNode {

    private Node node;
    private boolean isToken;
    private boolean isWrriten;
    private boolean isUsed;
    private NodeFeature nodeFeature;

    GraphNode(Node node){
        this.node = node;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public boolean isToken() {
        return isToken;
    }

    public void setToken(boolean token) {
        isToken = token;
    }

    public boolean isWrriten() {
        return isWrriten;
    }

    public void setWrriten(boolean wrriten) {
        isWrriten = wrriten;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public NodeFeature getNodeFeature() {
        return nodeFeature;
    }

    public void setNodeFeature(NodeFeature nodeFeature) {
        this.nodeFeature = nodeFeature;
    }

}

