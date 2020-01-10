package com.nan.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nan.entity.Application;
import com.nan.mapper.ApplicationMapper;
import com.nan.service.ApplicationService;

/**
 * <p>
 * 应用系统 服务实现类
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
@Service
public class ApplicationServiceImpl extends ServiceImpl<ApplicationMapper, Application> implements ApplicationService {

	@Override
	public List<Application> list(int pageNum, int pageSize, String sortName, String sortOrder, String search) {
		List<Application> results = new ArrayList<>();
		List<Application> list =  baseMapper.list(pageNum,pageSize,sortName,sortOrder,search);
		if (list != null) {
			results = list;
		}
		return results;
	}
	
}
