package com.example.demo.service;


import com.example.demo.dao.PersonsDao;
import com.example.demo.entity.Persons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonsService {
    @Autowired
    private PersonsDao personsDao;

    public List<Persons> searchPerson(String name,String source,String dpt){
        System.out.println("正在查询");
        return personsDao.searchPerson(name,source,dpt);
    }

    public void add(Persons persons){
        System.out.println("正在添加");
        personsDao.addPerson(persons);
    }

    public void update(Persons persons){
        System.out.println("正在修改");
        personsDao.updatePerson(persons);
    }
    public List<Persons> selectAll() {
        System.out.println("正在显示数据");
        return personsDao.selectPersonsAll();
    }

    public void delete(int jobnumber) {
        System.out.println("正在删除一条记录");

        personsDao.deletePerson(jobnumber);
    }

    public Persons getPerson(int jobnumber){
        System.out.println("获取表单到修改窗口");
        return personsDao.getPerson(jobnumber);
    }

}
