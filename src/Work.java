import java.io.*;
import java.util.Scanner;

public class Work {
    static StringBuffer token=new StringBuffer("");
    static int index=0;//用于记录当前下标；
    static int row=0,sum; //row 用于记录当前行数 ，sum 用于存储数字；
    static String storage="";  //用于存放输入后的总字符串
    //定义24个保留字
    static String[] keywords =new String[]{"int","long","char","short","float","double","do","static",
            "struct","void","default","case","switch","continue","break","return","while","for","if","else"
    };


    public static void main(String[] args) throws Exception {
        String filePath = "D:\\codes\\compileCW\\input.txt";
        FileInputStream fin = new FileInputStream(filePath);
        InputStreamReader reader = new InputStreamReader(fin);
        BufferedReader buffReader = new BufferedReader(reader);
        String strTmp = "";
        while((strTmp = buffReader.readLine())!=null){
//            System.out.println(strTmp);
            storage+=(strTmp+'\n');
        }
        buffReader.close();





		System.out.println(storage);
//        System.out.println("请输入C语言源程序字符串（以#结尾）：");
//        enter();
        do{
            int case1 =analyzer();
            switch(case1){

                case 0:
                    break;
                case 1: //keywords
                    System.out.println("<"+case1+","+"“"+token+"”"+">");
                    break;
                case 2: // identifiers
                    System.out.println("case2<"+case1+","+"“"+token+"”"+">");
                    break;
                case 3:
                    System.out.println("<"+case1+","+"“"+sum+"“"+">");
                    break;
                case 4:
                    System.out.println("<"+case1+","+"“"+token+"”"+">");
                    break;
                case 5:
                    System.out.println("<"+case1+","+"“"+token+"”"+">");
                    break;
                case 6:
                    System.out.println("Error in row"+row+"!");
                    break;
            }
        }while(index!=storage.length());
    }
    /**
     * 词法分析函数
     * @return
     */
    public static int analyzer() {
        token.delete(0, token.length());                //置空token对象，清除
        char ch=storage.charAt(index++);
        while(ch==' '){
            ch=storage.charAt(index++);      //去除空格符号
        }
        //如果遇到回车符行加一
        if(ch=='\n') {
            row++;

        }
        if((ch>='a'&&ch<='z')||(ch>='A'&&ch<='Z')){         //可能是关键字或者自定义的标识符
            while((ch>='0'&&ch<='9')||(ch>='a'&&ch<='z')||(ch>='A'&&ch<='Z')||(ch=='_')){
                token.append(ch);
                ch=storage.charAt(index++);
            }
            index--;
//            syn=1;       //默认为识别出的字符串为自定义的标识符，种别码为1
            String s=token.toString();
            for(int i = 0; i< keywords.length; i++){
                if(s.equals(keywords[i])){
                    return 1;
                }
            }
            return 2;
        }
        else if((ch>='0'&&ch<='9')){
            sum=0;
            while((ch>='0'&&ch<='9')){
                sum=sum*10+ch-'0';
                ch=storage.charAt(index++);
            }
            index--;
            return 3;
        }
        else switch(ch){

                case '<':
                    token.append(ch);
                    ch=storage.charAt(index++);
                    if(ch=='='){
                        token.append(ch);

                    }
                    else if(ch=='>'){
                        token.append(ch);

                    }
                    else{
                        index--;

                    }
                    return 4;
                case '>':
                    token.append(ch);
                    ch=storage.charAt(index++);
                    if(ch=='='){
                        token.append(ch);
                    }
                    else{

                        index--;
                    }
                    return 4;
                case '*':
                    token.append(ch);
                    ch=storage.charAt(index++);
                    if(ch=='*'){
                        token.append(ch);

                    }
                    else{

                        index--;
                    }
                    return 4;
                case '=':
                    token.append(ch);
                    ch=storage.charAt(index++);
                    if(ch=='='){

                        token.append(ch);
                    }
                    else{

                        index--;
                    }
                    return 4;
                case '/':
                    token.append(ch);
                    ch=storage.charAt(index++);
                    if(ch=='/'){
                        token.deleteCharAt(token.length()-1);
                        while(ch!='\n'){
                            ch=storage.charAt(index++);  //忽略掉注释，以空格为界定

                        }
                        return 0;
                    }
                    else if(ch=='*'){     //忽略星号注释
                        ch=storage.charAt(index++);
                        char ch2=storage.charAt(index);
                        while(ch!='*'||ch2!='/'){
                            ch=storage.charAt(index++);
                            ch2=storage.charAt(index);
                        }
                        index++;
                        return 0;
                    }
                    else{

                        index--;
                    }
                    return 4;

                case '+':

                    token.append(ch);
                    return 4;
                case '-':

                    token.append(ch);
                    return 4;
                case '%':

                    token.append(ch);
                    return 4;
                case ';':

                    token.append(ch);
                    return 5;
                case '(':

                    token.append(ch);
                    return 5;
                case ')':

                    token.append(ch);
                    return 5;
                case '#':

                    token.append(ch);
                    return 0;
                case '{':

                    token.append(ch);
                    return 5;
                case '}':
                    token.append(ch);
                    return 5;
                case ',':
                    token.append(ch);
                    return 5;
                case '\n':

                    return 0;
                default:
                    return 6;
            }
    }

}
