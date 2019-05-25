package com.example.demo.dao;

public class SearchPerson {
        public String searchPerson(String name,String source,String dpt){
            System.out.println("正在执行多重查询方法");
            StringBuffer sql=new StringBuffer("select * from persons  where ");
            if(!name.equals("")) {
                sql.append("name=" + "'" + name + "'");
                if (!source.equals("")) {
                    sql.append("and source=" + "'" + source + "'");
                }

                if (!dpt.equals("")) {
                    sql.append("and dpt=" + "'" + dpt + "'");
                }
            }
            else{
                sql.append("source=" + "'" + source + "'");
                if (!dpt.equals("")) {
                    sql.append("and dpt=" + "'" + dpt + "'");
                }
            }
            return sql.toString();
            /*return "select * from persons where name=" + name;*/
        }
    }

