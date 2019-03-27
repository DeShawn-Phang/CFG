package buildgraph;

import com.google.common.graph.*;

import java.util.HashSet;
import java.util.Set;

public class BuildGraph {

    public static void main(String[] args){

        //构造图
        MutableGraph<Integer> graph = GraphBuilder.directed().build();
        MutableNetwork<String, Integer> network = NetworkBuilder.directed()
                .allowsParallelEdges(true).nodeOrder(ElementOrder.insertion())
                .expectedNodeCount(100).expectedEdgeCount(100).build();

        //往图中添加一条边，如果图中还没有出现对应的两个端点时，两个端点则会静默添加到图中
        graph.putEdge(1,2);

        //判断节点是否在图中
        if (graph.nodes().contains(1)){
            System.out.println("ok");
        }

        //判断节点之间是否存在边
        System.out.println(graph.hasEdgeConnecting(1,2));

        //判断1的后继是否包含2
        System.out.println(graph.successors(2).contains(1));

        //对于network
        network.addNode("C");
        network.addEdge("A","B",5);
        network.addEdge("A","B",6);
        Set<String> successorsOfA = network.successors("A");
        Set<String> successorsOfB = network.successors("B");
        Set<Integer> outEdgesOfA = network.outEdges("A");
        System.out.println(
                network.toString() +

                //当平行边存在时，下面这条语句不能使用
                network.edgeConnecting("A","C").isPresent() +
                network.edgesConnecting("A","B").isEmpty() +
                successorsOfA.toString() +
                successorsOfB.toString() +
                outEdgesOfA.toString()
        );

        //获取节点的邻接点列表
        Set<Integer> adjacents = graph.adjacentNodes(1);

        //获取生成子图
        Set<Integer> subNodes = new HashSet<>();
        subNodes.add(1);
        MutableGraph<Integer> subgraph = Graphs.inducedSubgraph(graph,subNodes);

        //图的遍历（深度优先-后序）
        Iterable<Integer> dfs = Traverser.forGraph(graph).depthFirstPostOrder(1);
        for(Integer i:dfs){
            System.out.println(i);
        }
        System.out.println(dfs.toString());
    }

}