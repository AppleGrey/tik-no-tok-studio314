package com.studio314.videoservice.service.impl;

import com.studio314.tiknotokcommon.utils.Result;
import com.studio314.videoservice.domain.dto.VideoPreDTO;
import com.studio314.videoservice.mapper.VideoMapper;
import com.studio314.videoservice.domain.pojo.Video;
import com.studio314.videoservice.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    VideoMapper videoMapper;

    @Override
    public Result getMyVideo(int uID, int page, int size) {
        List<VideoPreDTO> myVideo = videoMapper.getMyVideo(uID, page, size);
        if (myVideo != null) {
            return Result.success(myVideo);
        }
        return null;
    }

    @Override
    public Result deleteVideo(int uID, int vID) {
        Video video = videoMapper.selectById(vID);
        if (video != null && video.getUID() == uID) {
            video.setDel(true);
            videoMapper.updateDelById(video.getVID());
            return Result.success();
        }
        return Result.error("您没有执行此操作的权限");
    }

    @Override
    public Result getVideo(int vID) {
        Video video = videoMapper.selectById(vID);
        if (video != null) {
            return Result.success(video);
        }
        return Result.error("视频不存在");
    }

}
