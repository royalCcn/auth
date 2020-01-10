package com.nan.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nan.entity.House;
import com.nan.mapper.HouseMapper;
import com.nan.service.HouseService;

/**
 * <p>
 * 宿舍管理 服务实现类
 * </p>
 *
 * @author nan
 * @since 2019-12-15
 */
@Service
public class HouseServiceImpl extends ServiceImpl<HouseMapper, House> implements HouseService {
	
	@Override
	public List<House> list(int pageNum, int pageSize, String sortName, String sortOrder, String search) {
		List<House> results = new ArrayList<>();
		List<House> list =  baseMapper.list(pageNum,pageSize,sortName,sortOrder,search);
		if (list != null) {
			results = list;
		}
		return results;
	}

}
