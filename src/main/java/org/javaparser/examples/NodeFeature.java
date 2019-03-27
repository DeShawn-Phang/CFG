package org.javaparser.examples;

import java.util.Set;

public class NodeFeature {

    private Set inEdges;
    private Set outEdges;
    private String nodeClass;

    @Override
    public String toString() {
        return "NodeFeature{" +
                "inEdges=" + inEdges +
                ", outEdges=" + outEdges +
                ", nodeClass='" + nodeClass + '\'' +
                '}';
    }

    public Set getInEdges() {
        return inEdges;
    }

    public void setInEdges(Set inEdges) {
        this.inEdges = inEdges;
    }

    public Set getOutEdges() {
        return outEdges;
    }

    public void setOutEdges(Set outEdges) {
        this.outEdges = outEdges;
    }

    public String getNodeClass() {
        return nodeClass;
    }

    public void setNodeClass(String nodeClass) {
        this.nodeClass = nodeClass;
    }
}
