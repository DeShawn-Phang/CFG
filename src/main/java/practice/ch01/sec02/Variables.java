package practice.ch01.sec02;

/*
    1.2.1
    基本类型：四种整型、两种浮点型、一种字符型、布尔型。
    常量Integer.MIN_VALUE和Integer.MAX_VALUE分别是最小和最大的整数，Long、Short、Byte也有。
    int，最实用；long，大于20亿；byte、short用在特殊应用，例如，当存储空间非常珍贵时的底层文件处理，或大数组处理。
    BigInteger类，可应对long不够用的情况。
    Java的整型范围不依赖于处理器。
    使用long整型变量值时带上L后缀，byte或short型常量则采用强制转换。
    十六进制前缀0x（是数字零不是字母），二进制前缀0b。
    八进制前缀为0，最好远离八进制和前导为0的数。
    可以给数字添加下划线，不会影响编译，如100万，1_000_000。
    如果使用的值不可能是负值，并且实际需要额外一个比特，那么可以使用将数值翻译成无符号类型的方法，如 Byte.toUnsignedInt()。

    1.2.2
    float类型有个F后缀，没有后缀的浮点类型默认为double类（也可以使用D后缀）。
    十六进制浮点数，如0x1.0p-10，使用p而不是e来表示指数（指数还是十进制）。
    Double.POSITIVE_INFINITY 代表 ∞，Double.NaN 代表"非数值"。
    所有的 NaN 都被认为是彼此不同的，通过调用 .isNaN() 来检查值，
    .isInfinite() 检查无穷大，.isFinite 检查既不是无穷也不是 NaN。
    浮点数存在误差，不适合做金融计算，必要的话可以使用 BigDecimal 类。

    1.2.3
    char型描述了UTF-16字符编码中的"编码单元"，编码单元可以用十六进制表示，如'\u004A'就是'J'。
    4A表示Unicode字符"U+004A 拉丁字符J"的编码单元。
    特殊编码 '\n' 换行,'\r' 回车,'\t' tab,'\b' 退格。
    转义单引号'\''转义反斜杠'\\'

    1.2.4
    Java中false和true跟0和1没有关系。
 */

public class Variables {



}
