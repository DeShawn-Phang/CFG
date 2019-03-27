package practice.ch04.sec03;

public enum Operation {

    ADD {
        public int eval(int arg1, int arg2) {
            return arg1 + arg2;
        }
    },
    SUBTRACT {
        public int eval(int arg1, int arg2) {
            return arg1 - arg2;
        }
    };
//
//    public abstract int eval(int arg1, int arg2);
//
//    public static void main(String[] args) {
//        Operation op = ADD;
//        int result = op.eval(5,3);
//        System.out.println(result);
//        op = SUBTRACT;
//        result = op.eval(5,3);
//        System.out.println(result);
//    }

}
