package com.studio314.videoservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoPostDTO {

    private Long uID;
    private String UUID;
    private String vTitle;
    private String vProfile;

}
