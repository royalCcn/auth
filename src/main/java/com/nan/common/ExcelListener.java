package com.nan.common;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;

public class ExcelListener extends AnalysisEventListener<Object> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelListener.class);
	
	private List<Object> list = new ArrayList<>();
	 
    @Override
    public void invoke(Object obj, AnalysisContext context) {
    	LOGGER.info("解析到一条数据:{}", JSON.toJSONString(obj));
        list.add(obj);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= Contants.BATCH_COUNT) {
            list.clear();
        }
    }

	@Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        list.clear();//解析结束销毁不用的资源
    	LOGGER.info("所有数据解析完成！");
    }

}
