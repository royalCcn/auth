package com.nan.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 员工角色
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="RoleEmployee对象", description="员工角色")
public class RoleEmployee extends Model<RoleEmployee> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "标识")
    private String id;

    @ApiModelProperty(value = "标识")
    private String employeeId;

    @ApiModelProperty(value = "标识")
    private String roleId;

    @ApiModelProperty(value = "用户")
    private Employee employee;

    @ApiModelProperty(value = "角色")
    private Role role;


    public static final String ID = "id";

    public static final String EMPLOYEE_ID = "employee_id";

    public static final String ROLE_ID = "role_id";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
