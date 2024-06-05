package com.studio314.videoservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "file-service")
public interface FileClient {

    @GetMapping("/file/videos/check")
    boolean checkVideo(String UUID, Long uID);

}
