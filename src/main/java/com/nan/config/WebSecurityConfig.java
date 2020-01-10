package com.nan.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	private String pwdQuery = "select * "
			+ "from employee where emp_name = ?";
	private String roleQuery = "select emp.emp_name,role.role_name "
			+ "from employee emp,role role,role_employee re "
			+ "where emp.id = re.employee_id and role.id = re.role_id "
			+ "and emp.emp_name = ?";
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder passwordEncoder = new PasswordEncoder() {
			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				return encodedPassword.equals(rawPassword.toString());
			}
			
			@Override
			public String encode(CharSequence rawPassword) {
				return rawPassword.toString();
			}
		};
		auth.jdbcAuthentication()
			//密码编译器
			.passwordEncoder(passwordEncoder)
			.dataSource(dataSource)
			.usersByUsernameQuery(pwdQuery)
			.authoritiesByUsernameQuery(roleQuery);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//开启跨域
		http.cors().and().csrf().disable();
		http.authorizeRequests()
			//匿名请求：不需要进行登录拦截的url
			//.antMatchers("/js/**").permitAll()
		
			//其他的路径都是登录权限登录认证后才可访问
			.anyRequest().authenticated()
			.and().rememberMe().tokenValiditySeconds(86400).key("remember-me-key")
			
			.and()
			
			/*登录配置*/
              .formLogin()
              	.loginPage("/login")//登录页，当未登录时会重定向到该页面
                  .loginProcessingUrl("/employee/login")//restful登录请求地址
                  .permitAll()
                  .and()
              /*登出配置*/
              .logout().permitAll();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers("/employee/**")
			.antMatchers("/menu/**")
			.antMatchers("/css/**")
			.antMatchers("/file/**")
			.antMatchers("/html/**")
			.antMatchers("/icon/**")
			.antMatchers("/image/**")
			.antMatchers("/js/**")
			.antMatchers("swagger-ui.html")
			.antMatchers("webjars/**")
			.antMatchers("v2/**")
			.antMatchers("swagger-resources/**");
	}

}