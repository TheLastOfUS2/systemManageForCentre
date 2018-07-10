package com.systemManage.dao.base;

import com.systemManage.pojo.base.AmOpus;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.SfShareFile;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SfShareFileMapper {
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
    int deleteByPrimaryKey(String shareFileId);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(SfShareFile record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(SfShareFile record);

    /**
     * 根据条件查询记录集
     */
    List<SfShareFile> selectByExample(Criteria example);

    /**
     * 根据主键查询记录
     */
    SfShareFile selectByPrimaryKey(String shareFileId);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("record") SfShareFile record, @Param("example") Criteria example);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("record") SfShareFile record, @Param("example") Criteria example);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(SfShareFile record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(SfShareFile record);
}