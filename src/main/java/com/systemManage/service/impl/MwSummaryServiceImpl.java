package com.systemManage.service.impl;

import com.systemManage.common.utils.JsonUtils;
import com.systemManage.common.utils.StringUtil;
import com.systemManage.dao.base.MwSummaryMapper;
import com.systemManage.dao.ext.MwSummaryMapperExt;
import com.systemManage.pojo.base.AmActivity;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.MwSummary;
import com.systemManage.service.MwSummaryService;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MwSummaryServiceImpl implements MwSummaryService {
    @Autowired
    private MwSummaryMapper mwSummaryMapper;
    @Autowired
    private MwSummaryMapperExt mwSummaryMapperExt;

    private static final Logger logger = LoggerFactory.getLogger(MwSummaryServiceImpl.class);
    
    @Override
    public JSONObject insertSelective(MwSummary mwSummary, HttpServletRequest request) {
        int res = 0;
        JSONObject result = null;
        if(mwSummary!=null){
            String activityId=mwSummary.getSummaryId();
            if (StringUtil.isEmpty(activityId)) {
                //设置主键
                mwSummary.setSummaryId(StringUtil.getUUID());
                //设置新增时间
                mwSummary.setSummaryCreateTime(new Date());
                //设置删除状态（0.未删除1.已删除）
                mwSummary.setSummaryDel("0");
            }
            //保存基本信息
            if (StringUtil.isEmpty(activityId)) {
                res = this.mwSummaryMapper.insertSelective(mwSummary);
            }else{
                res = this.mwSummaryMapper.updateByPrimaryKeySelective(mwSummary);
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
        int count = this.mwSummaryMapperExt.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public MwSummary selectByPrimaryKey(String summaryId) {
        return this.mwSummaryMapper.selectByPrimaryKey(summaryId);
    }

    public List<MwSummary> selectByExample(Criteria example) {
        return this.mwSummaryMapperExt.selectByExample(example);
    }

    public int deleteByPrimaryKey(String summaryId) {
        return this.mwSummaryMapper.deleteByPrimaryKey(summaryId);
    }

    public int updateByPrimaryKeySelective(MwSummary record) {
        return this.mwSummaryMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(MwSummary record,String summaryDel) {
     // baseInfoDel=0:恢复;baseInfoDel=1:删除;baseInfoDel=2:彻底删除
        if(!("2").equals(summaryDel)){
            //更改数据状态
            record.setSummaryDel(summaryDel);
            return this.mwSummaryMapper.updateByPrimaryKey(record);
        }else{
            //从数据库中删除
            return this.mwSummaryMapper.deleteByPrimaryKey(record.getSummaryId());
        }
    }

    public int deleteByExample(Criteria example) {
        return this.mwSummaryMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(MwSummary record, Criteria example) {
        return this.mwSummaryMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(MwSummary record, Criteria example) {
        return this.mwSummaryMapper.updateByExample(record, example);
    }

    public int insert(MwSummary record) {
        return this.mwSummaryMapper.insert(record);
    }

    public int insertSelective(MwSummary record) {
        return this.mwSummaryMapper.insertSelective(record);
    }
}