package com.studio314.videoservice.service;

import com.studio314.tiknotokcommon.utils.Result;

public interface VideoService {

    Result getMyVideo(int uID, int page, int size);

}
