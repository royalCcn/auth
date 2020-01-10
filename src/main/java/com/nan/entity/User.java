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
 * 人员管理
 * </p>
 *
 * @author nan
 * @since 2019-12-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="User对象", description="人员管理")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "标志")
    private String id;

    @ApiModelProperty(value = "标志")
    private String houseId;

    @ApiModelProperty(value = "名称")
    private String userName;

    @ApiModelProperty(value = "编号")
    private String userCode;

    @ApiModelProperty(value = "是否在校")
    private Integer isIn;

    @ApiModelProperty(value = "状态")
    private Integer sta;

    @ApiModelProperty(value = "说明")
    private String info;


    public static final String ID = "id";

    public static final String HOUSE_ID = "house_id";

    public static final String USER_NAME = "user_name";

    public static final String USER_CODE = "user_code";

    public static final String IS_IN = "is_in";

    public static final String STA = "sta";

    public static final String INFO = "info";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
