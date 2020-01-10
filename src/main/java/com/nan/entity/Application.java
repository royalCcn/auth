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
 * 应用系统
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Application对象", description="应用系统")
public class Application extends Model<Application> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "标识")
    private String id;

    @ApiModelProperty(value = "名称")
    private String appName;

    @ApiModelProperty(value = "编号")
    private String appCode;

    @ApiModelProperty(value = "状态")
    private int sta;

    @ApiModelProperty(value = "说明")
    private String info;


    public static final String ID = "id";

    public static final String APP_NAME = "app_name";

    public static final String APP_CODE = "app_code";

    public static final String STA = "sta";

    public static final String INFO = "info";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
