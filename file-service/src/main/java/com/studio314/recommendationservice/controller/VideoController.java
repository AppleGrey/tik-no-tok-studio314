package com.studio314.recommendationservice.controller;

import com.studio314.recommendationservice.service.VideoService;
import com.studio314.tiknotokcommon.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file/videos")
public class VideoController {

    @Autowired
    VideoService videoService;

    @PostMapping
    public Result uploadVideo(@RequestParam("file") MultipartFile file, @RequestHeader("userID") Long uID){
        return videoService.uploadVideo(file, uID);
    }

    @GetMapping("/check")
    public boolean checkVideo(@RequestParam("UUID") String UUID,
                              @RequestParam("uID") Long uID){
        return videoService.checkVideo(uID, UUID);
    }

}
