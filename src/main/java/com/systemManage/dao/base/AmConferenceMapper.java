package com.systemManage.dao.base;

import com.systemManage.pojo.base.AmConference;
import com.systemManage.pojo.base.Criteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AmConferenceMapper {
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
    int deleteByPrimaryKey(String conferenceId);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(AmConference record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(AmConference record);

    /**
     * 根据条件查询记录集
     */
    List<AmConference> selectByExample(Criteria example);

    /**
     * 根据主键查询记录
     */
    AmConference selectByPrimaryKey(String conferenceId);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("record") AmConference record, @Param("example") Criteria example);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("record") AmConference record, @Param("example") Criteria example);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(AmConference record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(AmConference record);
}