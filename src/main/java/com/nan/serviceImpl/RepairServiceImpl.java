package com.nan.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nan.entity.Repair;
import com.nan.mapper.RepairMapper;
import com.nan.service.RepairService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author nan
 * @since 2019-12-15
 */
@Service
public class RepairServiceImpl extends ServiceImpl<RepairMapper, Repair> implements RepairService {

    @Autowired
    private RepairMapper repairMapper;


    @Override
    public List<Repair> select() {
        QueryWrapper<Repair> queryWrapper = new QueryWrapper<>();
        List<Repair> repairList = repairMapper.selectList(queryWrapper);
        return repairList;
    }

    @Override
    public int add(Repair repair) {
        return repairMapper.insert(repair);
    }

    @Override
    public List<Repair> getHistoryByDorm(String dormId) {
        QueryWrapper<Repair> queryWrapper = new QueryWrapper<>();
         queryWrapper.eq("dormId",dormId);
         List<Repair> repairList = repairMapper.selectList(queryWrapper);
         return repairList;
    }
}
