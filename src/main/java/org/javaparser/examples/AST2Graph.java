package org.javaparser.examples;

import com.github.javaparser.JavaToken;
import com.github.javaparser.Token;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.DataKey;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.printer.XmlPrinter;
import com.google.common.graph.ElementOrder;
import com.google.common.graph.MutableNetwork;
import com.google.common.graph.Network;
import com.google.common.graph.NetworkBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

//连接AST的关联结点使其成为一个图
public class AST2Graph {

    private CompilationUnit compilationUnit;
    private MutableNetwork<Node,String> mutableNetwork;

    private Node ch1,ch2;

    AST2Graph(CompilationUnit compilationUnit){

        //获取编译单元并初始化图
        this.compilationUnit = compilationUnit;
        this.mutableNetwork = NetworkBuilder.directed()
                .allowsParallelEdges(true).nodeOrder(ElementOrder.insertion()).allowsSelfLoops(true)
                .expectedNodeCount(10000).expectedEdgeCount(10000).build();

        this.ch1 = compilationUnit;
    }

    //构造并返回由AST构造的图
    public MutableNetwork<Node,String> construct(){

//  TODO:用于测试 Print 方法，待删除
//        XmlPrinter xmlPrinter = new XmlPrinter(true);
//        System.out.println(xmlPrinter.output(compilationUnit));
//        String s = ConcreteSyntaxModel.genericPrettyPrint(compilationUnit);
//        System.out.println(s);

        //用 Child 边连接子节点
        connectChildNode(this.compilationUnit);

        //用 NextToken 边连接每个语法token的兄弟结点
        connectNextToken(this.compilationUnit);

        /*
        capture控制流和数据流：
            用 LastRead 边和 LastWrite 边连接上次读、写
            用 ComputedFrom 边连接赋值语句等号两边的 token
            通过 LastLexicalUse 边连接对所有相同变量的使用
            用 ReturnsTo 边连接 return token 到方法声名上
            用 FormalArgName 边将实参连接到形参
            将所有与一个变量有关的 token 用边 GuardedBy 和 GuardedByNegation 连接为闭合的保护表达式
         */
        connectLastRead(this.compilationUnit);
        connectLastWrite(this.compilationUnit);
        connectComputedFrom(this.compilationUnit);
        connectLastLexicalUse(this.compilationUnit);
        connectReturnsTo(this.compilationUnit);
        ConnectFormalArgName(this.compilationUnit);



        return this.mutableNetwork;
    }

