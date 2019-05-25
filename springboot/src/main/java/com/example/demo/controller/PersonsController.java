package com.example.demo.controller;

import com.example.demo.entity.Persons;
import com.example.demo.service.PersonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController("personController")
public class PersonsController {
    @Autowired
    private PersonsService personsService;
    @PostMapping(value="/api/addPerson")
    public void addPerson(Persons persons){
        System.out.println("hello controller");
        personsService.add(persons);
    }

    @RequestMapping(value ="/api/deleteOne", method = RequestMethod.POST)
    public void deletePerson(@RequestParam(value="jobnumber") int jobnumber){
        personsService.delete(jobnumber);
        System.out.println("接受到请求,将要删除工号为"+jobnumber+"的员工信息");
        if(jobnumber==0)
        {
            System.out.println("no param");
        }
    }
    @PostMapping(value = "/api/getPerson")
    public Persons getPerson(int jobnumber){
        return personsService.getPerson(jobnumber);
    }
    @GetMapping(value="/api/selectAll")
    public List<Persons> selectAll(){
        return personsService.selectAll();
    }
    @RequestMapping(value="/api/searchPerson",method =RequestMethod.POST)
    public List<Persons> searchPerson(String name,String source,String dpt){
        System.out.println("接受到查询参数"+name+source+dpt);
        List<Persons> persons=personsService.searchPerson(name,source,dpt);
        System.out.println("查询到的记录为"+persons);
        return persons;

    }
    @RequestMapping(value = "/api/updatePerson", method = RequestMethod.POST)
    public void updatePerson(Persons persons) {
        System.out.println("接受到要修改的参数");
        personsService.update(persons);
    }
}
