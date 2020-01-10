package com.nan.entity;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Menu对象", description="菜单")
public class Menu extends Model<Menu> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "标识")
    private String id;

    @ApiModelProperty(value = "标识")
    private String menuId;

    @ApiModelProperty(value = "名称")
    private String menuName;

    @ApiModelProperty(value = "编号")
    private String menuCode;
    
    @ApiModelProperty(value = "顺序")
    private int menuOrder;
    
    @ApiModelProperty(value = "级别")
    private int menuLevel;
    
    @ApiModelProperty(value = "图标")
    private String icon;
    
    @ApiModelProperty(value = "链接")
    private String url;

    @ApiModelProperty(value = "状态")
    private int sta;

    @ApiModelProperty(value = "说明")
    private String info;
    
    @TableField(exist = false)
    @ApiModelProperty(value = "集合说明")
    private List<Menu> menus;


    public static final String ID = "id";

    public static final String MENU_ID = "menu_id";

    public static final String MENU_NAME = "menu_name";

    public static final String MENU_CODE = "menu_code";
    
    public static final String MENU_ORDER = "menu_order";
    
    public static final String MENU_LEVEL = "menu_level";
    
    public static final String ICON = "icon";
    
    public static final String URL = "url";

    public static final String STA = "sta";

    public static final String INFO = "info";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
