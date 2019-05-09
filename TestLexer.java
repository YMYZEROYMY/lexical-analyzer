package lexicalAnalyzer;

import java.io.FileNotFoundException;

/**
 * 词法分析
 * 关键字，运算符一符一类
 * 标识符，常数，分隔符各自一类
 * 运算符未处理组合运算 ++、--、+= 等
 */
public class TestLexer extends TypeUtil {
    private StringBuffer buffer = new StringBuffer(); // 缓冲区
    private int i = 0;
    private char ch; // 字符变量，存放最新读进的源程序字符
    private String strToken; // 字符数组，存放构成单词符号的字符串
    private FileUtil fileUtil;

    /**
     * 读取指定路径文件
     *
     * @param filePath 读取文件路径
     */
    public TestLexer(String filePath) throws FileNotFoundException {
        fileUtil = new FileUtil(filePath);
    }

    /**
     * 词法分析
     */
    public void analyse() {
        strToken = ""; // 置strToken为空串
        int link=0;
        while (fileUtil.readFile(buffer)) {
            link++;
            i=0;
            buffer.trimToSize();
            if(buffer.length()==0){
                continue;
            }
            buffer.append(" ");
            while (i < buffer.length()) {
                getChar();
                if (isLetter(ch)) { // 如果ch为字母
                    while (isLetter(ch) || isDigit(ch)) {
                        concat();
                        getChar();
                    }
                    retract(); // 回调
                    if (isKeyWord(strToken)) {
                        writeFile(strToken, strToken);//strToken为关键字
                    } else {
                        writeFile("id", strToken);//strToken为标识符
                    }
                    strToken = "";
                } else if (isDigit(ch)) {
                    while (isDigit(ch)) {//ch为数字
                        concat();
                        getChar();
                    }
                    if (!isLetter(ch)) {//不能数字+字母
                        retract(); // 回调
                        writeFile("digit", strToken); // 是整形
                    } else {
                        writeFile(link,"非法字符"); // 非法字符!!!!!!
                    }
                    strToken = "";
                } else if (isOperator(ch)) { //运算符

                    //ch+""是为了将ch转换为String

                    switch (ch) {
                        case '=':
                            writeFile("eq", ch + "");
                            break;
                        case '<':
                            concat();
                            getChar();
                            if (ch == '>') {//为<>
                                concat();
                                writeFile("lag", strToken);
                            } else if (ch == '=') {//为<=
                                concat();
                                writeFile("le", strToken);
                            } else {//为<
                                retract();
                                writeFile("gt", strToken);
                            }
                            strToken = "";
                            break;
                        case '>':
                            concat();
                            getChar();
                            if (ch == '=') {//为>=
                                concat();
                                writeFile("ge", strToken);
                            } else {//为>
                                retract();
                                writeFile("gt", strToken);
                            }
                            strToken = "";
                            break;
                        case '-':
                            writeFile("sub", ch + "");
                            break;
                        case '*':
                            writeFile("mul", ch + "");
                            break;
                        case ':':
                            concat();
                            getChar();
                            if (ch == '=') {//输入为:=
                                concat();
                                writeFile("vol", strToken);
                            } else {
                                retract();
                                writeFile(link,"冒号不匹配");// 冒号不匹配!!!!!!
                            }
                            strToken = "";
                            break;
                        case '(':
                            writeFile("left", ch + "");
                            break;
                        case ')':
                            writeFile("right", ch + "");
                            break;
                        case ';':
                            writeFile("sem", ch + "");
                            break;
                        default:
                            break;
                    }
                }else if(ch==' '){

                }
                else {
                    writeFile(link,"非法字符");// 非法字符!!!!!!
                }
            }
            buffer=new StringBuffer();
        }
    }

    /**
     * 将下一个输入字符读到ch中，搜索指示器前移一个字符
     */
    private void getChar() {
        ch = buffer.charAt(i);
        i++;
    }

    /**
     * 将ch连接到strToken之后
     */
    private void concat() {
        strToken += ch;
    }

    /**
     * 将搜索指示器回调一个字符位置，将ch值为空白字
     */
    private void retract() {
        i--;
        ch = ' ';
    }

    /**
     * 按照二元式规则写入文件
     *
     * @param type 字符类型
     * @param s    当前字符
     */
    private void writeFile(String type, String s) {
        int tempType = getType(type.toUpperCase());
        StringBuilder tempStr=new StringBuilder();
        for (int i=0;i<16;i++){
            if(i<s.length()){
                tempStr.append(s.charAt(i));
            }else {
                tempStr.append(" ");
            }
        }
        System.out.println("(" + type + ", " + s + ")");

        String output = tempStr.toString() + " " + tempType + "\t\t\r\n";
        FileUtil.writeFile(output,1);
    }

    private void writeFile(int link,String message){
        System.out.println("错误行号："+link+" 信息："+message);
        String output="***LINE:"+link+"  "+message+"\n";
        FileUtil.writeFile(output,2);
    }
}