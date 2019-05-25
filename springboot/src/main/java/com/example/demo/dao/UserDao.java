package com.example.demo.dao;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("userMapper")
public interface UserDao {
    @Select("select * from hr where username = #{username} and password=#{password}")
    User login(String username,String password);
}

