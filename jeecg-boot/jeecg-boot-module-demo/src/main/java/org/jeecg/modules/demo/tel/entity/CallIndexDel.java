package org.jeecg.modules.demo.tel.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: index_del页面
 * @Author: jeecg-boot
 * @Date: 2021-03-29
 * @Version: V1.0
 */
@Data
@TableName("call_index_del")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "call_index_del对象", description = "index_del页面")
public class CallIndexDel implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createBy;
    /**
     * 创建日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private String updateBy;
    /**
     * 更新日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private Date updateTime;
    /**
     * 所属部门
     */
    @ApiModelProperty(value = "所属部门")
    private String sysOrgCode;
    /**
     * clientId
     */
    @Excel(name = "clientId", width = 255)
    @ApiModelProperty(value = "clientId")
    private String clientid;
    /**
     * userId
     */
    @Excel(name = "userId", width = 255)
    @ApiModelProperty(value = "userId")
    private String userId;
    /**
     * 姓名
     */
    @Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
    private String realname;
    /**
     * 身份证号
     */
    @Excel(name = "身份证号", width = 15)
    @ApiModelProperty(value = "身份证号")
    private String idcard;
    /**
     * 性别
     */
    @Excel(name = "性别", width = 15)
    @ApiModelProperty(value = "性别")
    private String gender;
    /**
     * 单位
     */
    @Excel(name = "单位", width = 15)
    @ApiModelProperty(value = "单位")
    private String companyname;
    /**
     * 单位
     */
    @Excel(name = "联系方式", width = 255)
    @ApiModelProperty(value = "联系方式")
    private String orimobile;
    /**
     * 单位
     */
    @Excel(name = "手机", width = 255)
    @ApiModelProperty(value = "单位")
    private String mobile;
    /**
     * sToken
     */
    @Excel(name = "token", width = 15)
    @ApiModelProperty(value = "token")
    @TableField(exist = false)
    private String token;
}
