package com.studio314.videoservice.controller;

import com.studio314.tiknotokcommon.utils.Result;
import com.studio314.videoservice.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/videos")
public class VideoController {

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


}
