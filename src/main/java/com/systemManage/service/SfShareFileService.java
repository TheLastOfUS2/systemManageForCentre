package com.systemManage.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.systemManage.pojo.base.AmOpus;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.SfShareFile;
import com.systemManage.pojo.dto.AmOpusDto;

public interface SfShareFileService {
    
	  int countByExample(Criteria example);
	  
	  List<SfShareFile> selectByExample(Criteria example);
	  
	  SfShareFile selectByPrimaryKey(String shareFileId);
	  
	 /**
     * 保存不为空的数据
     * @param request
     * @return
     */
    JSONObject insertSelective(SfShareFile sfShareFile, HttpServletRequest request);
    
    int updateByPrimaryKey(SfShareFile record,String shareFileDel);
    
    int deleteByPrimaryKey(String shareFileId);
}