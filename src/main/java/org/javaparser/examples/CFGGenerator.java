package org.javaparser.examples;

/*
    ******* 控制流图生成算法的设计与实现 *******
    *
    * 在分析的过程中我们需要用到语法制导翻译技术，其具体做法是：
    * 在对应的文法中嵌入相应的语义动作，其中的语义动作是相应控制流图生成算法的实现
    *
    ******* 该类的用法如下 *******
    *
    * 1、新建一个生成器实例
    * CFGGenerator cfgGenerator = new CFGGenerator();
    *
    * 2、调用生成器实例的 run() 方法，传入 AST 的编译单元 CompilationUnit，该方法会返回一个 MutableNetwork 对象，即 CFG
    * MutableNetwork<Node,String> CFG = cfgGenerator.run(compilationUnit);
    *
    * 3、调用生成器的 printCFG() 方法，可以打印控制流路径
    * cfgGenerator.printCFG();
 */

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.stmt.*;
import com.google.common.graph.ElementOrder;
import com.google.common.graph.MutableNetwork;
import com.github.javaparser.ast.Node;
import com.google.common.graph.NetworkBuilder;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class CFGGenerator {

    /*
        ***** 控制流图结点的数据结构 *****
        * 行号，用 tokenRange 代替，唯一标识控制流结点，由 AST.Node 提供
        * 前驱结点链表，用 network 管理
        * 后继结点链表，用 network 管理
        * nosuccNodelist，用来存放未确定后继结点的控制流结点
        * CFGNodeList，用 network 管理
        * break_stack，存放 break 语句结点的堆栈（分析循环体和switch）
        * continue_stack，存放 continue 语句结点的集合堆栈（分析循环体）
        * label_stack，存放 switch 语句结点（分析 switch，case，default）
        * T_NodeList，临时储存 if 语句条件为 True 的未确定后继的结点链表，分析完 else 后，一起存放到 nosuccNodeList
        * Switch_list，用来保存 switch 各个分支的分析结果
        * link(node1, node2)，用来连接前驱和后继结点，用 network 管理
        * CFG_NODE()，创建新的控制流结点，用 network 管理
     */

    private ArrayList<Node> nosuccNodelist = new ArrayList<>();
    private ArrayDeque<Node> break_stack = new ArrayDeque<>();
    private ArrayDeque<Node> continue_stack = new ArrayDeque<>();
    private ArrayDeque<Node> label_stack = new ArrayDeque<>();
    private ArrayList<Node> T_Nodelist = new ArrayList<>();
    private ArrayList<Node> Switch_list = new ArrayList<>();

    public MutableNetwork<Object, String> getCFG() {
        return CFG;
    }

    private MutableNetwork<Object,String> CFG = NetworkBuilder.directed()
            .allowsParallelEdges(true).nodeOrder(ElementOrder.insertion()).allowsSelfLoops(true)
                .expectedNodeCount(10000).expectedEdgeCount(10000).build();

    public MutableNetwork run(CompilationUnit compilationUnit){

        // 通过遍历 AST 构建 CFG
        preorderTravesal(compilationUnit);

        return CFG;
    }

    private void preorderTravesal(Node parentNode){

        // 只要当前传入结点的孩子结点不为空，就对每一个孩子结点进行分析
        if(!parentNode.getChildNodes().isEmpty()){
            for(Node childNode : parentNode.getChildNodes()){

                // 对孩子结点进行匹配
                switch (childNode.getClass().toString().substring("class com.github.javaparser.ast.stmt.".length())){

                    // 如果是简单的表达式语句
                    case "ExpressionStmt":{

                        // 为表达式语句生成控制流结点，并加入到 CFG 中
                        CFG.addNode(childNode);

                        // 处理栈
                        if(!nosuccNodelist.isEmpty()){
                            for(Node node : nosuccNodelist){
                                CF_link(node, childNode);
                            }
                        }
                        nosuccNodelist.clear();
                        nosuccNodelist.add(childNode);

                    } break;

                    // 如果是 if 语句
                    case "IfStmt":{

                        // 为表达式语句生成控制流结点，并加入到 CFG 中
                        CFG.addNode(childNode);

                        // 处理栈
                        if(!nosuccNodelist.isEmpty()){
                            for(Node node : nosuccNodelist){
                                CF_link(node, childNode);
                            }
                        }
                        nosuccNodelist.clear();
                        nosuccNodelist.add(childNode);

                        // 访问 if 语句的 then 分支
                        IfStmt ifStmt = (IfStmt)childNode;
                        preorderTravesal(ifStmt.getThenStmt());

                        // 把 true 的结果保存到 T_Nodelist
//                        T_Nodelist = nosuccNodelist; 绝对不能这样写，这样写是引用赋值
                        T_Nodelist.addAll(nosuccNodelist);
                        nosuccNodelist.clear();
                        nosuccNodelist.add(childNode);

                        // 访问 if 语句的 else 分支
                        try {
                            preorderTravesal(ifStmt.getElseStmt().get());
                        } catch (NoSuchElementException e){
                            System.out.println("else分支为空"+e.getMessage());
                        }
                        nosuccNodelist.addAll(T_Nodelist);

                    } break;

                    // 如果是 switch 语句
                    case "SwitchStmt":{

                        // 为表达式语句生成控制流结点，并加入到 CFG 中
                        CFG.addNode(childNode);

                        // 处理栈
                        if(!nosuccNodelist.isEmpty()){
                            for(Node node : nosuccNodelist){
                                CF_link(node, childNode);
                            }
                        }
                        nosuccNodelist.clear();

                        // break 和 label 栈置空
                        break_stack.clear();
                        label_stack.clear();
                        label_stack.push(childNode);

                        // 访问 switch 的语句体
                        SwitchStmt switchStmt = (SwitchStmt)childNode;
                        for(SwitchEntryStmt SES : switchStmt.getEntries()) {

                            // switch 入栈，等待和 entry 进行连接
                            nosuccNodelist.add(childNode);
                            preorderTravesal(SES);
                        }
                        label_stack.pop();
                        for(Node node : Switch_list){
                            nosuccNodelist.add(node);
                        }
                        for(Node node : break_stack){
                            nosuccNodelist.add(node);
                        }
                        Switch_list.clear();

                    } break;

                    // 如果是 while 语句
                    case "WhileStmt":{

                        // 为表达式语句生成控制流结点，并加入到 CFG 中
                        CFG.addNode(childNode);

                        // 处理栈
                        if(!nosuccNodelist.isEmpty()){
                            for(Node node : nosuccNodelist){
                                CF_link(node, childNode);
                            }
                        }
                        nosuccNodelist.clear();
                        nosuccNodelist.add(childNode);

                        // 将 break 和 continue 栈置空
                        break_stack.clear();
                        continue_stack.clear();

                        // 访问 while 语句的语句体
                        WhileStmt whileStmt = (WhileStmt)childNode;
                        preorderTravesal(whileStmt.getBody());

                        // 使没有确定后继的结点和 continue 栈顶结点连接到后继
                        try {
                            nosuccNodelist.add(continue_stack.pop());
                        } catch (NoSuchElementException e){
                            System.out.println("continue栈空"+e.getMessage());
                        }
                        for (Node node : nosuccNodelist) {
                            CF_link(node, childNode);
                        }
                        nosuccNodelist.clear();
                        nosuccNodelist.add(childNode);
                        try {
                            nosuccNodelist.add(break_stack.pop());
                        } catch (NoSuchElementException e){
                            System.out.println("break栈空"+e.getMessage());
                        }


                    } break;

                    // 如果是 do_while 语句
                    case "DoStmt":{
                        DoStmt doStmt = (DoStmt)childNode;

//                        // 把条件语句先加入 nosuccNodelist 再遍历 do 的 body
//                        nosuccNodelist.add(doStmt.getCondition());
                        preorderTravesal(doStmt.getBody());

                        // 这里应该先加入 while 条件部分
                        CFG.addNode(doStmt.getCondition());

                        // 处理栈
                        if(!nosuccNodelist.isEmpty()){
                            for(Node node : nosuccNodelist){
                                CF_link(node,doStmt.getCondition());
                            }
                        }
//                        // 把 while 结点的后继结点设为 do 语句体的第一条，这里先连接 do 的块语句
//                        CF_link(doStmt.getCondition(),doStmt.getBody().asBlockStmt());

                        // 把 while 线连进去
                        nosuccNodelist.clear();
                        nosuccNodelist.add(doStmt.getCondition());
                        preorderTravesal(doStmt.getBody());

                        nosuccNodelist.clear();
                        nosuccNodelist.add(doStmt.getCondition());
                    } break;

                    // 如果是 for 语句
                    case "ForStmt":{

                        // 为表达式语句生成控制流结点，并加入到 CFG 中
                        CFG.addNode(childNode);

                        // 处理栈
                        if(!nosuccNodelist.isEmpty()){
                            for(Node node : nosuccNodelist){
                                CF_link(node, childNode);
                            }
                        }
                        ForStmt forStmt = (ForStmt)childNode;

                        // 获取循环条件语句结点
                        Node condition_node = forStmt.getCompare().get();
                        CFG.addNode(condition_node);

                        // 获取条件改变语句结点
                        Node first_update_node = forStmt.getUpdate().get(0);
                        CFG.addNode(first_update_node);

                        // 连接 for 和 condition
                        CF_link(childNode,condition_node);
                        nosuccNodelist.clear();

                        // condition 入栈，遍历 body 部分
                        nosuccNodelist.add(condition_node);
                        break_stack.clear();
                        continue_stack.clear();
                        preorderTravesal(forStmt.getBody());

                        try {
                            nosuccNodelist.add(continue_stack.pop());
                        } catch (NoSuchElementException e){
                            System.out.println("continue栈空"+e.getMessage());
                        }

                        // 连接 body 最后的语句和 update 语句
                        for(Node node: nosuccNodelist){
                            CF_link(node, first_update_node);
                        }

                        // 连接 update 语句 和 condition
                        CF_link(first_update_node, condition_node);

                        nosuccNodelist.clear();
                        nosuccNodelist.add(condition_node);
                        try {
                            nosuccNodelist.add(break_stack.pop());
                        }catch (NoSuchElementException e){
                            System.out.println("break栈空"+e.getMessage());
                        }

                    } break;

                    case "BreakStmt":{

                        // 为表达式语句生成控制流结点，并加入到 CFG 中
                        CFG.addNode(childNode);

                        // 处理栈
                        if(!nosuccNodelist.isEmpty()){
                            for(Node node : nosuccNodelist){
                                CF_link(node, childNode);
                            }
                        }
                        nosuccNodelist.clear();
                        break_stack.push(childNode);

                    } break;

                    case "ContinueStmt":{

                        // 为表达式语句生成控制流结点，并加入到 CFG 中
                        CFG.addNode(childNode);

                        // 处理栈
                        if(!nosuccNodelist.isEmpty()){
                            for(Node node : nosuccNodelist){
                                CF_link(node, childNode);
                            }
                        }
                        nosuccNodelist.clear();
                        continue_stack.push(childNode);

                    } break;

                    // case、default
                    case " ":{

                        // 先连接 switch 和 entry
                        CF_link(nosuccNodelist.get(nosuccNodelist.size()-1),childNode);
                        if(!break_stack.isEmpty()){
                            nosuccNodelist.clear();
                            nosuccNodelist.add(label_stack.peek());
                            Switch_list.add(break_stack.pop());
                        }

                        SwitchEntryStmt switchEntryStmt = (SwitchEntryStmt)childNode;
                        preorderTravesal(switchEntryStmt);

                    } break;

                    default: {

                        /*
                         * 注：上述任何一种匹配的情况，由于其子结点都已经得到了处理，因此无需再次遍历其子结点，
                         * 除非无任何匹配，才可能需要通过 default 主动遍历子结点
                         */
                        System.out.println(childNode.getClass().toString());
                        preorderTravesal(childNode);
                    } break;
                }
            }
        }
        return;
    }

    private void CF_link(Node pre, Node succ){
        CFG.addEdge(pre, succ, "CF: " + pre.getRange().get().begin + "-->" + succ.getRange().get().begin);
    }

    public void printCFG(){
        for(String edge :CFG.edges()){
            System.out.println(edge);
        }
    }

}