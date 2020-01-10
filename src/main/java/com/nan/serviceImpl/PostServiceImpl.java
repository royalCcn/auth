package com.nan.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nan.entity.Post;
import com.nan.mapper.PostMapper;
import com.nan.service.PostService;

/**
 * <p>
 * 职位 服务实现类
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

	@Override
	public List<Post> list(int pageNum, int pageSize, String sortName, String sortOrder, String search) {
		List<Post> results = new ArrayList<>();
		List<Post> list =  baseMapper.list(pageNum,pageSize,sortName,sortOrder,search);
		if (list != null) {
			results = list;
		}
		return results;
	}
	
}
