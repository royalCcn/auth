package com.nan.mapper;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nan.entity.User;

/**
 * <p>
 * 人员管理 Mapper 接口
 * </p>
 *
 * @author nan
 * @since 2019-12-15
 */
public interface UserMapper extends BaseMapper<User> {
	
	List<User> list(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("sortName")String sortName,
			@Param("sortOrder")String sortOrder,@Param("search")String search);

}
