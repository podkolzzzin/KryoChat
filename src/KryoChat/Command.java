package KryoChat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by podko_000
 * At 1:11 on 09.01.14
 */

public class Command {

    private String method;
    private String[] args;
    private boolean empty;
    private Command(String method, String[] args) {
        this.method = method;
        this.args = args;
    }

    private Command()
    {
        empty = true;
    }

    public static Command parseCommand(String command)
    {
        String[] parsed = command.split(" ");
        if(parsed.length==0)
            return new Command();
        else
        {
            String[] args = new String[parsed.length-1];
            System.arraycopy(parsed, 1, args, 0, parsed.length - 1);
            return new Command(parsed[0], args);
        }
    }

    public void execute(Object obj) {
        if(empty) return;
        Method m = null;
        try {
            Method[] ms = obj.getClass().getMethods();
            for(Method t:ms)
            {
                if(t.getName().equals(method))
                {
                   m=t;
                   break;
                }
            }
            if(m==null) throw new NoSuchMethodException();
        } catch (NoSuchMethodException e) {
            Console.writeLine("Unknown command");
            return;
        }
        try {
                m.invoke(obj, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }  catch (InvocationTargetException e) {
            Console.writeLine("Invoke command error");
        } catch (IllegalArgumentException e) {
            Console.writeLine("Unknown call");
        }
    }
}
