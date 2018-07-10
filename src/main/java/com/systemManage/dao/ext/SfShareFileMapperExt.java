package com.systemManage.dao.ext;

import java.util.List;

import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.SfShareFile;

public interface SfShareFileMapperExt {
	/**
     * 根据条件查询记录总数
     */
    int countByExample(Criteria example);

    /**
     * 根据条件查询记录集
     */
    List<SfShareFile> selectByExample(Criteria example);
}