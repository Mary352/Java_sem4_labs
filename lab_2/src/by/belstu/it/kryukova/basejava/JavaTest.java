package by.belstu.it.kryukova.basejava;

import com.company.WrapperString;

import static java.lang.Integer.*;
import static java.lang.Math.min;
import static java.lang.Math.*;
import java.util.Arrays;

/**
 * @author Maria Kryukova
 * @version 1.1.5
 */

public class JavaTest {

    static int sint;
    final int FI = 2;
    public final int PFI;
    public static final int PSFI = 8;

    {
        PFI = 7;
    }

    /**
     * @return
     * @throws
     * @param args
     */
    public static void main(String[] args) {
        char ch = 'f';
        int i = 214748;
        short sh = 32760;
        byte b = 120;
        long lo = 9223372035L;
        boolean boo = true;

        String str = "qwerty";
        double d = 23552.665223;
        
        byte bRes = (byte) (b + i);
        int iRes = (int) (d + lo);
        long loRes = i + 2147483647;

        /**
         *  @value
         *  @see https://javarush.ru/quests/lectures/questsyntax.level04.lecture07
         */
        boolean boo2 = false;

        boolean booResAnd = boo && boo;
        boolean booResXor = boo ^ boo2;

        long loGuess = 9223372036854775807L;
        long lo2Guess = 0x7fff_ffff_fffL;

        char chA = 'a', chSym = '\u0061', chNum = 97;

        Double Db10 = 1.0/0.0;
        Double Db00 = 0.0/0.0;

        System.out.println("String+int = " + str + i);
        System.out.println("String+char = " + str + ch);
        System.out.println("String+double = " + str + d);
        System.out.println("byte = byte + int: " + bRes);
        System.out.println("int = double+long: " + iRes);
        System.out.println("long = int + 2147483647: " + loRes);
        System.out.println("значение static int без инициализации: " + sint);
        System.out.println("boolean = boolean && boolean: " + booResAnd);
        System.out.println("boolean= boolean ^ boolean: " + booResXor);
        System.out.println(chA);
        System.out.println("\\u0061: " + chSym);
        System.out.println("97: " + chNum);
        System.out.println("3.45 % 2.4 = " +  (3.45 % 2.4));
        System.out.println("1.0/0.0 = " + 1.0/0.0);
        System.out.println("0.0/0.0 = " + 0.0/0.0);
        System.out.println("log(-345) = " +  log(-345));
        System.out.println("Float.intBitsToFloat(0x7F800000): " + Float.intBitsToFloat(0x7F800000));
        System.out.println("Float.intBitsToFloat(0xFF800000): " + Float.intBitsToFloat(0xFF800000));
        System.out.println("Math.PI = " + PI);
        System.out.println("Math.E = " + E);
        System.out.println("Math.round(Math.PI) = " + round(PI));
        System.out.println("Math.round(Math.E) = " + round(E));
        System.out.println("Math.min(Math.PI, Math.E) = " + min(PI, E));
        System.out.println("Случайное число из диапазона [0,1) = " + random());

        Boolean Boo = true;
        Character Ch = 'd';
        Integer Int = 1000000000;
        Byte Byt = 2;
        Short Sh = 525;
        Long Lo = 64985564549L;
        Double Db= 14774.1542;

        System.out.println("\nByte+Integer = " + (Byt + Int));
        System.out.println("Short*Byte = " + (Sh * Byt));
        System.out.println("Double-Short = " + (Db - Sh));
        System.out.println("Long&Integer = " + (Lo & Int));
        System.out.println("~Byte = " + (~Byt));
        System.out.println("Integer>>2 = " + (Int>>2));
        System.out.println("Byte>>>3 = " + (Byt>>>3));
        System.out.println("Long.MIN_VALUE = " + Long.MIN_VALUE);
        System.out.println("Long.MAX_VALUE = " + Long.MAX_VALUE);
        System.out.println("Double.MIN_VALUE = " + Double.MIN_VALUE);
        System.out.println("Double.MAX_VALUE = " + Double.MAX_VALUE);

        // упаковка
        Byte BytBox = b;
        Integer IntBox = 88;

        // распаковка
        byte bUnbox = BytBox;
        int iUnbox = IntBox*4;

        String NumStr = "25562";
        Integer IntParse = parseInt(NumStr);

        boolean checkNaN_Db10 = Db10.isNaN();
        boolean checkNaN_Db00 = Db00.isNaN();

        System.out.println("\nparseInt(NumStr) = " + IntParse);
        System.out.println("toHexString(Int) = " + toHexString(Int));
        System.out.println("compare(Int, IntParse) = " + compare(Int, IntParse));
        System.out.println("IntBox.toString() = " + IntBox.toString());
        System.out.println("Binary i = " + Integer.toBinaryString(i));
        System.out.println("Number of one bits in i = " + Integer.bitCount(i));
        System.out.println("Db10.isNaN() = " + checkNaN_Db10);
        System.out.println("Db00.isNaN() = " + checkNaN_Db00);

        String s34 = "2345";

        System.out.println(Integer.valueOf(s34));
        System.out.println(Integer.parseInt(s34));

        byte[] byteArray = str.getBytes();
        String strByteArray = new String(byteArray);
        System.out.println("Строку в массив байт: " + Arrays.toString(byteArray));
        System.out.println("Строку из массива байт: " + strByteArray);

        System.out.println(Boolean.valueOf(s34));
        System.out.println(Boolean.parseBoolean(s34));

        String str1 = "qwerty";
        System.out.println("str == str1: " + (str == str1));
        System.out.println("str.equals(str1): " + (str.equals(str1)));
        System.out.println("str.compareTo(str1): " + (str.compareTo(str1)));

        String str2 = null;
        System.out.println("str == str2: " + (str == str2));
        System.out.println("str.equals(str2): " + (str.equals(str2)));

        // NullPointerException: Cannot invoke "String.compareTo(String)" because "str2" is null
        //System.out.println("str.compareTo(str2): " + (str2.compareTo(str)));

        String strFun = "Las Vegas is a popular destination in the western portion of the United States";
        String[] words = strFun.split(" ");
        System.out.println("-------------");
        for (String word : words)
        {
            System.out.println(word);
        }
        System.out.println("-------------");
        System.out.println("strFun.contains(\"Vegas\"): " + strFun.contains("Vegas"));
        System.out.println("strFun.hashCode() = " + strFun.hashCode());
        System.out.println("strFun.indexOf('a', 14) = " + strFun.indexOf('a', 14));
        System.out.println("strFun.length() = " + strFun.length());
        System.out.println(strFun.replace("Las Vegas", "New York"));

        char[][] c1;
        char[] c2[];
        char c3[][];

        c1 = new char[3][];

        c1[0] = new char[1];
        c1[1] = new char[2];
        c1[2] = new char[3];
        System.out.println("c1.length = " + c1.length);
        System.out.println("c1[0].length = " + c1[0].length);
        System.out.println("c1[1].length = " + c1[1].length);
        System.out.println("c1[2].length = " + c1[2].length);

        c2 = new char[2][];
        c2[0] = new char[] {'f', 'd'};
        c2[1] = new char[] {'n'};

        c3 = new char[2][];
        c3[0] = new char[] {'g'};
        c3[1] = new char[] {'r'};

        boolean comRes = c2 == c3; // false
        c2 = c3;
        for (char[] row: c2)
        {
            System.out.println(Arrays.toString(row));
        }

        WrapperString wrapperString = new WrapperString(str);
        wrapperString.replace('r','f');

        WrapperString strReplace = new WrapperString()
        {
            @Override
            public void replace(char oldchar, char newchar) {
                System.out.println(oldchar + " -> " + newchar + " replacing...");
            }

            public void delete(char newchar)
            {
                String oldStr = wrapperString.getStrNew();
                String newStrResult = null;
                try
                {
                    int deletePosition = oldStr.indexOf(newchar, 0);
                    newStrResult = oldStr.substring(0, deletePosition);
                    newStrResult += oldStr.substring(deletePosition + 1);
                }
                catch (Exception ex)
                {
                    System.out.println("no such symbol");
                }
            }
        };
        strReplace.replace('s','v');
        // только при наличии метода delete в WrapperString
        //strReplace.delete('q');
    }
}
