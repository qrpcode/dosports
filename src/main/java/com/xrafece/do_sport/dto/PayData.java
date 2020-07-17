package com.xrafece.do_sport.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Xrafece
 */
@Data
@NoArgsConstructor
public class PayData {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("nickName")
    private String wx_nickName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("avatar")
    private String wx_avatar;
    @JsonProperty("payTime")
    @JsonFormat(pattern = "yyyy年MM月dd日")
    private Date pay_time;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("gender")
    private Integer wx_gender;
    @JsonProperty("payCard")
    private String card_name;
    @JsonProperty("payPrice")
    private Double pay_money;
}
