package com.nan.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author nan
 * @since 2019-12-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Repair对象", description="")
public class Repair extends Model<Repair> {

    private static final long serialVersionUID = 1L;

    @TableField("dormId")
    private String dormId;

    private String sta;

    private String descr;

    private String mobile;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


    public static final String DORMID = "dormId";

    public static final String STA = "sta";

    public static final String DESCR = "descr";

    public static final String MOBILE = "mobile";

    public static final String ID = "id";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
