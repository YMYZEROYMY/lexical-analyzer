package lexicalAnalyzer;

//单词符号与种别对照表
public class KeyTypes {
    //基本字
    public static final int BEGIN=1;//begin
    public static final int END =2;//end
    public static final int INTEGER=3;//integer
    public static final int IF=4;//if
    public static final int THEN=5;//then
    public static final int ELSE=6;//else
    public static final int FUNCTION=7;//function
    public static final int READ=8;//read
    public static final int WRITE=9;//write

    public static final int ID=10;//标识符
    public static final int DIGIT=11;//常数

    //运算符
    public static final int EQ=12;//=
    public static final int LAG=13;//<>
    public static final int LE=14;//<=
    public static final int LT=15;//<
    public static final int GE=16;//>=
    public static final int GT=17;//>
    public static final int SUB=18;//-
    public static final int MUL=19;//*

    //界符
    public static final int VOL=20;//:=
    public static final int LEFT=21;//(
    public static final int RIGHT=22;//)
    public static final int SEM=23;//;
}
