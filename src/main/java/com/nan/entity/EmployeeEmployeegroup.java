package com.nan.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 员工员工组
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EmployeeEmployeegroup对象", description="员工员工组")
public class EmployeeEmployeegroup extends Model<EmployeeEmployeegroup> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "标识")
    private String id;

    @ApiModelProperty(value = "标识")
    private String employeeId;

    @ApiModelProperty(value = "标识")
    @TableField("employeeGroup_id")
    private String employeegroupId;

    @ApiModelProperty(value = "用户")
    private Employee employee;
    
    @ApiModelProperty(value = "用户组")
    private Employeegroup employeegroup;


    public static final String ID = "id";

    public static final String EMPLOYEE_ID = "employee_id";

    public static final String EMPLOYEEGROUP_ID = "employeeGroup_id";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
