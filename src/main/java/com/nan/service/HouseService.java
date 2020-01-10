package com.nan.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nan.entity.House;

/**
 * <p>
 * 宿舍管理 服务类
 * </p>
 *
 * @author nan
 * @since 2019-12-15
 */
public interface HouseService extends IService<House> {
	
	List<House> list(int pageNum, int pageSize, String sortName, String sortOrder, String search);

}
