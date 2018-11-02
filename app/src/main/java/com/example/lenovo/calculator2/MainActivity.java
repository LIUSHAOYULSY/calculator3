package com.example.lenovo.calculator2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
public class MainActivity extends AppCompatActivity {
   View view;
   String string="",secstr="";
   TextView et_input;
   int flag=0,c=0;
   double b=0;
   double g=0;
   double f=0;
   //对变量初始化，规范化
   public void substr(){
       //substr 是截取字符串函数
       int a= string.indexOf(".");
       //找到返回位置的整数值
       int b= string.indexOf("E");
       //返回 E 第一次出现时的索引
       String mm=string;
       //声明变量名类型
       if(a==-1){
           if(string.length()>16)
               string= string.substring(0,16);
       }if(b>0){
       mm= string.substring(b);
       if(string.length()>16){
           int perStrLen=16-mm.length();
           string= string.substring(0,perStrLen)+mm;
       }
   }
}
//声明计算方法
public double calc(){
    switch (c){
        case 0:
            f=g;
            break;
        case 1:
            f=b+g;
            break;
        case 2:
            f=b-g;
            break;
        case 3:
            f=b*g;
            break;
        case 4:
            f=b/g;
            break;
    }b=f;
    c=0;
    return f;
}@Override
    //设置监听事件
protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
     final Button number[]=new Button[10];
     final Button btn[]=new Button[13];
     //获取所有按键，并保存到数组中，此处final为不可更改，只可以被赋值一次
     btn[0]=findViewById(R.id.BtnC);
    btn[1]=findViewById(R.id.BtnCE);
    btn[2]=findViewById(R.id.BtnSquare);
    btn[3]=findViewById(R.id.BtnRooting);
    btn[4]=findViewById(R.id.BtnBack);
    btn[5]=findViewById(R.id.BtnSign);
    btn[6]=findViewById(R.id.BtnReciprocal);
    btn[7]=findViewById(R.id.BtnDivide);
    btn[8]=findViewById(R.id.BtnMultiply);
    btn[9]=findViewById(R.id.BtnMinus);
    btn[10]=findViewById(R.id.BtnPlus);
    btn[11]=findViewById(R.id.BtnPoint);
    btn[12]=findViewById(R.id.BtnEqual);
    //获取功能按钮
  number[0]=(Button)findViewById(R.id.Btn0);
    number[1]=(Button)findViewById(R.id.Btn1);
    number[2]=(Button)findViewById(R.id.Btn2);
    number[3]=(Button)findViewById(R.id.Btn3);
    number[4]=(Button)findViewById(R.id.Btn4);
    number[5]=(Button)findViewById(R.id.Btn5);
    number[6]=(Button)findViewById(R.id.Btn6);
    number[7]=(Button)findViewById(R.id.Btn7);
    number[8]=(Button)findViewById(R.id.Btn8);
    number[9]=(Button)findViewById(R.id.Btn9);
    //获取数字按钮
    et_input=(TextView)findViewById(R.id.et_input);
    et_input.setText(string);
    //获取显示文本框
        //C:
    btn[0].setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            b = 0.0;
            c = 0;
            g = 0.0;
            string = "";
            et_input.setText(string);
            secstr = "";
        }
    });
    //CE
    btn[1].setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            string = "";
            et_input.setText(string);
            view = v;
        }
    });

    // 对平方的事件的处理
    btn[2].setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (string != "") {
                double temp = Double.parseDouble(string);
                string = "" + temp * temp;
                substr();
                et_input.setText(string);
            }
        }
    });

    // 对根号
    btn[3].setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (string != "") {
                double temp = Double.parseDouble(string);
                if(temp>=0)
                {string = Math.sqrt(temp) + "";
                substr();
                et_input.setText(string);}
                else
                    et_input.setText("无效输入");
            }
        }
    });
    // 对退格
    btn[4].setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (string != "") {if(string.length()==1){
                string="";
                et_input.setText(string);
                }

            else{
                    string = string.substring(0, string.length() - 1);
                    et_input.setText(string);
                }}
            else {
                et_input.setText(string);

            }
        }
    });

    // 对正负号
    btn[5].setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (view != btn[11] && string != "") {
                //不是小数点
                char ch=string.charAt(0);
                //取string 中的第一个字符
                if (ch == '-')
                    string = string.replace("-", "");
                //用空白代替负号
                else
                    string = "-" + string;
                et_input.setText(string);
            }
        }
    });

    // 对求1/x事件的处理
    btn[6].setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (string != "") {
                Double d = Double.parseDouble(string);
                Double s = 1.0 / d;
                string = "" + s.toString();
                substr();
                et_input.setText(string);
            } else {
                et_input.setText("除数不能为零");
            }
        }
    });

    // 对除号
    btn[7].setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) { if (string!="")
            {
                if (view == btn[7] || view == btn[8] || view == btn[9]
                        || view == btn[10]) {
                    c = 4;
                } else {
                    g = Double.parseDouble(string);
                    calc();
                    string = "" + f;
                    substr();
                    et_input.setText(string);
                    c = 4;
                    flag=1;
                    view = v;
                }
            }
        }
    });

    // 对乘号
    btn[8].setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (string != "") {
                if (view == btn[7] || view == btn[8] || view == btn[9]
                        || view == btn[10]) {
                    c = 3;
                } else {
                    g = Double.parseDouble(string);
                    calc();
                    string = "" + f;
                    substr();
                    et_input.setText(string);
                    c = 3;
                    flag=1;
                    view = v;
                }
            }
        }
    });

    // 对减号
    btn[9].setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (string != "") {
                if (view == btn[7] || view == btn[8] || view == btn[9]
                        || view == btn[10]) {
                    c = 2;
                } else {
                    g = Double.parseDouble(string);
                    calc();
                    string = "" + f;
                    substr();
                    et_input.setText(string);
                    c = 2;
                    flag=1;
                    view = v;
                }
            }
        }
    });

    // 对加号
    btn[10].setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (string != "") {//首先判断是否是加减乘除的运算符
                if (view == btn[7] || view == btn[8] || view == btn[9]|| view == btn[10]) {
                    c = 1;//如果是加减乘除运算符则C为1
                } else {
                    g = Double.parseDouble(string);
                    calc();
                    string = "" + f;
                    substr();
                    et_input.setText(string);
                    c = 1;
                    flag=1;
                    view = v;
                }
            }
        }
    });

    // 对小数点
    number[0].setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (string == "") {
                string += ".";
                et_input.setText(string);
            } else {
                if (string.contains(".")) {
                    string += "";
                    et_input.setText(string);
                } else {
                    char ch[];
                    int x = 0;
                    ch = string.toCharArray();//将字符串对象中的字符转换为一个字符数组
                    for (int i = 0; i < ch.length; i++)
                        if (ch[i] == '.')
                            x++;
                    if (x == 0) {
                        string += ".";//字符串后面加上小数点
                        et_input.setText(string);
                    }

                }
            }
        }
    });

    // 对等号
    btn[12].setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (string != "" && view != btn[7] && view != btn[8]
                    && view != btn[9] && view != btn[10]) {
                g = Double.parseDouble(string);
                calc();
                string = "" + f;
                substr();
                et_input.setText(string);
                flag=1;
                view = v;

            }
        }
    });
    // 设定数字按键
    btn[11].setOnClickListener(new View.OnClickListener() {

        public void onClick(View v) {
            if (flag == 1) {
                string = "";
                string += 0;
                et_input.setText(string);
                flag=0;
            } else {
                char ch1[];
                ch1 = string.toCharArray();
                if (!(ch1.length == 1 && ch1[0] == '0')) {
                    string += 0;
                    et_input.setText(string);
                }

            }
            view = v;
        }
    });

    number[1].setOnClickListener(new View.OnClickListener() {

        public void onClick(View v) {
            if (flag == 1) {
                string = "";
                string += 1;
                et_input.setText(string);
                flag=0;
            } else {
                string += 1;
                et_input.setText(string);
            }
            view = v;
        }
    });

    number[2].setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            if (flag == 1) {
                string = "";
                string += 2;
                et_input.setText(string);
                flag=0;
            } else {
                string += 2;
                et_input.setText(string);
            }
            view = v;
        }
    });

    number[3].setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            if (flag == 1) {
                string = "";
                string += 3;
                et_input.setText(string);
                flag=0;
            } else {
                string += 3;
                et_input.setText(string);
            }
            view = v;
        }
    });

    number[4].setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            if (flag == 1) {
                string = "";
                string += 4;
                et_input.setText(string);
                flag=0;
            } else {
                string += 4;
                et_input.setText(string);
            }
            view = v;
        }
    });

    number[5].setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            if (flag == 1) {
                string = "";
                string += 5;
                et_input.setText(string);
                flag=0;
            } else {
                string += 5;
                et_input.setText(string);
            }
            view = v;
        }
    });

    number[6].setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            if (flag == 1) {
                string = "";
                string += 6;
                et_input.setText(string);
                flag=0;
            } else {
                string += 6;
                et_input.setText(string);
            }
            view = v;
        }
    });

    number[7].setOnClickListener(new View.OnClickListener() {

        public void onClick(View v) {
            if (flag == 1) {
                string = "";
                string += 7;
                et_input.setText(string);
                flag=0;
            } else {
                string += 7;
                et_input.setText(string);
            }
            view = v;
        }
    });

    number[8].setOnClickListener(new View.OnClickListener() {

        public void onClick(View v) {
            if (flag == 1) {
                string = "";
                string += 8;
                et_input.setText(string);
                flag=0;
            } else {
                string += 8;
                et_input.setText(string);
            }
            view = v;
        }
    });

    number[9].setOnClickListener(new View.OnClickListener() {

        public void onClick(View v) {
            if (flag == 1) {
                string = "";
                string += 9;
                et_input.setText(string);
                flag=0;
            } else {
                string += 9;
                et_input.setText(string);
            }
            view = v;
        }
    });

}

    }



