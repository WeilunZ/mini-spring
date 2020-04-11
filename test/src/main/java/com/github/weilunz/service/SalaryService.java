package com.github.weilunz.service;

import com.github.weilunz.beans.Bean;

/**
 * @program: mini-spring
 * @description:
 * @author: wl.zhou
 * @create: 2020-04-11 22:38
 **/
@Bean
public class SalaryService {
    public Integer calSalary(Integer experience){
        return experience * 5000;
    }
}
