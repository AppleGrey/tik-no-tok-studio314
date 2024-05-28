package com.studio314.videoservice.service.impl;

import com.studio314.tiknotokcommon.utils.Result;
import com.studio314.videoservice.mapper.LikeMapper;
import com.studio314.videoservice.mapper.VideoMapper;
import com.studio314.videoservice.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    LikeMapper likeMapper;

    @Autowired
    VideoMapper videoMapper;

    @Override
    public Result likeVideo(int uID, int vID) {
        if(likeMapper.getLike(uID, vID) != null){
            return Result.error("您已经点过赞了");
        }
        likeMapper.likeVideo(uID, vID);
        videoMapper.likeVideo(vID);
        return Result.success();
    }

    @Override
    public Result cancelLike(int uID, int vID) {
        likeMapper.cancelLike(uID, vID);
        videoMapper.cancelLike(vID);
        return Result.success();
    }

    @Override
    public Result getLike(int uID, int vID) {
        if(likeMapper.getLike(uID, vID) != null){
            return Result.success(new HashMap<>(){{
                put("isLike", true);
            }});
        }
        return Result.success(new HashMap<>(){{
            put("isLike", false);
        }});
    }
}
