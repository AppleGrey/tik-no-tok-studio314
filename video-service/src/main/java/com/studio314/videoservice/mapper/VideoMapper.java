package com.studio314.videoservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.studio314.videoservice.domain.pojo.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface VideoMapper extends BaseMapper<Video> {

    @Select("select * from video where uID = #{uID} limit #{page}, #{size}")
    List<Video> getMyVideo(int uID, int page, int size);

}