    private void connectChildNode(Node parent) {

//  TODO:用于测试 token 的获取，待删除
//        parent.getTokenRange().get().getBegin().getNextToken().get();

        //READ
        System.out.println(parent.toString() + "-->" + parent.getClass().toString());
        if(!isLeaf(parent)){

            //如果传入的结点不是叶子节点，就获得其子结点并连线，同时添加到图中，然后对子结点进行递归
            List<Node> children = parent.getChildNodes();
            for (Node child:children
                 ) {
                mutableNetwork.addEdge(parent,child,"Child"+parent.getRange()+child.getRange());
                connectChildNode(child);
            }
        }else{

//            //如果结点是叶子节点，就获取叶子节点的范围和 token 的范围，并用Child连接范围内所有的 token
//            /*
//                这里有几个问题：
//                    1、childNode 和 token 并不一一对应，应该如何连接
//                    2、JavaToken 属于 Node 类型吗？不妨用代码试试
//                当前的处理：
//                    1、先将叶子结点和直系 token 用 Child 连接，其他先不管
//                    2、JavaToken 不属于 Node
//                进一步的想法：
//                    1、先把 leaf 当作 token 来处理，如果需要的话，再进一步引入 token
//             */

//   TODO:这里发现 token 不属于 Node，需要新建一个 GraphNode 类才行兼容两者，有待讨论，暂不处理
//            //token 的获取，先得到 nextToken，再用 previousToken 反向得到当前 token
//            JavaToken token = parent.getTokenRange().get().getBegin().
//                    getNextToken().get().getPreviousToken().get();
//            Token token2 = new Token();

//  TODO:之前用的把叶子结点当 token 的方法，待删除
//            mutableNetwork.addEdge(parent,this.compilationUnit,"Token"+parent.getRange());
//            ch2=parent;
//            if(ch1!=null){
//                //如果前后两个token同属于一个父结点，就用NextToken连接
//                if(ch1.getParentNode().equals(ch2.getParentNode())){
//                    mutableNetwork.addEdge(ch1,ch2,"NextToken"+ch1.getRange()+ch2.getRange());
//                }
//            }
//            ch1=parent;

        }

    }

//  TODO:这里暂时处理为顺序连接所有的叶子结点
    private void connectNextToken(Node parent) {

        if(!isLeaf(parent)) {

            List<Node> children = parent.getChildNodes();
            for (Node child : children
                    ) {
                connectNextToken(child);
            }
        }else{

            ch2=parent;
            if(!ch1.equals(ch2)){
                mutableNetwork.addEdge(ch1,ch2,"NextToken"+ch1.getRange()+ch2.getRange());
            }
            ch1=parent;
        }

    }

//  TODO:一开始尝试用Comment存储Feature，后来发现比较麻烦，待删除
//    private static class TokenFinder extends VoidVisitorAdapter<Void> {
//
//        @Override
//        public void visit(LineComment lc, Void arg){
//            super.visit(lc,arg);
//
//            //检查行注释中是否包含Feature，"token"
//            if(lc.toString().indexOf("token")!=-1) {
//                lc.getParentNode();
//                System.out.println(lc.toString());
//            }
//        }
//    }
    //获取变量声明语句，按执行顺序获取作用域下所有同名变量，根据所在语句给变量结点赋use、write值
    private void connectLastRead(CompilationUnit compilationUnit) {

        List<SimpleName> sns = new ArrayList();
        List<GraphNode> gns = new ArrayList();
        compilationUnit.findAll(VariableDeclarator.class).stream().map(vd-> {
            sns.clear();
            gns.clear();
            vd.getParentNode()
                    .get().findAll(SimpleName.class).stream().filter(sn -> sn.getIdentifier().equals(vd.getName()))
                    .map(sn -> sns.add(sn)
                    );
            int i = 0;
            while(i<sns.size()) {
                gns.set(i, new GraphNode(sns.get(i)));
            }
            //对于AssignExpr
            vd.getParentNode().get().findAll(AssignExpr.class).stream().map(ae->{
                gns.get(sns.indexOf(ae.getTarget())).setWrriten(true);
                return null;
            });
            //对于BinaryExpr
            vd.getParentNode().get().findAll(BinaryExpr.class).stream().map(ae->{ae
                    .findAll(SimpleName.class).stream().map(sn->{
                        gns.get(sns.indexOf(sn)).setUsed(true);
                        return null;
                    });
                return null;
            });
            //
            return null;
        });

    }

    private void connectLastWrite(CompilationUnit compilationUnit) {
    }

    //从赋值语句流中获取目标流，对于每个目标流查找其父类结点（赋值语句）下二元运算结点下的所有变量名，连线
    //TODO:对于二元运算中的数字要连线吗？
    private void connectComputedFrom(CompilationUnit compilationUnit) {

        Stream<Node> targets = compilationUnit.findAll(AssignExpr.class).stream().map(e->e.getTarget());
        targets.map(target->target.getParentNode().get().findAll(BinaryExpr.class).stream().map(be->
            be.findAll(SimpleName.class).stream().map(e ->
                    mutableNetwork.addEdge(target, e, "ComputeFrom")
            )
        ));

    }

    //根据java的特性，只要匹配作用域内所有同名变量即可得到文本使用流
    private void connectLastLexicalUse(CompilationUnit compilationUnit) {

        List<SimpleName> sns = new ArrayList();
        compilationUnit.findAll(VariableDeclarator.class).stream().map(vd-> {
            sns.clear();
            vd.getParentNode()
                    .get().findAll(SimpleName.class).stream().filter(sn -> sn.getIdentifier().equals(vd.getName()))
                    .map(sn -> sns.add(sn)
                    );
            int i = 0;
            while(i<sns.size()-1){
                mutableNetwork.addEdge(sns.get(i+1),sns.get(i),"LastLexicalUse");
            }
            return null;
        });
        //TODO:field的作用域和对象相关，与类的声明可以是分开的，因此难以界定作用域，暂时不处理
    }

