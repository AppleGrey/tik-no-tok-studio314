package com.studio314.videoservice.controller;

import com.studio314.tiknotokcommon.utils.Result;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/videos")
public class VideoController {

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
        return Result.success();
    }

}
