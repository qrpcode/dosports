package com.xrafece.do_sport;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author Xrafece
 */
public class ObjMapperTest {
    @Test
    void name() {
        String cardList = null;
        List list = new ObjectMapper().convertValue(cardList, List.class);
        for (Object o : list) {
            System.out.println(list);
        }
    }
}
