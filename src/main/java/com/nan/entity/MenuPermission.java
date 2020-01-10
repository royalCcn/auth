package com.nan.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单权限
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MenuPermission对象", description="菜单权限")
public class MenuPermission extends Model<MenuPermission> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "标识")
    private String id;

    @ApiModelProperty(value = "标识")
    private String menuId;

    @ApiModelProperty(value = "标识")
    private String permissionId;

    @ApiModelProperty(value = "菜单")
    private Menu menu;

    @ApiModelProperty(value = "权限")
    private Permission permission;


    public static final String ID = "id";

    public static final String MENU_ID = "menu_id";

    public static final String PERMISSION_ID = "permission_id";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
