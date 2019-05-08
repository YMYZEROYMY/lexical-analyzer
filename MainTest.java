package 编译原理实验课.实验1.version1;

import java.io.FileNotFoundException;


/* @author ymy
 * @version 1.1
 */
public class MainTest {

    private static final String inputPath = "E:\\Java程序\\BookTest\\src\\编译原理实验课\\实验1\\version1\\code.pas";

    public static void main(String[] args) throws FileNotFoundException {
        /*创建词法分析类*/
        TestLexer testLexer = new TestLexer(inputPath);
        //FileUtil.clearFile();//清空文件
        testLexer.analyse();
    }
}
