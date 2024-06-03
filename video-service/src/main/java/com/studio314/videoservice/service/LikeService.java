package com.studio314.videoservice.service;

import com.studio314.tiknotokcommon.utils.Result;

public interface LikeService {

    Result likeVideo(Long uID, Long vID);

    Result cancelLike(Long uID, Long vID);

    Result getLike(Long uID, Long vID);
}
