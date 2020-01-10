package com.nan.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nan.entity.Student;
import com.nan.mapper.StudentMapper;
import com.nan.service.StudentService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author nan
 * @since 2019-12-14
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> getLate() {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        List<Student> studentList = studentMapper.selectList(queryWrapper);
        return studentList;
    }

    @Override
    public Student[] getInfo(String name) {
    	QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
    	queryWrapper.eq("name",name);
    	Student student = studentMapper.selectOne(queryWrapper);
    	Student[] stuArr = new Student[1];
    	stuArr[0]=student;
    	return stuArr;
    }

    @Override
    public int add(Student student) {
       return studentMapper.insert(student);
    }

    @Override
    public int delete(String studentNum) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("studentNum",studentNum);
       return studentMapper.delete(queryWrapper);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public List<Student> page(long current,long size) {
        IPage iPage =  studentMapper.selectPage(new Page(current,size),null);
        List<Student> studentList =  iPage.getRecords();
        return studentList;
    }

}
