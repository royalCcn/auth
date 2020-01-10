package com.nan.entity;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 员工
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Employee对象", description="员工")
public class Employee extends Model<Employee> {

    private static final long serialVersionUID = 1L;

    @ExcelIgnore
    @ApiModelProperty(value = "标识")
    private String id;

    @ExcelProperty(value = "名称", index = 0)
    @ApiModelProperty(value = "名称")
    private String empName;

    @ExcelProperty(value = "编号", index = 1)
    @ApiModelProperty(value = "编号")
    private String empCode;

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "密码")
    private String pwd;

    @ApiModelProperty(value = "类型")
    private String empType;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "职位")
    private String post;

    @ExcelProperty(value = "入职时间", index = 2)
    @ApiModelProperty(value = "入职时间")
    private Date hireDate;

    @ApiModelProperty(value = "毕业时间")
    private Date graduation;

    @ApiModelProperty(value = "教育背景")
    private String education;

    @ApiModelProperty(value = "个人简历")
    private String empResume;

    @ApiModelProperty(value = "图片")
    private String image;

    @ApiModelProperty(value = "状态")
    private int sta;

    @ApiModelProperty(value = "说明")
    private String info;


    public static final String ID = "id";

    public static final String EMP_NAME = "emp_name";

    public static final String EMP_CODE = "emp_code";

    public static final String ACCOUNT = "account";

    public static final String PWD = "pwd";

    public static final String EMP_TYPE = "emp_type";

    public static final String PHONE = "phone";

    public static final String ADDRESS = "address";

    public static final String POST = "post";

    public static final String HIRE_DATE = "hire_date";

    public static final String GRADUATION = "graduation";

    public static final String EDUCATION = "education";

    public static final String EMP_RESUME = "emp_resume";

    public static final String IMAGE = "image";

    public static final String STA = "sta";

    public static final String INFO = "info";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
