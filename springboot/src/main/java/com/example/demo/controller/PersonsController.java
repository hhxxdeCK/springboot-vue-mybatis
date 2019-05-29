package com.example.demo.controller;

import com.example.demo.entity.Persons;
import com.example.demo.service.PersonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController("personController")
public class PersonsController {
    @Autowired
    private PersonsService personsService;

    //添加
    @PostMapping(value = "/api/v1/Persons")
    public void addPerson(Persons persons) {
        System.out.println("hello controller");
        personsService.add(persons);
    }

    //删除
    @DeleteMapping(value = "/api/v1/Persons/jobnumber")
    public void deletePerson(/*@RequestParam(value = "jobnumber")*/ int jobnumber) {
        personsService.delete(jobnumber);
        System.out.println("接受到请求,将要删除工号为" + jobnumber + "的员工信息");
        if (jobnumber == 0) {
            System.out.println("no param");
        }
    }

    //获取到修改列表
    @PostMapping(value = "/api/v1/Persons/jobnumber")
    public Persons getPerson(int jobnumber) {
        System.out.println(jobnumber);
        System.out.println(personsService.getPerson(jobnumber));
        return personsService.getPerson(jobnumber);
    }

    //获取所有
    @GetMapping(value = "/api/v1/Persons")
    public List<Persons> selectAll() {
        return personsService.selectAll();
    }

    //查询
    @PostMapping(value = "/api/v1/Persons/NameSourceDpt")
    public List<Persons> searchPerson(String name, String source, String dpt) {
        System.out.println("接受到查询参数" + name + source + dpt);
        List<Persons> persons = personsService.searchPerson(name, source, dpt);
        System.out.println("查询到的记录为" + persons);
        return persons;

    }

    //修改
    @RequestMapping(value = "/api/v1/Persons", method = RequestMethod.PUT)
    public void updatePerson(Persons persons) {
        System.out.println("接受到要修改的参数");
        personsService.update(persons);
    }
}
