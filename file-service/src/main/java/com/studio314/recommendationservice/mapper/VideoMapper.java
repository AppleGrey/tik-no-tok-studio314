package com.studio314.recommendationservice.mapper;

import com.studio314.recommendationservice.domain.pojo.VideoTmp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface VideoMapper {

    @Insert("insert into temp_video (uuid, uid, path) values (#{uuid}, #{uid}, #{path})")
    void addTempVideo(String uuid, Long uid, String path);

    @Select("select * from temp_video where UUID = #{uuid}")
    VideoTmp getTmpVideo(String uuid);

}
