package com.nan.mapper;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nan.entity.Post;

/**
 * <p>
 * 职位 Mapper 接口
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
public interface PostMapper extends BaseMapper<Post> {
	
	List<Post> list(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("sortName")String sortName,
			@Param("sortOrder")String sortOrder,@Param("search")String search);

}
