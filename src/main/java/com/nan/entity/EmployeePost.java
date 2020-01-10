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
 * 员工职位
 * </p>
 *
 * @author nan
 * @since 2019-12-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EmployeePost对象", description="员工职位")
public class EmployeePost extends Model<EmployeePost> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "标识")
    private String id;

    @ApiModelProperty(value = "标识")
    private String postId;

    @ApiModelProperty(value = "标识")
    private String employeeId;

    @ApiModelProperty(value = "用户")
    private Employee employee;

    @ApiModelProperty(value = "职位")
    private Post post;


    public static final String ID = "id";

    public static final String POST_ID = "post_id";

    public static final String EMPLOYEE_ID = "employee_id";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
