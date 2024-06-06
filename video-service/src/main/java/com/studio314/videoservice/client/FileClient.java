package com.studio314.videoservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "file-service")
public interface FileClient {

    @GetMapping("/file/videos/check")
    boolean checkVideo(@RequestParam(value = "UUID") String UUID, @RequestParam(value = "uID") Long uID);

}
