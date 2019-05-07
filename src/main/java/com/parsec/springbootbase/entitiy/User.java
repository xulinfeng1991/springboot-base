package com.parsec.springbootbase.entitiy;

import com.parsec.universal.utils.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * 用户实体
 *
 * @author xujiahong
 * @date 2019/2/14
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Table(name = "tbl_user")
@ApiModel("User | 用户对象")
public class User {

    @Id
    @KeySql(useGeneratedKeys = true)
    @ApiModelProperty("ApiModelProperty-用户ID")
    private Long id;

    @ApiModelProperty("ApiModelProperty-用户名称")
    private String name;

    @ApiModelProperty("ApiModelProperty-用户年龄")
    private Integer age = 18;

    @ApiModelProperty("ApiModelProperty-用户备注")
    private String remark;

    @ApiModelProperty("ApiModelProperty-用户创建时间")
    @DateTimeFormat(pattern = DateUtil.pattern_YMDHmS)
    private LocalDateTime createTime;
}
