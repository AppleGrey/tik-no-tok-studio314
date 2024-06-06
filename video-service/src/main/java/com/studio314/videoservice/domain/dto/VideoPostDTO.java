package com.studio314.videoservice.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoPostDTO {

    @JsonProperty("uID")
    private Long uID;

    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("vTitle")
    private String vTitle;

    @JsonProperty("vProfile")
    private String vProfile;

}
