package com.liang.ticketbooksystem.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2020-04-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Type implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "type_id", type = IdType.AUTO)
    private Integer typeId;

    private String type;


}