    //找到所有return语句，然后向上连接方法声明
    private void connectReturnsTo(CompilationUnit compilationUnit) {

        compilationUnit.findAll(ReturnStmt.class).stream().map(rs->
        mutableNetwork.addEdge(rs,getMethodDeclaration(rs),"ReturnsTo"));

    }

    public Node getMethodDeclaration(Node node){
        while (!node.equals(compilationUnit)){
            node = node.getParentNode().get();
            if(node.getClass().equals(MethodDeclaration.class)) {
                return node;
            }
        }
        return null;
    }

    //根据方法调用语句的方法名，查找同名的方法声明语句，连接它们的参数
    private void ConnectFormalArgName(CompilationUnit compilationUnit) {

        compilationUnit.findAll(MethodCallExpr.class).stream().map(
                mc-> {
                    String name = mc.asMethodCallExpr().getName().asString();
                    List<Parameter> parameters = compilationUnit.findAll(MethodDeclaration.class).stream()
                            .filter(md->md.getName().toString().equals(name)).findFirst().get().getParameters();
                    int i=0;
                    for (Node arg:mc.getArguments()
                         ) {
                        mutableNetwork.addEdge(arg,parameters.get(i),"FormalArgName");
                        i++;
                    }
                    return null;
                });

    }

    private boolean isLeaf(Node node) {

        if(node.getChildNodes().isEmpty())
            return true;
        return false;

    }
//  TODO:简单打印图，待删除
//    public void printGraph(){
//        System.out.println(this.mutableNetwork.toString());
//    }

//    //示例代码
//    public <T> String format(Iterable<T> iterable) {
//        StringBuilder builder = new StringBuilder();
//        builder.append("{");
//        for (T obj : iterable) {
//            builder.append(obj).append(",");
//        }
//        if (builder.length()  > 1) {
//            builder.deleteCharAt(builder.length() - 1);
//        }
//        builder.append("}");
//        return builder.toString();
//    }

    //获取图上指定结点的Feature
    public NodeFeature getFeature(MutableNetwork network,Node node){
        NodeFeature nodeFeature = new NodeFeature();
        nodeFeature.setInEdges(network.inEdges(node));
        nodeFeature.setOutEdges(network.outEdges(node));
        nodeFeature.setNodeClass(node.getClass().toString());
//        StringBuilder feature = new StringBuilder();
//        feature.append(node.getRange()+"_feature_");
//        feature.append("inEdges_"+network.inEdges(node));
//        feature.append("outEdges_"+network.outEdges(node));
//        return feature.toString();
        return nodeFeature;
    }

    //TODO:DataKey？
    public <T> String getNetworkFeature(Iterable<T> iterable) {
        StringBuilder builder = new StringBuilder();
        for (T obj : iterable) {
            builder.append(getFeature(mutableNetwork,(Node) obj).toString()+"\n");
        }
        return builder.toString();
    }

    //根据原来的图构建一个新的以GraphNode为结点的图
    public MutableNetwork drawNewNetwork(MutableNetwork network){
        MutableNetwork<GraphNode,String> newNetwork = NetworkBuilder.directed()
                .allowsParallelEdges(true).nodeOrder(ElementOrder.insertion()).allowsSelfLoops(true)
                .expectedNodeCount(10000).expectedEdgeCount(10000).build();
        for(Object edge :network.edges()){
            Node nodeU = (Node)network.incidentNodes(edge).nodeU();
            Node nodeV  = (Node)network.incidentNodes(edge).nodeV();
            GraphNode graphNodeU = new GraphNode(nodeU);
            graphNodeU.setNodeFeature(getFeature(network,nodeU));
            GraphNode graphNodeV = new GraphNode(nodeV);
            graphNodeV.setNodeFeature(getFeature(network,nodeV));
            newNetwork.addEdge(graphNodeU,graphNodeV,(String) edge);
        }
        return newNetwork;
    }

    public CompilationUnit getCompilationUnit() {
        return compilationUnit;
    }

    public void setCompilationUnit(CompilationUnit compilationUnit) {
        this.compilationUnit = compilationUnit;
    }

    public MutableNetwork<Node, String> getMutableNetwork() {
        return mutableNetwork;
    }

    public void setMutableNetwork(MutableNetwork<Node, String> mutableNetwork) {
        this.mutableNetwork = mutableNetwork;
    }
}