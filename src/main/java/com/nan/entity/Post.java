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
 * 职位
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Post对象", description="职位")
public class Post extends Model<Post> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "标识")
    private String id;

    @ApiModelProperty(value = "名称")
    private String postName;

    @ApiModelProperty(value = "编号")
    private String postCode;

    @ApiModelProperty(value = "状态")
    private int sta;

    @ApiModelProperty(value = "说明")
    private String info;


    public static final String ID = "id";

    public static final String POST_NAME = "post_name";

    public static final String POST_CODE = "post_code";

    public static final String STA = "sta";

    public static final String INFO = "info";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
