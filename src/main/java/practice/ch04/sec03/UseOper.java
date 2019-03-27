package practice.ch04.sec03;

import static practice.ch04.sec03.Operation.*;

public class UseOper {

    // 在 switch 语句内，使用的是 ADD，外部使用 Operation.ADD，或者使用静态导入声明
    Operation oper = Operation.ADD;
    Operation oper2 = ADD;

    public static int eval(Operation op, int arg1, int arg2){
        int result = 0;
        switch (op){
            case ADD: result = arg1 + arg2; break;
            case SUBTRACT: result = arg1 - arg2; break;
        }
        return result;
    }
}
