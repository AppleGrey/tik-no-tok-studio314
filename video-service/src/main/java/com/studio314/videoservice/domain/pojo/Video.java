package com.studio314.videoservice.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Video {

    private Long vID;
    private String vTitle;
    private String vProfile;
    private LocalDateTime cTime;
    private String coverUrl;
    private String vUrl;
    private int vLike;
    private String state;
    private Long uID;
    private boolean isDel;

}
