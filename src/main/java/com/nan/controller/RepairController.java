package com.nan.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nan.common.Result;
import com.nan.entity.Repair;
import com.nan.service.RepairService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author nan
 * @since 2019-12-15
 */
@RestController
@RequestMapping("/repair")
public class RepairController {

    @Autowired
    private RepairService repairService;

    Repair repair = new Repair();

    //查询全部
    @RequestMapping("/select")
    public Result select(){
        List<Repair> repairList = new ArrayList<Repair>();
        repairList= repairService.select();
        if(repairList!=null)
            return new Result(Result.SUCCESS, "1", repairList);
        else
            return new Result(Result.ERROR, "-1", "查询失败");
    }

    //提交报修信息
    @PostMapping("/add")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "dormId", value = "宿舍号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "descr", value = "维修描述", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "mobile", value = "联系方式", required = true, dataType = "String"),

    })
    public Result post(String dormId ,String descr,String mobile){
        repair.setDormId(dormId);
        repair.setDescr(descr);
        repair.setMobile(mobile);
        int add = repairService.add(repair);
        if (add != 0)
            return new Result(Result.SUCCESS, "1", "添加成功");
        else
            return new Result(Result.ERROR, "-1", "添加失败");
    }

    //根据宿舍分组查询状态
    @PostMapping("/getHistoryByDorm")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "dormId", value = "宿舍号", required = true, dataType = "String")
 })
      public Result getHistoryByDorm(String dormId){
        List<Repair> repairList = new ArrayList<Repair>();
        repairList =  repairService.getHistoryByDorm(dormId);
        if(repairList!=null)
            return new Result(Result.SUCCESS, "1",repairList);
        else
            return new Result(Result.ERROR, "-1", "查询失败");

    }


}
