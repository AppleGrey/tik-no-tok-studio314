package com.studio314.videoservice.controller;

import com.studio314.tiknotokcommon.utils.Result;
import com.studio314.videoservice.domain.dto.VideoPostDTO;
import com.studio314.videoservice.service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ssl.SslProperties;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

import java.io.File;

@RestController
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    VideoService videoService;

    /**
     * 获取视频具体信息
     */
    @GetMapping("/{vID}")
    public Result getVideo(@RequestHeader("userID") Long uID,
                           @PathVariable("vID") Long vID) {
        return videoService.getVideo(uID, vID);
    }

    /**
     * 获取我的视频
     */
    @GetMapping
    public Result getMyVideo(@RequestHeader("userID") Long uID,
                             @RequestParam(required = false) Integer offset,
                             @RequestParam(required = false) Integer size) {
        if (offset == null) {
            offset = 99999;
        }
        if (size == null) {
            size = 10;
        }
        return videoService.getMyVideo(uID, offset, size);
    }

    /**
     * 删除视频
     */
    @DeleteMapping("/{vID}")
    public Result deleteVideo(@RequestHeader("userID") Long uID,
                              @PathVariable("vID") Long vID) {
        return videoService.deleteVideo(uID, vID);
    }

    /**
     * 获取推荐列表
     */
    @GetMapping("/recommend")
    public Result getRecommend(@RequestHeader("userID") Long uID,
                               @RequestParam(required = false) Integer size) {
        if (size == null) {
            size = 10;
        }
//        Result myVideo = videoService.getMyVideo(0L, 1, 10);
        return videoService.getRecommend(uID, size);
//        return myVideo;
    }

    /**
     * 视频发布
     */
    @PostMapping
    public Result publishVideo(@RequestHeader("userID") Long uID,
                               @RequestBody VideoPostDTO videoPostDTO) {
        videoPostDTO.setUID(uID);
        return videoService.publishVideo(videoPostDTO);
    }

    @PostMapping("/test")
    public Result test(@RequestBody VideoPostDTO videoPostDTO) {
        System.out.println(videoPostDTO);
        return Result.success();
    }
}
