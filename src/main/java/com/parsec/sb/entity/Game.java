package com.parsec.sb.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import tk.mybatis.mapper.annotation.KeySql;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Created by AutoGenerateCode on 2019-10-29 15:04:06.
 * @author parsec 
 */  
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Table(name = "tbl_game")
@ApiModel(value = "Game", description = "tbl_game")
public class Game {

	@Id
	@KeySql(useGeneratedKeys = true)
	@ApiModelProperty(value = "")
	private Integer id;

	@ApiModelProperty(value = "")
	private String title;

	@ApiModelProperty(value = "")
	private Integer first;

	@ApiModelProperty(value = "")
	private Integer second;

	@ApiModelProperty(value = "")
	private Integer third;

	@ApiModelProperty(value = "")
	private String label;

	@ApiModelProperty(value = "")
	private String slogan;

}