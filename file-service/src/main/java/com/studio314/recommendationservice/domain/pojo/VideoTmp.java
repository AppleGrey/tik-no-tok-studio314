package com.studio314.recommendationservice.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoTmp {

    private int tID;
    private int uID;
    private String path;
    private String UUID;

}

