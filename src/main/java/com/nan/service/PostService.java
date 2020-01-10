package com.nan.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nan.entity.Post;

/**
 * <p>
 * 职位 服务类
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
public interface PostService extends IService<Post> {
	
	List<Post> list(int pageNum, int pageSize, String sortName, String sortOrder, String search);

}
