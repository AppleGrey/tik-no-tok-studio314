package com.studio314.videoservice.utils;

import java.io.File;

/**
 * @author 钱波
 * @ClassName Constant
 * @description: TODO
 * @date 2024年05月30日
 * @version: 1.0
 */
public enum Constant {
    UPLOAD_DIR(System.getProperty("user.dir") + File.separator + "uploads");

    private final String value;

    Constant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

