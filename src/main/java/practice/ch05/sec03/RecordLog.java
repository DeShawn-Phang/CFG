package practice.ch05.sec03;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 5.3.1
 * 采用 Logger
 * 日志管理系统管理一个通过调用 Logger.getGlobal() 获得的默认日志记录器
 * 使用 info 方法记录一条信息消息，系统会自动添加一些上下文信息
 * 如果调用 Logger.getGlobal().setLevel(Level.OFF)，info 方法将不会记录日志
 * TODO：如果你担心消息创建的代价，则可以使用 lambda 表达式代替
 *
 * 5.3.2
 * 定义自己的日志记录器
 * 日志记录器和它的孩子会共享一些特定属性
 *
 * 5.3.3
 * 日志有7种级别，通过设置不同的阈值选择要记录的日志，例如 logger.setLevel(Level.FINE);
 * 默认的日志处理器不会输出低于 INFO 级别的信息
 *
 * 5.3.4
 * 其他日志记录方法：entering()、exiting()
 * 这些调用会产生以字符串 ENTRY 和 RETURN 开始的 FINER 级别的日志记录
 * 在记录中包含异常的描述信息，log()，throwing()
 * 使用 logp() 方法来获取调用类和调用方法的准确位置
 * TODO:如果想以多种语言记录日志以便用户理解日志信息，你需要调用方法将日志本地化。资源束（resource bundle）
 *
 * 5.3.5
 * 日志记录的配置项
 * 日志管理器是在 VM 启动阶段初始化的，这是在 main 函数执行之前进行的
 * 通过 JConsole 工具，有可能在程序运行时改变日志记录的级别
 *
 * 5.3.6
 * 日志处理器
 * 默认情况下，一个日志记录器将记录发给自己的处理器和父处理器
 * 如果想将日志记录发送到其他地方，可以添加另外的处理器，如 FileHandler 和 SocketHandler
 * TODO:FileHandler，将记录收集到文件中
 *
 * 5.3.7
 * 过滤器和格式化器
 * setFilter()、setFormatter()
 */

public class RecordLog {

    public int read(String file,String pattern){
        Logger logger = Logger.getLogger("com.yyy.myapp");
        logger.entering("com.kkk.Reader","read",new Object[]{file,pattern});
        int count = 0;
        logger.exiting("com.kkk.Reader","read",count);
        return count;
    }

    public static void main(String[] args) {

        Logger.getGlobal().info("Opening file");
        Logger.getGlobal().setLevel(Level.OFF);
        Logger logger = Logger.getLogger("com.kkk.myapp");

        //另外一种提供日志级别的方式
        Level level = Level.FINE;
        logger.log(level,"msg");

        Logger mLogger = Logger.getLogger("com.yyy.myapp");
        int a = 1;
        mLogger.info("这是我的 logger"+a);
        mLogger.fine("Fine");
        mLogger.severe("Severe");
    }
}