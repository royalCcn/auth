package com.nan.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nan.entity.User;
import com.nan.mapper.UserMapper;
import com.nan.service.UserService;

/**
 * <p>
 * 人员管理 服务实现类
 * </p>
 *
 * @author nan
 * @since 2019-12-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
	
	@Override
	public List<User> list(int pageNum, int pageSize, String sortName, String sortOrder, String search) {
		List<User> results = new ArrayList<>();
		List<User> list =  baseMapper.list(pageNum,pageSize,sortName,sortOrder,search);
		if (list != null) {
			results = list;
		}
		return results;
	}

}
