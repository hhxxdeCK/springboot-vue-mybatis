package com.example.demo.dao;

import com.example.demo.entity.Persons;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("PersonsMapper")
public interface PersonsDao {
    @Select("select * from persons where jobnumber = #{jobnumber}")
    Persons getPerson(int jobnumber);

    @Insert("insert into persons(jobnumber,name,sex,source,address,dpt,tel,mail,post) values(#{jobnumber},#{name},#{sex},#{source},#{address},#{dpt},#{tel},#{mail},#{post})")
    void addPerson(Persons persons);

    @Select("select * from persons")
    List<Persons> selectPersonsAll();

    @Delete("delete from persons where jobnumber = #{jobnumber}")
    void deletePerson(int jobnumber);

    @Update("update persons set name=#{name},sex=#{sex},source=#{source},address=#{address},dpt=#{dpt},tel=#{tel},mail=#{mail},post=#{post} where jobnumber=#{jobnumber}")
    void updatePerson(Persons persons);

    @SelectProvider(type = SearchPerson.class, method = "searchPerson")
    @Results(id = "ResultMap", value = {
            @Result(property = "jobnumber", column = "jobnumber"),
            @Result(property = "name", column = "name"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "source", column = "source"),
            @Result(property = "address", column = "address"),
            @Result(property = "dpt", column = "dpt"),
            @Result(property = "tel", column = "tel"),
            @Result(property = "mail", column = "mail"),
            @Result(property = "post", column = "post")
    })
    List<Persons> searchPerson(String name, String source, String dpt);
}

