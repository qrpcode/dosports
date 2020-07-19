package com.xrafece.do_sport.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Xrafece
 */
@Data
@NoArgsConstructor
public class Admin {
    private Integer id;
    private String username;
    @JsonIgnore
    private String password;
    private String level;
}
