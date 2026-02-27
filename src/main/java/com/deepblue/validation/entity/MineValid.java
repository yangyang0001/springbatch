package com.deepblue.validation.entity;

import com.deepblue.validation.ContentValid;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MineValid {

    @ContentValid(max = 10)
    private String content;

}
