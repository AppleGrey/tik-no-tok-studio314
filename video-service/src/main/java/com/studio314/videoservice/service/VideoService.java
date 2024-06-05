package com.studio314.videoservice.service;

import com.studio314.tiknotokcommon.dto.VideoMsgDTO;
import com.studio314.tiknotokcommon.utils.Result;
import com.studio314.videoservice.domain.dto.VideoPostDTO;

public interface VideoService {

    Result getMyVideo(Long uID, int page, int size);

    Result deleteVideo(Long uID, Long vID);

    Result getVideo(Long uID, Long vID);

//    Result uploadVideo(MultipartFile file, Long uid);

    Result publishVideo(VideoPostDTO videoPostDTO);

    boolean updateVideo(VideoMsgDTO videoMsg);

    Result getRecommend(Long uID, int page, int size);
}
