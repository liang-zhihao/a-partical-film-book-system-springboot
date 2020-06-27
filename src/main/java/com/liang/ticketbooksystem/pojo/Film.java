package com.liang.ticketbooksystem.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author liang
 * @since 2020-04-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Film implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "film_id", type = IdType.AUTO)
    private Integer filmId;

    private Integer typeId;

    private String name;

    private String director;

    private String introduction;

    private String poster;

    private String status;

    private String actors;

    private Double rating;

    private Integer duration;
    @TableField(exist = false)
    private List<Session> sessionList;
    @TableField(exist = false)
    private String type;

}
