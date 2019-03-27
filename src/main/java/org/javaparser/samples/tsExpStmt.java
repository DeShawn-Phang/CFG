package org.javaparser.samples;

public class tsExpStmt {

    public void test(){

        int x=0;
        int y=1;
        y = x>y ? 5: 6;
        boolean a = true;
        a = !a;
        while(x<10){
            if(x==0){
                x = 4;
                String.valueOf(x);
                continue;
            }
            x++;
        }
        if(x>0);
        if(x>=0);
        if(x<=0);
        if(x!=0);
        y--;
//
//        for(int i=0;
//            i<5;
//            i++){
//            x++;
//        }
//
//        x=1;
//
//        for(int i=0,j=1;i+j<5;i++,j++){
//            x++;
//        }

//        do{
//            x++;
//            x--;
//        }while (x==0);

//        while(x==0){
//            x++;
//        }

//        if(true){
//
//            x++;
//
//        } else {
//
//            x=100;
//            x--;
//
//        }

//        switch (x){
//            case 1: x = 1;
//            case 2: x = 2;
//            break;
//            case 3: x = 3;
//            default: x = 4;
//        }

//        int a = 1;
//        int b = 2;
//        int c = 3;
    }

}