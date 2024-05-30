package com.studio314.videoservice.service;

import com.studio314.tiknotokcommon.utils.Result;
import org.springframework.web.multipart.MultipartFile;

public interface VideoService {

    Result getMyVideo(int uID, int page, int size);

    Result deleteVideo(int uID, int vID);

    Result getVideo(int vID);

    Result uploadVideo(MultipartFile file, int uid);
}
