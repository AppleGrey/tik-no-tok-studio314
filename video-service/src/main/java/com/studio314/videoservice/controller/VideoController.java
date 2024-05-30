package com.studio314.videoservice.controller;

import com.studio314.tiknotokcommon.utils.Result;
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

    private static final Logger log = LoggerFactory.getLogger(VideoController.class);
    @Autowired
    VideoService videoService;

    /**
     * 获取视频具体信息
     */
    @GetMapping("/{vID}")
    public Result getVideo(@PathVariable("vID") int vID) {
        return videoService.getVideo(vID);
    }

    /**
     * 获取我的视频
     */
    @GetMapping
    public Result getMyVideo(@RequestHeader("userID") int uID,
                             @RequestBody HashMap body) {
        int page = 1;
        int size = 10;
        if (body.containsKey("page")) {
            page = (int) body.get("page");
        }
        if (body.containsKey("size")) {
            size = (int) body.get("size");
        }
        return videoService.getMyVideo(uID, page, size);
    }

    /**
     * 删除视频
     */
    @DeleteMapping("/{vID}")
    public Result deleteVideo(@RequestHeader("userID") int uID,
                              @PathVariable("vID") int vID) {
        return videoService.deleteVideo(uID, vID);
    }

    /**
     * 获取推荐列表
     */
    @GetMapping("/recommend")
    public Result getRecommend(@RequestHeader("userID") int uID) {
        return Result.success();
    }

    @PostMapping("/upload")
    public Result uploadVideo(@RequestParam("file") MultipartFile file){
        log.info("接收到文件");
        return videoService.uploadVideo(file);
    }
}
