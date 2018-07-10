package com.systemManage.dao.base;

import com.systemManage.pojo.base.AmLecture;
import com.systemManage.pojo.base.Criteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AmLectureMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Criteria example);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(Criteria example);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String lectureId);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(AmLecture record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(AmLecture record);

    /**
     * 根据条件查询记录集
     */
    List<AmLecture> selectByExample(Criteria example);

    /**
     * 根据主键查询记录
     */
    AmLecture selectByPrimaryKey(String lectureId);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("record") AmLecture record, @Param("example") Criteria example);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("record") AmLecture record, @Param("example") Criteria example);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(AmLecture record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(AmLecture record);
}