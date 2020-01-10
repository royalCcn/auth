package com.nan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nan.entity.Application;

/**
 * <p>
 * 应用系统 Mapper 接口
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
public interface ApplicationMapper extends BaseMapper<Application> {
	
	List<Application> list(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("sortName")String sortName,
			@Param("sortOrder")String sortOrder,@Param("search")String search);

}
