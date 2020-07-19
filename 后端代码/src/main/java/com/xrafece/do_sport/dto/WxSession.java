package com.xrafece.do_sport.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Xrafece
 */
@Data
@NoArgsConstructor
public class WxSession {
    private String session_key;
    private String openid;
}
