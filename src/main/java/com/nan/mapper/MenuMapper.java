package com.nan.mapper;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nan.entity.Menu;

/**
 * <p>
 * 菜单 Mapper 接口
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
public interface MenuMapper extends BaseMapper<Menu> {
	
	List<Menu> getMenuByEmpName(@Param("empName")String empName);
	
    List<Menu> list(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("sortName")String sortName,
			@Param("sortOrder")String sortOrder,@Param("search")String search);

}
