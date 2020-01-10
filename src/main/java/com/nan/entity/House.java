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
 * 宿舍管理
 * </p>
 *
 * @author nan
 * @since 2019-12-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="House对象", description="宿舍管理")
public class House extends Model<House> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "标志")
    private String id;

    @ApiModelProperty(value = "名称")
    private String houseName;

    @ApiModelProperty(value = "编号")
    private String houseCode;

    @ApiModelProperty(value = "人数")
    private Integer num;

    @ApiModelProperty(value = "是否欠费")
    private Integer isArrearage;

    @ApiModelProperty(value = "状态")
    private Integer sta;

    @ApiModelProperty(value = "说明")
    private String info;


    public static final String ID = "id";

    public static final String HOUSE_NAME = "house_name";

    public static final String HOUSE_CODE = "house_code";

    public static final String NUM = "num";

    public static final String IS_ARREARAGE = "is_arrearage";

    public static final String STA = "sta";

    public static final String INFO = "info";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
