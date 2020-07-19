package com.xrafece.do_sport.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Xrafece
 */
@Data
@NoArgsConstructor
public class RechargeCard {
    @JsonIgnore
    private Integer id;
    @JsonProperty("name")
    private String card_name;
    @JsonProperty("price")
    private Double card_price;
    @JsonProperty("expireDay")
    private Integer card_expireDay;
    @JsonProperty("see")
    private Integer card_see;
    @JsonIgnore
    @JsonProperty("rank")
    private Integer card_rank;
}
