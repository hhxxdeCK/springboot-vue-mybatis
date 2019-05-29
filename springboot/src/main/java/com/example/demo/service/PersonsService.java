package com.example.demo.service;


import com.example.demo.dao.PersonsDao;
import com.example.demo.entity.Persons;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PersonsService {
    @Autowired
    private PersonsDao personsDao;

    public List<Persons> searchPerson(String name, String source, String dpt) {
        log.info("正在查询");
        return personsDao.searchPerson(name, source, dpt);
    }

    public void add(Persons persons) {
        log.info("正在添加");
        personsDao.addPerson(persons);
    }

    public void update(Persons persons) {
        log.info("正在修改");
        personsDao.updatePerson(persons);
    }

    public List<Persons> selectAll() {
        log.info("正在显示数据");
        return personsDao.selectPersonsAll();
    }

    public void delete(int jobnumber) {
        log.info("正在删除一条记录");
        personsDao.deletePerson(jobnumber);
    }

    public Persons getPerson(int jobnumber) {
        log.info("获取表单到修改窗口");
        return personsDao.getPerson(jobnumber);
    }

}
