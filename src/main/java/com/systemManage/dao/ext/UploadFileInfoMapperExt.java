package com.systemManage.dao.ext;

import java.util.List;

import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.UploadFileInfo;

public interface UploadFileInfoMapperExt {
	
    /**
     * 根据条件查询记录总数
     */
    int countByCondition(Criteria example);

    /**
     * 根据条件查询记录集
     */
    List<UploadFileInfo> selectByCondition(Criteria example);
}