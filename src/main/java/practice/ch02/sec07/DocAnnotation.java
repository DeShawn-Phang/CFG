package practice.ch02.sec07;

/**
    2.7.1
    插入注释
    JDK包含一个非常有用的工具 javadoc，它能从源代码文件产生 HTML 文档
    这里使用的就是一个文档注释，它变绿了！！！
    标签示例：@author @param
    自由格式文本第一句应该是总结声明，可以使用 HTML 修饰符，比如 <em>强调</em>
    <code>单一空格"打字机"</code> <strong>粗黑</strong>
    如果要包含文件，可以将文件放在源文件目录的子目录下面，命名 doc-files
    javadoc 工具会将 doc-files 目录和它们的内容从源代码目录复制到文档目录
    需要再链接中指定目录，例如<img src="doc-files/uml.png" alt="UML diagram">
 */

/**
 * 2.7.2
 * 类注释
 * 一个 <code>Invoice</code> 对象代表发货单
 * 发货单中有订单每个部分的发货信息
 * @author Fred Flintstone
 * @author Barney Rubble
 * @version 1.1
 */

public class DocAnnotation {

    private int salary;

    /**
     * 2.7.3
     * 方法注释
     * 提高员工的薪水
     * @param byPercent 薪水提高的百分比
     * @return 涨薪后的薪水
     *
     * 2.7.4
     * 变量注释：只需要注释公有变量——通常是指静态常量
     *
     * 2.7.5
     * 通用注释：
     * @since version 1.7.1  （描述从哪个版本开始有这个功能）
     * @deprecated 使用<code>setVisible(true)</code>代替    （添加注释，表示类、方法或变量不应该再使用了。文本应该提供一个替代者）
     * @Deprecated   （注解，当使用了不建议使用的内容，编译器用来发出警告）
     *
     * 2.7.6
     * 链接
     * 给javadoc文档的相关部分或者外部文档添加超链接
     * @see practice.ch02.sec07.DocAnnotation#raiseSalary(double)
     * @see <a href="http://en.wikipedia.org/wiki/Leap_year">Leap Years</a>
     * @see "Core Java for the Impatient"
     * {@link package.class#toString()}
     *
     * 2.7.7
     * 要想产生包注释，需要再每个包目录添加单独的文件，package-info.java
     * 为所有源文件提供一个概述注释：overview.html 文件，位于包含所有源文件的父目录中<body>被提取</body>
     *
     * 2.7.8
     * 提取注释：切换到源文件目录，运行命令：javadoc -d docDirectory package1 package2
     * -author -version
     * -link 用来添加到标准类的超链接
     * -linksource
     */

    public double raiseSalary(double byPercent) {
        double raise = salary * byPercent/100;
        salary += raise;
        return raise;
    }
}