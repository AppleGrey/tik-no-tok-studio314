package com.studio314.videoservice.domain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("video")
public class Video {

    @TableId(type = IdType.AUTO)
    private Long vID;
    private String vTitle;
    private String vProfile;
    private LocalDateTime cTime;
    private String coverUrl;
    private String vUrl;
    private int vLike;
    private String state;
    private Long uID;

}
