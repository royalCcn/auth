package com.nan.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nan.entity.Employee;
import com.nan.mapper.EmployeeMapper;
import com.nan.service.EmployeeService;

/**
 * <p>
 * 员工 服务实现类
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
	
	public Employee getLoginUser() {
		
		 /**
		  SecurityContextHolder.getContext()获取安全上下文对象，就是那个保存在 ThreadLocal 里面的安全上下文对象
		 总是不为null(如果不存在，则创建一个authentication属性为null的empty安全上下文对象)
		 获取当前认证了的 principal(当事人),或者 request token (令牌)
		 如果没有认证，会是 null,该例子是认证之后的情况
		  */
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		//有登陆用户就返回登录用户，没有就返回null
		if (authentication != null) {
			if (authentication instanceof AnonymousAuthenticationToken) {
				return null;
			}

			if (authentication instanceof UsernamePasswordAuthenticationToken) {
				return (Employee) authentication.getPrincipal();
			}
		}

		return null;
	}

	@Override
	public List<Employee> list(int pageNum, int pageSize, String sortName, String sortOrder, String search) {
		List<Employee> results = new ArrayList<>();
		List<Employee> list =  baseMapper.list(pageNum,pageSize,sortName,sortOrder,search);
		if (list != null) {
			results = list;
		}
		return results;
	}
	
}
