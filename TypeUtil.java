package 编译原理实验课.实验1.version1;

import java.lang.reflect.Field;

/**
 * 对读取字符分类相关操作
 *
 * @author zhangyu
 */
public class TypeUtil {
    private final String keyWords[] = {"begin", "end", "integer", "if", "then", "else", "function", "read", "write"}; // 保留字数组
    private final char operators[] = {'=', '<', '>', '-', '*',';', '(', ')', ':'}; // 符号数组

    /**
     * 判断是否为字母
     *
     * @param ch 需判断的字符
     * @return boolean
     */
    public boolean isLetter(char ch) {
        return Character.isLetter(ch);
    }

    /**
     * 判断是否为数字
     *
     * @param ch 需判断的字符
     * @return boolean
     */
    public boolean isDigit(char ch) {
        return Character.isDigit(ch);
    }

    /**
     * 判断是否为关键字
     *
     * @param s 需判断的字符串
     * @return boolean
     */
    public boolean isKeyWord(String s) {
        for (int i = 0; i < keyWords.length; i++) {
            if (keyWords[i].equals(s))
                return true;
        }
        return false;
    }

    /**
     * 判断是否为符号
     *
     * @param ch 需判断的字符
     * @return boolean
     */
    public boolean isOperator(char ch) {
        for (char operator : operators) {
            if (ch == operator)
                return true;
        }
        return false;
    }


    /**
     * 利用反射获取种别编码
     *
     * @return 种别编码
     */
    public int getType(String args) {
        int type = -1;
        Field[] fields = KeyTypes.class.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals(args)) {
                try {
                    type = (Integer) field.get(new KeyTypes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return type;
    }

}
