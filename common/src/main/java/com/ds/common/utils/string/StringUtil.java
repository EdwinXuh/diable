package com.ds.common.utils.string;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author raptor
 */
public class StringUtil {
    public static boolean porcessSuffixChars = true;

    public static final String DEFAULT_DATE_FORMAT_MASK = "yyyy-MM-dd HH:mm:ss";

    public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT_MASK);

    public static final boolean string2boolean(String string, boolean def) {
        if (isEmpty(string)) {
            return def;
        }
        return "Y".equals(string.toUpperCase()) || "TRUE".equals(string.toUpperCase())
                || "YES".equals(string.toUpperCase()) || "1".equals(string.toUpperCase());
    }

    public static final int string2int(String str, int def) {
        int retval;
        try {
            retval = Integer.parseInt(str);
        } catch (Exception e) {
            retval = def;
        }
        return retval;
    }

    public static final long string2long(String str, long def) {
        long retval;
        try {
            retval = Long.parseLong(str);
        } catch (Exception e) {
            retval = def;
        }
        return retval;
    }

    public static final double string2double(String str, double def) {
        double retval;
        try {
            retval = Double.parseDouble(str);
        } catch (Exception e) {
            retval = def;
        }
        return retval;
    }

    public static final Date string2date(String str, Date def) {
        try {
            synchronized (simpleDateFormat) {
                return simpleDateFormat.parse(str);
            }
        } catch (Exception e) {
            return def;
        }
    }

    public static final String date2string(Date date) {
        synchronized (simpleDateFormat) {
            return simpleDateFormat.format(date);
        }
    }

    /**
     * 判断两个字符串是否相等，区分大小写
     *
     * @param str1 字符串
     * @param str2 字符串
     * @return 如果相等返回true，否则返回false。
     */
    public static boolean equals(String str1, String str2) {
        if (str1 == null) {
            if (str2 == null) {
                return true;
            }
        } else {
            return str1.equals(str2);
        }
        return false;
    }

    /**
     * 判断两个字符串是否相等，不区分大小写
     *
     * @param str1 字符串
     * @param str2 字符串
     * @return 如果相等返回true，否则返回false。
     */
    public static boolean equalsIgnoreCase(String str1, String str2) {
        if (str1 == null) {
            if (str2 == null) {
                return true;
            }
        } else {
            return str1.equalsIgnoreCase(str2);
        }
        return false;
    }

    /**
     * 判断字符串是否为空。
     *
     * @param str 字符串
     * @return 如果字符串为null或者trim()后长度为0返回true，否则返回false。
     */
    public static boolean isEmpty(String str) {
        if (str == null) {
            return true;
        } else {
            String temp = str.trim();
            if (temp.length() == 0) {
                return true;
            }
        }
        return false;
    }

    private final static String[] hexDigits = {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"
    };

    /**
     * 将一个字节转化成十六进制形式的字符串
     *
     * @param b 字节
     * @return 十六进制形式的字符串
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * 转换字节数组为十六进制字符串。
     *
     * @param b 字节数组
     * @return 十六进制字符串
     */
    public final static String byteArray2HexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    /**
     * 根据字符串的字符形式，返回字节数组形式。
     * 如输入字符串"AD67EA2F3BE6E5AD"返回字节数组"173,103,234,47,59,230,229,173"。
     *
     * @param string 字符串
     * @return 字节数组
     */
    public static byte[] hexString2byteArray(String string) {
        byte[] bRet = new byte[string.length() / 2];
        for (int i = 0; i < string.length() / 2; i++) {
            Integer itg = new Integer(16 * getChar2Int(string.charAt(2 * i)) + getChar2Int(string.charAt(2 * i + 1)));
            bRet[i] = itg.byteValue();
        }
        return bRet;
    }

    // 计算一个16进制字符的10进制值。
    private static int getChar2Int(char ch) {
        for (int i = 0; i < hexDigits.length; i++) {
            if (ch == hexDigits[i].charAt(0)) {
                return i;
            }
        }
        return 0;
    }

    /**
     * 根据选中的字符串和分隔符，以及选中的字符串重新排序。已经选中的已删除，其它按照原顺序排列。
     *
     * @param string  字符串
     * @param segment 分隔符
     * @param checked 优先排列的字符串
     * @return 排序后的字符列表
     */
    public static String[] orderArray(String string, String segment, String[] checked) {
        List<String> list = orderList(string, segment, checked);
        return list.toArray(new String[0]);
    }

    /**
     * 根据选中的字符串和分隔符，以及选中的字符串重新排序。已经选中的已删除，其它按照原顺序排列。
     *
     * @param string_old  字符串
     * @param segment_old 分隔符
     * @param checked     优先排列的字符串
     * @return 排序后的字符列表
     */
    public static List<String> orderList(String string_old, String segment_old, String[] checked) {
        List<String> list = new ArrayList<String>();

        String string = string_old;
        String segment = segment_old;
        if (StringUtil.isEmpty(string)) {
            string = "";
        }

        if (StringUtil.isEmpty(segment)) {
            segment = "";
        }

        if (checked != null) {
            for (String s : checked) {
                if (!StringUtil.isEmpty(s)) {
                    string = string.replace(s + segment, "");
                }
            }
        }

        String[] words = string.split(segment);
        if (words != null) {
            for (String s : words) {
                if (!list.contains(s)) {
                    list.add(s);
                }
            }
        }
        return list;
    }

    /**
     * 字符串清洗，从字符串中排除不存在于候选字符串中的值。如果候选字符串为空，返回空串。
     *
     * @param string_old  字符串
     * @param condidate   候选字符串
     * @param segment_old 分隔符
     * @return 清洗后的字符串
     */
    public static String filter(String string_old, String condidate, String segment_old) {
        String segment = segment_old;
        String string = string_old;
        if (StringUtil.isEmpty(condidate)) {
            return "";
        }

        if (StringUtil.isEmpty(string)) {
            return condidate;
        }

        if (StringUtil.isEmpty(segment)) {
            segment = "";
        }

        if (!string.endsWith(segment)) {
            string += segment;
        }

        // 获取不在候选数组中的字符，再清除。
        String[] condidates = condidate.split(segment);
        List<String> list = orderList(string, segment, condidates);
        for (String s : list) {
            if (!StringUtil.isEmpty(s)) {
                string = string.replace(s + segment, "");
            }
        }

        if (string.endsWith(segment)) {
            string = string.substring(0, string.length() - segment.length());
        }

        return string;
    }

    /**
     * 字符串清洗，从字符串中排除不存在于候选字符串中的值。如果候选字符串为空，返回空串。
     *
     * @param string_old 字符串
     * @param condidate  候选字符串
     * @param segment    分隔符
     * @return 清洗后的字符串
     */
    public static String filterFill(String string_old, String condidate, String segment) {
        String string = string_old;
        string = filter(string, condidate, segment);

        if (StringUtil.isEmpty(string)) {
            if (StringUtil.isEmpty(condidate)) {
                return "";
            }
            return condidate;
        }

        if (!string.endsWith(segment)) {
            string += segment;
        }

        // 追加候选数组中的其它字符
        String[] condidates = condidate.split(segment);
        for (String s : condidates) {
            if (!string.contains(s + segment)) {
                string += s + segment;
            }
        }

        if (string.endsWith(segment)) {
            string = string.substring(0, string.length() - segment.length());
        }

        return string;
    }

    /**
     * 返回对给定的字符串编码后的字符串。
     *
     * @param password
     * @return 编码后的字符串
     */
    public static final String encodePassword(String password) {
        if (!StringUtil.isEmpty(password)) {
            try {
                //创建具有指定算法名称的信息摘要
                MessageDigest md = MessageDigest.getInstance("MD5");
                //使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
                byte[] results = md.digest(password.getBytes());
                //将得到的字节数组变成字符串返回
                String resultString = byteArrayToHexString(results);
                byte[] resultBytes = resultString.toUpperCase().getBytes();
                String restvalue = new String(resultBytes);
                return restvalue;
            } catch (Exception ex) {
            }
        }
        return "";
    }

    /**
     * 转换字节数组为十六进制字符串
     *
     * @param b 字节数组
     * @return 十六进制字符串
     */
    public final static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    /**
     * 判断指定的字符串是否在数组中出现。
     *
     * @param str   字符串
     * @param array 字符串数组
     * @return 如果指定的字符串是否在数组中出现，返回它在数组中的下标，否则返回-１
     */
    public static int findPos(String str, String[] array) {
        if (array == null || array.length == 0) {
            return -1;
        }
        if (StringUtil.isEmpty(str)) {
            return -1;
        }

        for (int i = 0; i < array.length; i++) {
            String s = array[i];
            if (StringUtil.equalsIgnoreCase(s, str)) {
                return i;
            }
        }

        return -1;
    }

    public static int getLength(String str, String segment) {
        int length = 0;
        if (!StringUtil.isEmpty(str)) {
            String[] tmp = str.split(segment);
            for (String s : tmp) {
                if (!StringUtil.isEmpty(s)) {
                    length++;
                }
            }
        }
        return length;
    }

    public static String formatShow(String content) {
        if (isEmpty(content)) {
            return content;
        }
        String englishOneBlanks = " ";
        String englishTwoBlanks = "  ";
        String chineseOneBlanks = "　";
        String chineseTwoBlanks = "　　";
        String chineseTwoBlank2 = "";
        String chineseTwoBlank3 = "";
        try {
            content = content.trim();

            content = replaceString(content, "\r\n", "<br>");
            content = replaceString(content, "\n", "<br>");
            content = replaceString(content, "\r", "<br>");

            content = replaceString(content, "<p>", "<br>");
            content = replaceString(content, "<P>", "<br>");
            content = replaceString(content, "<BR>", "<br>");

            content = processSpecialAscii(content);

            content = content.replaceAll(englishTwoBlanks, "");
            content = content.replaceAll(chineseTwoBlanks, "");
            content = content.replaceAll(chineseTwoBlank2, "");
            content = content.replaceAll(chineseTwoBlank3, "");
            if ((content.length() > 1) && (content.substring(0, 1).equals(chineseOneBlanks))) {
                content = content.substring(1);
            }

            content = content.replaceAll("<br>" + englishOneBlanks, "<br>");
            content = content.replaceAll("<br>" + chineseOneBlanks, "<br>");

            content = content.replaceAll("(<br>){2,}", "<br>");

            if (porcessSuffixChars) {
                content = processLastChars(content);
            }

            content = processPicDemo(content);

            content = content.replaceAll("<br>", "<br>" + chineseTwoBlanks);

            if ((content.indexOf("&nbsp;&nbsp;") != 0) && (content.indexOf(chineseTwoBlanks) != 0)) {
                content = chineseTwoBlanks + content;
            }

            content = content.trim();
        } catch (Exception e) {
            System.out.println("正文格式化显示错误！" + e);
        }
        return content;
    }

    public static String processPicDemo(String content) {
        if (isEmpty(content)) {
            return content;
        }
        try {
            String[] brarr = content.split("<br>");
            boolean beforeIsPic = false;
            String[] brlink = new String[brarr.length];
            for (int i = 0; i < brarr.length; ++i) {
                brlink[i] = "<br>";
                String picdemo = brarr[i];
                if (picdemo.equals("")) {
                    brlink[i] = "";
                } else if (isPicSrc(picdemo)) {
                    beforeIsPic = true;
                } else if (beforeIsPic) {
                    beforeIsPic = false;
                    if (picdemo.length() > 40) {
                        continue;
                    }
                    String newpicdemo = "<p align=\"center\">" + picdemo + "</p>";
                    brarr[i] = newpicdemo;
                    brlink[i] = "";
                    brlink[(i - 1)] = "";
                }
            }

            String newcontent = "";
            for (int i = 0; i < brarr.length; ++i) {
                if (newcontent.length() == 0) {
                    newcontent = brarr[i];
                } else {
                    newcontent = newcontent + brlink[i] + brarr[i];
                }
            }
            return newcontent;
        } catch (Exception e) {
            System.out.println("处理图片下的文字描述错误！" + e);
        }
        return content;
    }

    private static boolean isPicSrc(String picdemo) {
        if (isEmpty(picdemo)) {
            return false;
        }

        if ((picdemo.indexOf("<IMAGE IDX=") >= 0) || (picdemo.indexOf("<IMG IDX=") >= 0)) {
            return true;
        }

        return (picdemo.indexOf("<img src=") >= 0);
    }

    public static String replaceString(String source, String keyword, String replacement) {
        if (source == null) {
            return "";
        }
        if ((keyword == null) || (replacement == null) || (keyword.equals(replacement))) {
            return source;
        }
        StringBuffer buffer = new StringBuffer(source.length() * 2);

        int lineBegin = 0;
        int lineEnd;
        while ((lineEnd = source.indexOf(keyword, lineBegin)) != -1) {
            buffer.append(source.substring(lineBegin, lineEnd));
            buffer.append(replacement);
            lineBegin = lineEnd + keyword.length();
        }
        buffer.append(source.substring(lineBegin, source.length()));

        return buffer.toString();
    }

    public static String processSpecialAscii(String content) {
        if (isEmpty(content)) {
            return content;
        }
        try {
            content = replaceString(content, "&#xD;", "");

            byte[] bytes = content.getBytes("UTF-8");
            for (int i = 0; i < bytes.length; ++i) {
                //	        bytes[i];

                if ((i + 1 < bytes.length) && (bytes[i] == -62) && (bytes[(i + 1)] == -96)) {
                    bytes[i] = 32;
                    bytes[(i + 1)] = 32;
                }

                if ((bytes[i] >= 0) && (bytes[i] <= 32)) {
                    bytes[i] = 32;
                }
            }
            content = new String(bytes, "UTF-8");
            return content;
        } catch (Exception e) {
            System.out.println("处理非打印字符错误！" + e);
        }
        return content;
    }

    public static String processLastChars(String content) {
        if (isEmpty(content)) {
            return content;
        }
        try {
            String[] brarr = content.split("<br>");
            for (int i = brarr.length - 1; i >= 0; --i) {
                if (brarr[i].trim().toLowerCase().equals("<trs_page_separator>")) {
                    brarr[i] = "";
                } else if (brarr[i].trim().toLowerCase().equals("</trs_page_separator>")) {
                    brarr[i] = "";
                } else if (isPageParam(brarr[i].trim())) {
                    brarr[i] = "";
                } else {
                    if (brarr[i].trim().length() > 3) {
                        break;
                    }
                    brarr[i] = "";
                }

            }

            String newcontent = "";
            for (int i = 0; i < brarr.length; ++i) {
                if (brarr[i].equals("")) {
                    continue;
                }
                if (newcontent.length() == 0) {
                    newcontent = brarr[i];
                } else {
                    newcontent = newcontent + "<br>" + brarr[i];
                }
            }
            return newcontent;
        } catch (Exception e) {
            System.out.println("处理最后字符错误！" + e);
        }
        return content;
    }

    public static boolean isPageParam(String content) {
        if (isEmpty(content)) {
            return false;
        }
        String pageParams = "12345678910上下一页|<>【】[]";
        for (int i = 0; i < content.length(); ++i) {
            String chars = content.substring(i, i + 1);
            if (pageParams.indexOf(chars) == -1) {
                return false;
            }
        }
        return true;
    }

    public static String generateKey(String key_prefix, String key) {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append(key_prefix).append(":").append(key).toString();
    }
}
