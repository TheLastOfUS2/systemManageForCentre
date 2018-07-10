package com.systemManage.service.impl;

import com.systemManage.common.utils.JsonUtils;
import com.systemManage.common.utils.StringUtil;
import com.systemManage.dao.base.CaCentralActivityMapper;
import com.systemManage.dao.ext.CaCentralActivityMapperExt;
import com.systemManage.pojo.base.AmActivity;
import com.systemManage.pojo.base.CaCentralActivity;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.service.CaCentralActivityService;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaCentralActivityServiceImpl implements CaCentralActivityService {
    @Autowired
    private CaCentralActivityMapper caCentralActivityMapper;
    @Autowired
    private CaCentralActivityMapperExt caCentralActivityMapperExt;
    
    private static final Logger logger = LoggerFactory.getLogger(CaCentralActivityServiceImpl.class);
    
    @Override
    public JSONObject insertSelective(CaCentralActivity caCentralActivity, HttpServletRequest request) {
        int res = 0;
        JSONObject result = null;
        if(caCentralActivity!=null){
            String activityId=caCentralActivity.getActivityId();
            if (StringUtil.isEmpty(activityId)) {
                //设置主键
                caCentralActivity.setActivityId(StringUtil.getUUID());
                //设置新增时间
                caCentralActivity.setActivityCreateTime(new Date());
                //设置删除状态（0.未删除1.已删除）
                caCentralActivity.setActivityDel("0");
            }
            //保存基本信息
            if (StringUtil.isEmpty(activityId)) {
                res = this.caCentralActivityMapper.insertSelective(caCentralActivity);
            }else{
                res = this.caCentralActivityMapper.updateByPrimaryKeySelective(caCentralActivity);
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
    
    public int countByExample(Criteria example) {
        int count = this.caCentralActivityMapperExt.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public CaCentralActivity selectByPrimaryKey(String activityId) {
        return this.caCentralActivityMapper.selectByPrimaryKey(activityId);
    }

    public List<CaCentralActivity> selectByExample(Criteria example) {
        return this.caCentralActivityMapperExt.selectByExample(example);
    }

    public int deleteByPrimaryKey(String activityId) {
        return this.caCentralActivityMapper.deleteByPrimaryKey(activityId);
    }

    public int updateByPrimaryKeySelective(CaCentralActivity record) {
        return this.caCentralActivityMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(CaCentralActivity record,String activityDel) {
     // baseInfoDel=0:恢复;baseInfoDel=1:删除;baseInfoDel=2:彻底删除
        if(!("2").equals(activityDel)){
            //更改数据状态
            record.setActivityDel(activityDel);
            return this.caCentralActivityMapper.updateByPrimaryKey(record);
        }else{
            //从数据库中删除
            return this.caCentralActivityMapper.deleteByPrimaryKey(record.getActivityId());
        }
    }

    public int deleteByExample(Criteria example) {
        return this.caCentralActivityMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(CaCentralActivity record, Criteria example) {
        return this.caCentralActivityMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(CaCentralActivity record, Criteria example) {
        return this.caCentralActivityMapper.updateByExample(record, example);
    }

    public int insert(CaCentralActivity record) {
        return this.caCentralActivityMapper.insert(record);
    }

    public int insertSelective(CaCentralActivity record) {
        return this.caCentralActivityMapper.insertSelective(record);
    }
}