package com.studio314.videoservice.mapper;

import com.studio314.videoservice.domain.pojo.Like;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LikeMapper {

    @Insert("insert into `like` (uID, vID) values (#{uID}, #{vID})")
    void likeVideo(int uID, int vID);

    @Select("select * from `like` where uID = #{uID} and vID = #{vID}")
    Like getLike(int uID, int vID);

    @Delete("delete from `like` where uID = #{uID} and vID = #{vID}")
    void cancelLike(int uID, int vID);
}
