package com.studio314.videoservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoPreDTO {

    private int vID;
    private String vTitle;
    private String coverUrl;
    private String state;

}
