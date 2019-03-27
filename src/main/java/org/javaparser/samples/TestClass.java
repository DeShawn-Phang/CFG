package org.javaparser.samples;

public abstract class TestClass{

    public static int num;
    public static void test(int x,int y){

        do{
            y++;
        }while(x==0);

        switch(x){
            case 1: y=1;
            case 2: y=2;
            default: y=3; break;
        }

        if(x>1){
            y=1;
        }else{
            y=2;
        }

    }

    public static void main(String[] args){

        int j = 8;

        int i = 1;

        TestClass.test(i,j);

        i = 1 + i;

        for(;i<5;i++) {
            String.valueOf(i);
            for(;i<6;i++){
                i--;
            }
        }
        TestClass.num++;
    }
}