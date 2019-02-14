package com.parsec.springbootbase.entitiy;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.*;
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
@NameStyle(Style.camelhumpAndUppercase)
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    private Long id;

    private String name;

    private Integer age;

    private LocalDateTime createTime;

    /**
     * 这是一个冗余字段
     */
    @Transient
    private String remark;
}
