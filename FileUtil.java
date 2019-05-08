package 编译原理实验课.实验1.version1;

import java.io.*;

/**
 * 文件操作
 *
 * @author ymy
 */
public class FileUtil {
    private static final String outputPath = "E:\\Java程序\\BookTest\\src\\编译原理实验课\\实验1\\version1\\output.dyd";
    private static final String errorPath = "E:\\Java程序\\BookTest\\src\\编译原理实验课\\实验1\\version1\\error.err";

    private FileReader fileReader;
    private BufferedReader bufferedReader;

    public FileUtil(String filePath) throws FileNotFoundException {
        fileReader = new FileReader(filePath);
        bufferedReader = new BufferedReader(fileReader);
        clearFile();
    }

    /**
     * 文件读取到缓冲区
     *
     * @param buffer 缓冲区
     *               false : filed
     */
    public boolean readFile(StringBuffer buffer) {
        try {
            String temp;
            if ((temp = bufferedReader.readLine()) != null) {
                buffer.append(temp);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static void singleWriteFile(String str,String filePath){
        try {
            File file = new File(filePath);
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(str);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 追加方式写文件
     *
     * @param args 需要写入字符串
     *             false : filed
     */
    public static void writeFile(String args,int choose) {
        if(choose==1){
            singleWriteFile(args,outputPath);
        }else if(choose==2){
            singleWriteFile(args,errorPath);
        }
    }

    /**
     * 清空文件
     */
    public static void clearFile() {
        singleClearFile(outputPath);
        singleClearFile(errorPath);
    }

    private static void singleClearFile(String filePath){
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                if(!file.createNewFile()){
                    System.out.println("创建文件失败");
                    System.exit(0);
                }
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
