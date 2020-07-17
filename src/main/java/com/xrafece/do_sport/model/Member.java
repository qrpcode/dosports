package com.xrafece.do_sport.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Member {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;
    @JsonIgnore
    private String wx_id;
    @JsonProperty("avatar")
    private String wx_avatar;
    @JsonProperty("nickName")
    private String wx_nickName;
    @JsonProperty("gender")
    private Integer wx_gender;
    @JsonProperty("expireTime")
    @JsonFormat(pattern = "yyyy年MM月dd日")
    private Date card_expireTime;
}
