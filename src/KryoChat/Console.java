package KryoChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by podko_000
 * At 0:54 on 09.01.14
 */

public class Console {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void writeLine(String line)
    {
        System.out.println(line);
    }

    public static void writeLine()
    {
        System.out.println();
    }

    public static String readLine()
    {
        try {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return "";
    }

    public static void write(String s) {
        System.out.print(s);
    }
}
