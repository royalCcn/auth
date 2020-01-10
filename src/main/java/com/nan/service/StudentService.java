package com.nan.service;

import com.nan.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author nan
 * @since 2019-12-14
 */
public interface StudentService extends IService<Student> {

     List<Student> getLate();

     Student[] getInfo(String name);

     int add(Student student);

     int delete(String studentNum);
   
     List<Student> page(long current ,long size);
}
