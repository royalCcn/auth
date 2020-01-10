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
 * 员工组
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Employeegroup对象", description="员工组")
public class Employeegroup extends Model<Employeegroup> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "标识")
    private String id;

    @ApiModelProperty(value = "标识")
    @TableField("employeeGroup_id")
    private String employeegroupId;

    @ApiModelProperty(value = "名称")
    @TableField("empGroup_name")
    private String empgroupName;

    @ApiModelProperty(value = "编号")
    @TableField("empGroup_code")
    private String empgroupCode;

    @ApiModelProperty(value = "状态")
    private int sta;

    @ApiModelProperty(value = "说明")
    private String info;


    public static final String ID = "id";

    public static final String EMPLOYEEGROUP_ID = "employeeGroup_id";

    public static final String EMPGROUP_NAME = "empGroup_name";

    public static final String EMPGROUP_CODE = "empGroup_code";

    public static final String STA = "sta";

    public static final String INFO = "info";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
