package cn.bin2.sport.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @Author: bingshuai.lu
 * @Description:
 * @Date: Created in 11:01 2019/1/25
 * @Modified By:
 */
public class ThrowableUtil {

    public static String getStackTrace(java.lang.Throwable throwable){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            throwable.printStackTrace(pw);
            return "\n"+sw.toString();
        } finally {
            pw.close();
        }
    }
}
