package parsertest;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.visitor.ModifierVisitor;

import java.io.File;
import java.util.regex.Pattern;

public class ModifyingVisitorExample {

    private static final String FILE_PATH = "src/main/java/org/javaparser/samples/ReversePolishNotation.java";

    public static void main(String[] args) throws Exception {
        CompilationUnit cu = JavaParser.parse(new File(FILE_PATH));

        ModifierVisitor<?> numericLiteralVisitor = new IntegerLiteralModifier();
        numericLiteralVisitor.visit(cu,null);
        System.out.println(cu.toString());
    }

    private static class IntegerLiteralModifier extends ModifierVisitor<Void> {

        @Override
        public FieldDeclaration visit(FieldDeclaration fd,Void arg) {
            super.visit(fd,arg);
            fd.getVariables().forEach(v -> v.getInitializer().ifPresent(i ->
            {
                if (i instanceof IntegerLiteralExpr) {
                    v.setInitializer(formatWithUnderscores(((IntegerLiteralExpr) i).getValue()));
                }
            }));
            return fd;
        }
    }

    private static final Pattern LOOK_AHEAD_THREE = Pattern.compile("(\\d)(?=(\\d{3})+$)");

    static String formatWithUnderscores(String value){
        String withouUnderscores = value.replaceAll("_","");
        return LOOK_AHEAD_THREE.matcher(withouUnderscores).replaceAll("$1_");
    }
}