package com.systemManage.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemManage.common.utils.JsonUtils;
import com.systemManage.common.utils.StringUtil;
import com.systemManage.dao.base.SfShareFileMapper;
import com.systemManage.dao.ext.SfShareFileMapperExt;
import com.systemManage.pojo.base.AmOpus;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.SfShareFile;
import com.systemManage.pojo.dto.AmOpusDto;
import com.systemManage.service.SfShareFileService;

@Service
public class SfShareFileServiceImpl implements SfShareFileService {
    @Autowired
    private SfShareFileMapper sfShareFileMapper; // 共享文件
    @Autowired
    private SfShareFileMapperExt sfShareFileMapperExt; // 共享文件
    
    private static final Logger logger = LoggerFactory.getLogger(SfShareFileServiceImpl.class);
   
    public int countByExample(Criteria example) {
        int count = this.sfShareFileMapperExt.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }
    
    public List<SfShareFile> selectByExample(Criteria example) {
        return this.sfShareFileMapperExt.selectByExample(example);
    }
    
    public SfShareFile selectByPrimaryKey(String shareFileId) {
        return this.sfShareFileMapper.selectByPrimaryKey(shareFileId);
    }
    
    @Override
    public JSONObject insertSelective(SfShareFile sfShareFile, HttpServletRequest request) {
        int res = 0;
        JSONObject result = null;
        if(sfShareFile!=null){
            String shareFileId=sfShareFile.getShareFileId();
            if (StringUtil.isEmpty(shareFileId)) {
                //设置主键
            	sfShareFile.setShareFileId(StringUtil.getUUID());
                //设置新增时间
            	sfShareFile.setShareFileCreateTime(new Date());
                //设置删除状态（0.未删除1.已删除）
            	sfShareFile.setShareFileDel("0");
            }
            //保存基本信息
            if (StringUtil.isEmpty(shareFileId)) {
                res = this.sfShareFileMapper.insertSelective(sfShareFile);
            }else{
                res = this.sfShareFileMapper.updateByPrimaryKeySelective(sfShareFile);
            }
        }
        if(res > 0){
            result = JsonUtils.setSuccess();    
            result.put("text", "操作成功");
        }else{
            result = JsonUtils.setError();
            result.put("text", "操作失败");
        }
        return result;
    }
    
    public int updateByPrimaryKey(SfShareFile record,String shareFileDel) {
        // baseInfoDel=0:恢复;baseInfoDel=1:删除;baseInfoDel=2:彻底删除
       if(!("2").equals(shareFileDel)){
           //更改数据状态
           record.setShareFileDel(shareFileDel);
           return this.sfShareFileMapper.updateByPrimaryKey(record);
       }else{
           //从数据库中删除
           return this.deleteByPrimaryKey(record.getShareFileId());
       }
    }
    
    public int deleteByPrimaryKey(String shareFileId) {
        return this.sfShareFileMapper.deleteByPrimaryKey(shareFileId);
    }
}