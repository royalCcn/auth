package com.nan.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nan.entity.User;

/**
 * <p>
 * 人员管理 服务类
 * </p>
 *
 * @author nan
 * @since 2019-12-15
 */
public interface UserService extends IService<User> {
	
	List<User> list(int pageNum, int pageSize, String sortName, String sortOrder, String search);

}
