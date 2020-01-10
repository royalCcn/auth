package com.nan.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author nan
 * @since 2019-12-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Student对象", description="")
public class Student extends Model<Student> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "宿舍成员姓名")
    private String name;

    @TableId("studentNum")
    private String studentNum;

    @TableField("dormId")
    private String dormId;

    private String time;


    public static final String NAME = "name";

    public static final String STUDENTNUM = "studentNum";

    public static final String DORMID = "dormId";

    public static final String TIME = "time";

    @Override
    protected Serializable pkVal() {
        return this.studentNum;
    }

}
