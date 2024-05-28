package com.studio314.videoservice.controller;

import com.studio314.tiknotokcommon.utils.Result;
import com.studio314.videoservice.service.LikeService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/videos/{vID}/like")
public class LikeController {

    @Autowired
    LikeService likeService;

    /**
     * 点赞视频
     */
    @PostMapping
    public Result likeVideo(@RequestHeader("userID") int uID,
                            @PathParam("vID") int vID){
        return likeService.likeVideo(uID, vID);
    }

    /**
     * 取消点赞
     */
    @DeleteMapping
    public Result cancelLike(@RequestHeader("userID") int uID,
                             @PathParam("vID") int vID){
        return likeService.cancelLike(uID, vID);
    }

    /**
     * 查看我是否点赞
     */
    @GetMapping
    public Result getLike(@RequestHeader("userID") int uID,
                          @PathParam("vID") int vID){
        return likeService.getLike(uID, vID);
    }
}
