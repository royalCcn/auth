package com.nan.service;

import com.nan.entity.Repair;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author nan
 * @since 2019-12-15
 */
public interface RepairService extends IService<Repair> {
    List<Repair> select();

    int add(Repair repair);

    List<Repair> getHistoryByDorm(String dormId);

}
