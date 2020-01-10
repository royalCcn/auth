package com.nan.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nan.common.Result;
import com.nan.entity.Student;
import com.nan.service.StudentService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author nan
 * @since 2019-12-14
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    Student student = new Student();

    //获取晚归学生列表
    @RequestMapping("/getLate")
    public Result getLate() {
        if (studentService.getLate() != null)
            return new Result(Result.SUCCESS, "1", studentService.getLate());
        else
            return new Result(Result.ERROR, "-1", "查询失败");
    }

    //通过学生姓名获取晚归信息
    @PostMapping("/getInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "name", value = "姓名", required = true, dataType = "String"),
    })
    public Result getInfo(String name)throws Exception {
    	Student[] stuArr =  studentService.getInfo(name);
    	if (student != null) {
    	    return new Result(Result.SUCCESS, "1", stuArr);
    	} else
    	    return new Result(Result.ERROR, "-1", "查询失败");
    }

    //增加晚归学生信息
    @PostMapping("/add")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "name", value = "姓名", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "studentNum", value = "学号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "dormId", value = "宿舍", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "time", value = "时间", required = true, dataType = "String"),

    })
    public Result add(String name, String studentNum, String dormId, String time) {
        student.setName(name);
        student.setStudentNum(studentNum);
        student.setTime(time);
        student.setDormId(dormId);
        int add = studentService.add(student);
        if (add != 0)
            return new Result(Result.SUCCESS, "1", "添加成功");
        else
            return new Result(Result.ERROR, "-1", "添加失败");


    }


    //删除晚归学生
    @RequestMapping("/delete/{studentNum}")
    public Result delete(@PathVariable String studentNum, HttpServletResponse response) {
        int delete = studentService.delete(studentNum);
        if (delete != 0)
            return new Result(Result.SUCCESS, "1", "删除成功");
        else
            return new Result(Result.ERROR, "-1", "删除失败");
    }
    
    @RequestMapping("/page/{currentPage}/{PageSize}")
    public Result page(@PathVariable long currentPage, @PathVariable long PageSize) {
         List<Student> studentList =  studentService.page(currentPage,PageSize);
         if(studentList!=null)
         return new Result(Result.SUCCESS, "1", studentList);
         else
             return new Result(Result.ERROR, "-1", "查询失败");
    }
}