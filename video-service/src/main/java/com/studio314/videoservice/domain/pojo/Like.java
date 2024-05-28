package com.studio314.videoservice.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Like {

    private int uID;
    private int vID;
    private LocalDateTime lTime;

}
