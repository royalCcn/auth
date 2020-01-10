package com.nan.common;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

/**
 * <p>
 * 自定义公共字段填充处理器
 * </p>
 */
@Component
public class GlobalMetaObjectHandler implements MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
		Object fieldValue = getFieldValByName("sta", metaObject);
		if (fieldValue == null) {
			setFieldValByName("sta", 0, metaObject);
		}
		System.out.println("插入方法实体填充");
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		Object fieldValue = getFieldValByName("sta", metaObject);
		if (fieldValue == null) {
			setFieldValByName("sta", 0, metaObject);
		}
		System.out.println("更新方法实体填充");
	}
	
}