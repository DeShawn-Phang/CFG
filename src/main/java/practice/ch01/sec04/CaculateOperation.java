package practice.ch01.sec04;

/*
    1.4.1
    a -= 10 等同于 a = a - 10

    1.4.2
    小心 / 操作符，如果两个操作数都是整型，那么结果也是整型
    整数除以零会导致异常，浮点数除以零会产生无限值或 NaN，不会导致异常
    如果 n 为奇数，且为负数，那么 n%2 = -1，要小心处理
    使用 Math.floorMod 方法，可以一定程度上避免 n%N 产生负值，但是对于负因子会产生负数结果，需要注意
    n++，n--，是为了减少性能损耗而出现的，但是现在不那么必要了，使用两个单独的声明也便于阅读
    许多现代处理器使用了超过64比特的浮点寄存器，以增加运算精度并减少运算中间步骤出现的溢出风险
    Java用strictfp修饰符，修饰方法，使方法中的所有浮点运算都是严格可移植的（在不同虚拟机上结果一致）

    1.4.3
    Math.pow() 计算次方，Math.sqrt() 计算平方根，Math.min() 得出较小数
    静态方法可以通过类名调用
    常量 Math.PI 和 Math.E
    Math类提供一些安全计算的方法。例如，10亿*3，结果会溢出，如果调用 Math.multiplyExact() 方法，就会产生异常，
    就能捕获、处理异常，而不是让程序以错误结果继续运行
    其他类，如Integer和Long类中也包含数学方法，如处理无符号值的方法，compareUnsigned()
    StrickMath类，提供了严格实现的数学方法，如，严格可重现的浮点运算（及时效率有损失）

    1.4.4
    自动类型转换：合法的转换，大多没有信息损失
    合法的，但损失了信息的类型转换：int到float，long到float或double
    强制类型转换：cast操作符，其实就是 (int) var 这样的形式
    Math.round() 四舍五入
    用 Math.toIntExact 代替 cast，当转换会丢弃数字的重要部分时，会报异常

    1.4.5
    逻辑操作符，捷径评估（短路与或）
    条件操作符，A ? B : C
    比特位操作符，& | ^（非） ~（将比特位进行反转）
    移位操作符，注意 >> 和 >>> 的区别，后者包括符号位
    将 & | 应用到 boolean 值的用法不常见，如果需要强制评估第二个操作数，不如将其指派到 boolean 变量，这样执行流显而易见

    1.4.6
    大数 BigInteger，BigDecimal
    Java 不允许对象使用操作符，因此操作大数时，必须使用方法调用
 */

import java.math.BigDecimal;
import java.math.BigInteger;

public class CaculateOperation {

    public static void main(String [] args){

        BigInteger k = new BigInteger("9999999");

        // r = 5*k+1
        BigInteger r = BigInteger.valueOf(5).multiply(k.add(BigInteger.ONE));

        // (2)*10^-(0) - (11)*10^-(1) = -0.9
        BigDecimal l = BigDecimal.valueOf(2,0).subtract(BigDecimal.valueOf(11,1));
        System.out.println(l);
    }

}