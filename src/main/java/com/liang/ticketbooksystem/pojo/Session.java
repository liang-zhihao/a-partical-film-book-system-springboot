package com.liang.ticketbooksystem.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author liang
 * @since 2020-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Session implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "session_id", type = IdType.AUTO)
    private Integer sessionId;

    private Integer filmId;

    private LocalDateTime time;

    private String status;

    private Integer remainingSeats;

    private Integer roomId;

    private Integer cinemaId;
    private Double price;
    @TableField(exist = false)
    private Cinema cinema;
    @TableField(exist = false)
    private  Film film;
    @TableField(exist = false)
    private Room room;

}
