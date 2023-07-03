package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface CommunityMapper {
    
   
    public int updatehit(long no);

}
