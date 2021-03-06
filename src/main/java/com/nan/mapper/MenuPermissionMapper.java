package com.nan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nan.entity.MenuPermission;

/**
 * <p>
 * 菜单权限 Mapper 接口
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
public interface MenuPermissionMapper extends BaseMapper<MenuPermission> {
	
	List<MenuPermission> list(@Param("pageNum")int pageNum, @Param("pageSize")int pageSize,
			@Param("sortName")String sortName, @Param("sortOrder")String sortOrder, @Param("search")String search);

}
