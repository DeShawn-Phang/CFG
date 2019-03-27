package practice.ch09;

/*
    InputStream, OutputStream 抽象类（处理字节流）
    FileInputStream, FileOutputStream 文件流
    Reader, Writer 文本输入输出
 */

import com.github.javaparser.ast.stmt.WhileStmt;

import java.io.*;

public class learnStream {

    public static void main(String[] args) {
//        byte[] buffer = new byte[1024];
//        try {
//            int len = System.in.read(buffer);
//            String s = new String(buffer, 0, len);
//            System.out.println("读到了"+len+"字节");
//            System.out.println(s);
//            System.out.println("s的长度是: "+s.length());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        byte[] buf = new byte[10];
        for (int i=0; i<buf.length; i++){
            buf[i] = (byte)i;
        }
        try {
//            DataOutputStream out = new DataOutputStream(
//                    new BufferedOutputStream(
//                            new FileOutputStream("a.dat")));
            PrintWriter out = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    new FileOutputStream("a.txt"))));
            int i = 123456;
//            out.writeInt(i);
            out.println(i);
            out.close();
//            DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("a.dat")));
//            int j = in.readInt();
//            System.out.println(j);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream("src/main/java/practice/ch09/learnStream.java")));
            String line;
            while((line=in.readLine())!=null){
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
