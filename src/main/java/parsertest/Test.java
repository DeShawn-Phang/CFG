package parsertest;

import com.github.javaparser.JavaParser;
import com.github.javaparser.TokenTypes;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.type.ArrayType;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.w3c.dom.ls.LSInput;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

    private static final String FILE_PATH = "src/main/java/org/javaparser/samples/ReversePolishNotation.java";

    public static void main (String[] args) throws Exception {
        /*
            Although we're creating an instance of a FileInputStream
            we do not need to concern ourselves with closing the resourece
        */
        CompilationUnit cu = JavaParser.parse(new File(FILE_PATH));
        List<Node> nodeList = cu.getChildNodes();
        List<Node> nodesList2;
        for (Node node:nodeList
             ) {
//            System.out.println("node:"+node.getRange()+"tokenRange:"+node.getTokenRange());
            nodesList2=node.getChildNodes();
            for (Node node2:nodesList2
                 ) {
                System.out.println("rang: "+node2.getRange()+"node: "+node2.getTokenRange());
            }

        }

//        VoidVisitor<?> methodNameVisitor = new MethodNamePrinter();
//        methodNameVisitor.visit(cu,null);
//
//        List<String> methodNames = new ArrayList<>();
//        VoidVisitor<List<String>> methodNameCollector = new MethodNameCollector();
//        methodNameCollector.visit(cu, methodNames);
//        methodNames.forEach(n -> System.out.println("Method Name Collected: " + n));
//
//        List<String> swtichStmt = new ArrayList<>();
//        VoidVisitor<List<String>> swtichStmtCollector = new SwtichStmtCollector();
//        swtichStmtCollector.visit(cu,swtichStmt);
//        swtichStmt.forEach(n -> System.out.println("SS "+n));

    }

    //The Void means we’re not expecting the visit to return anything
    private static class MethodNamePrinter extends VoidVisitorAdapter<Void> {

        @Override
        public void visit(MethodDeclaration md,Void arg){
            super.visit(md,arg);
            System.out.println("Method Name Printed: "+md.getName());
        }
    }

    //it might be better to think of this object as being a collector in order to make it more palatable
    private static class MethodNameCollector extends VoidVisitorAdapter<List<String>> {

        @Override
        public void visit(MethodDeclaration md, List<String> collector) {
            super.visit(md,collector);
            collector.add(md.getNameAsString());
        }
    }

    private static class SwtichStmtCollector extends VoidVisitorAdapter<List<String>>{

        @Override
        public void visit(SwitchStmt ss, List<String> collector){
            super.visit(ss,collector);
            collector.add(ss.toString());
        }
    }

}
