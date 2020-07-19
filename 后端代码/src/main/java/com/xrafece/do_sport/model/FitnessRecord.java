package com.xrafece.do_sport.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Xrafece
 */
@Data
@NoArgsConstructor
public class FitnessRecord {
    @JsonIgnore
    private Integer id;
    private String wx_id;
    private Date fitness_time;
}
