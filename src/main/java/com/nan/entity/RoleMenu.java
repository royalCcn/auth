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
 * 角色菜单
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="RoleMenu对象", description="角色菜单")
public class RoleMenu extends Model<RoleMenu> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "标识")
    private String id;

    @ApiModelProperty(value = "标识")
    private String roleId;

    @ApiModelProperty(value = "标识")
    private String menuId;

    @ApiModelProperty(value = "角色")
    private Role role;

    @ApiModelProperty(value = "菜单")
    private Menu menu;


    public static final String ID = "id";

    public static final String ROLE_ID = "role_id";

    public static final String MENU_ID = "menu_id";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
