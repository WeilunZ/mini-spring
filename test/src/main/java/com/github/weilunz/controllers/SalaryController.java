package com.github.weilunz.controllers;

import com.github.weilunz.beans.Autowired;
import com.github.weilunz.service.SalaryService;
import com.github.weilunz.web.mvc.Controller;
import com.github.weilunz.web.mvc.RequestMapping;
import com.github.weilunz.web.mvc.RequestParam;

/**
 * @program: mini-spring
 * @description:
 * @author: wl.zhou
 * @create: 2020-04-11 16:37
 **/
@Controller
public class SalaryController {
    @Autowired
    SalaryService salaryService;

    @RequestMapping("/get_salary.json")
    public Integer getSalary(@RequestParam("name") String name,
                             @RequestParam("experience") String experience){
        return salaryService.calSalary(Integer.parseInt(experience));
    }
}
