package com.liang.ticketbooksystem.utils;


import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class convertToH2 {

    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\34773\\Desktop\\cinema s.sql");
        String content = Files.toString(file, Charsets.UTF_8);

        content = "SET MODE MYSQL;\n\n" + content;

        content = content.replaceAll("`", "");
        content = content.replaceAll("COLLATE.*(?=D)", "");
        content = content.replaceAll("COMMENT.*'(?=,)", "");
        content = content.replaceAll("\\).*ENGINE.*(?=;)", ")");
        content = content.replaceAll("DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", " AS CURRENT_TIMESTAMP");

        content = uniqueKey(content);

        System.out.println(content);
    }

    /**
     * h2的索引名必须全局唯一
     *
     * @param content sql建表脚本
     * @return 替换索引名为全局唯一
     */
    private static String uniqueKey(String content) {
        int inc = 0;
        Pattern pattern = Pattern.compile("(?<=KEY )(.*?)(?= \\()");
        Matcher matcher = pattern.matcher(content);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group() + inc++);
        }
        matcher.appendTail(sb);
        content = sb.toString();
        return content;
    }

}