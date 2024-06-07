package com.studio314.recommendationservice.mapper;

import com.studio314.recommendationservice.domain.pojo.VideoTmp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface VideoMapper {

    @Insert("insert into temp_video (uuid, uid, path) values (#{uuid}, #{uid}, #{path})")
    void addTempVideo(String uuid, Long uid, String path);

    @Select("select * from temp_video where UUID = #{uuid}")
    VideoTmp getTmpVideo(String uuid);

    @Update("update temp_video set vid = #{vid} where UUID = #{uuid}")
    void updateVID(String uuid, Long vid);

    @Select("select * from temp_video where vID = #{vID}")
    VideoTmp getTmpVideoByVID(Long vID);

    @Update("update temp_video set compress = #{compressPath} where UUID = #{uuid}")
    void updateCompress(String uuid, String compressPath);
}
