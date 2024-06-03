package com.studio314.recommendationservice.service;

import com.studio314.tiknotokcommon.utils.Result;
import org.springframework.web.multipart.MultipartFile;

public interface VideoService {
    Result uploadVideo(MultipartFile file, Long uID);
}
