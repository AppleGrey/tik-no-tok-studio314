package com.studio314.recommendationservice.service;

import com.studio314.recommendationservice.domain.pojo.VideoTmp;
import com.studio314.tiknotokcommon.utils.Result;
import org.springframework.web.multipart.MultipartFile;

public interface VideoService {
    Result uploadVideo(MultipartFile file, Long uID);

    boolean checkVideo(Long uID, String uuid);

    VideoTmp getVideoUuid(Long vID);
}
