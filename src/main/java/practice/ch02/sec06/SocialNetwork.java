package practice.ch02.sec06;

/*
    2.6.2
    内部类（无static修饰的嵌套类）
    内部类的方法可以访问它的外部类的实例变量
    TODO:分清静态嵌套类和内部类的区别
    每个内部类对象都有自己外部类对象的引用，而静态嵌套类没有这样的引用，就像静态方法没有 this 引用
    使用：当嵌套类的实例不需要知道它属于外部类的哪个实例时，使用静态嵌套类，否则使用内部类。
    为了避免语义的模糊不清，内部类禁止声明静态成员。
 */

import com.google.common.graph.Network;

import java.util.ArrayList;

public class SocialNetwork {

    //Member 对象知道自己属于哪个网络实例
    public class Member {
        private String name;
        private ArrayList<Member> friends;

        public Member(String name) {
            this.name = name;
            friends = new ArrayList<>();
        }
        public void leave(){

            //以下两句效果相同，外部类引用 SocialNetwork.this. 不是必需的
            members.remove(this);
            SocialNetwork.this.members.remove(this);
        }

        //需要用到外部类引用的情况
        public boolean belongsTo(SocialNetwork n){
            return SocialNetwork.this == n;
        }
    }

    private ArrayList<Member> members;

    public Member enroll(String name) {

        //以下两句效果相同
        Member newMember = new Member(name);
        Member newMember2 = this.new Member(name);
        members.add(newMember);
        return newMember;
    }

    public static void main(String[] args) {
        SocialNetwork myFace = new SocialNetwork();
        SocialNetwork.Member fred = myFace.enroll("fred");
        fred.leave();
        SocialNetwork.Member wilma = myFace.new Member("wilma");
    }
}
