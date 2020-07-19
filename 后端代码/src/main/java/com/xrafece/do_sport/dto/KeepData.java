package com.xrafece.do_sport.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Xrafece
 */
@Data
@NoArgsConstructor
public class KeepData {
    @JsonFormat(pattern = "MM月dd日")
    private Date keepDay;
    @JsonFormat(pattern = "yyyy年 HH:mm")
    private Date keepTime;
}
