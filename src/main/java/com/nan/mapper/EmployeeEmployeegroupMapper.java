package com.nan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nan.entity.EmployeeEmployeegroup;

/**
 * <p>
 * 员工员工组 Mapper 接口
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
public interface EmployeeEmployeegroupMapper extends BaseMapper<EmployeeEmployeegroup> {
	
	List<EmployeeEmployeegroup> list(@Param("pageNum")int pageNum, @Param("pageSize")int pageSize,
			@Param("sortName")String sortName, @Param("sortOrder")String sortOrder, @Param("search")String search);

}
