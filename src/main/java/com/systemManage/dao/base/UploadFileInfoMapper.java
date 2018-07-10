package com.systemManage.dao.base;

import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.UploadFileInfo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UploadFileInfoMapper {
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
    int deleteByPrimaryKey(String fileId);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(UploadFileInfo record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(UploadFileInfo record);

    /**
     * 根据条件查询记录集
     */
    List<UploadFileInfo> selectByExample(Criteria example);

    /**
     * 根据主键查询记录
     */
    UploadFileInfo selectByPrimaryKey(String fileId);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("record") UploadFileInfo record, @Param("example") Criteria example);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("record") UploadFileInfo record, @Param("example") Criteria example);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(UploadFileInfo record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(UploadFileInfo record);
}