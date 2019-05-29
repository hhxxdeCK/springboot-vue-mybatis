package com.example.demo.dao;
import java.util.regex.*;
public class SearchPerson {
    private String CHECKSQL ="/\\w*((\\%27)|(\\’))((\\%6F)|o|(\\%4F))((\\%72)|r|(\\%52))/ix";
    public String searchPerson(String name, String source, String dpt) {
        System.out.println("正在执行多重查询方法");
        Pattern.matches(CHECKSQL,name);
        Pattern.matches(CHECKSQL,source);
        Pattern.matches(CHECKSQL,dpt);
        StringBuffer sql = new StringBuffer("select * from persons  where ");
        if (!name.equals("")) {
            sql.append("name=?");
            if (!source.equals("")) {
                sql.append("and source=" + "'" + source + "'");
            }

            if (!dpt.equals("")) {
                sql.append("and dpt=" + "'" + dpt + "'");
            }
        } else {
            sql.append("source=" + "'" + source + "'");
            if (!dpt.equals("")) {
                sql.append("and dpt=" + "'" + dpt + "'");
            }
        }
        return sql.toString();
    }
}

