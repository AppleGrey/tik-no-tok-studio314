package com.studio314.videoservice.mapper;

import com.studio314.videoservice.domain.pojo.Like;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LikeMapper {

    @Insert("insert into `like` (uID, vID) values (#{uID}, #{vID})")
    void likeVideo(Long uID, Long vID);

    @Select("select * from `like` where uID = #{uID} and vID = #{vID}")
    Like getLike(Long uID, Long vID);

    @Delete("delete from `like` where uID = #{uID} and vID = #{vID}")
    void cancelLike(Long uID, Long vID);
}
