package com.studio314.videoservice.service;

import com.studio314.tiknotokcommon.utils.Result;

public interface LikeService {

    Result likeVideo(int uID, int vID);

    Result cancelLike(int uID, int vID);

    Result getLike(int uID, int vID);
}
