package org.javaparser.examples;

import java.io.FileNotFoundException;
import java.util.Iterator;

public class Operator {

    public static void main(String[] args) throws FileNotFoundException {


        String filePath = "src/main/java/org/javaparser/samples/tsExpStmt.java";
        ParserUtil parserUtil = new ParserUtil(filePath);

//        生成 CFG 部分
        CFGGenerator cfgGenerator = new CFGGenerator();
        cfgGenerator.run(parserUtil.construct());
//        cfgGenerator.printCFG();
        VecGenerator vecGenerator = new VecGenerator(cfgGenerator.getCFG());
        vecGenerator.run();

//        生成图部分
//        AST2Graph ast2Graph = new AST2Graph(parserUtil.construct());
//        ast2Graph.construct();

//        ast2Graph.printGraph();
//        System.out.println(""+ ast2Graph.format(ast2Graph.construct().nodes()) );
//        System.out.println(ast2Graph.getNetworkFeature(ast2Graph.construct().nodes()));
    }
}