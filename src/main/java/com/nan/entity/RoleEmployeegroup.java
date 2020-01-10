package com.nan.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色员工组
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="RoleEmployeegroup对象", description="角色员工组")
public class RoleEmployeegroup extends Model<RoleEmployeegroup> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "标识")
    private String id;

    @ApiModelProperty(value = "标识")
    private String roleId;

    @ApiModelProperty(value = "标识")
    @TableField("employeeGroup_id")
    private String employeegroupId;

    @ApiModelProperty(value = "角色")
    private Role role;

    @ApiModelProperty(value = "用户组")
    private Employeegroup employeegroup;


    public static final String ID = "id";

    public static final String ROLE_ID = "role_id";

    public static final String EMPLOYEEGROUP_ID = "employeeGroup_id";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
