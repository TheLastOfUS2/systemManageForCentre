package com.systemManage.service;

import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.UploadFileInfo;
import java.util.List;

public interface UploadFileInfoService {
    int countByExample(Criteria example);

    UploadFileInfo selectByPrimaryKey(String fileId);

    List<UploadFileInfo> selectByExample(Criteria example);

    int deleteByPrimaryKey(String fileId);

    int updateByPrimaryKeySelective(UploadFileInfo record);

    int updateByPrimaryKey(UploadFileInfo record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(UploadFileInfo record, Criteria example);

    int updateByExample(UploadFileInfo record, Criteria example);

    int insert(UploadFileInfo record);

    int insertSelective(UploadFileInfo record);
}