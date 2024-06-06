package com.studio314.tiknotokcommon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * kafka消息传输对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoMsgDTO {

    private Long vID;
    private Long uID;
    private String uuid;
    private String coverUrl;
    private String vUrl;

}
