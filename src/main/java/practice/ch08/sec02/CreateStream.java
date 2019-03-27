package practice.ch08.sec02;

/*

    什么是Stream.<String>empry()

 */

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class CreateStream {

    String contents = "test,testtesttesttest,stest";
    List<String> words = Arrays.asList(contents.split("\\PL+"));
    long count = words.parallelStream().filter(w->w.length()>12).count();

    Stream<String> song = Stream.of("gently","down","the","stream");

    //这两句是什么语法？
    Stream<String> echos = Stream.generate(()->"Echo");
    Stream<Double> randoms = Stream.generate(Math :: random);

    Stream<BigInteger> integers = Stream.iterate(BigInteger.ZERO,n->n.add(BigInteger.ONE));

    Stream<String> word = Pattern.compile("\\PL+").splitAsStream(contents);

}
