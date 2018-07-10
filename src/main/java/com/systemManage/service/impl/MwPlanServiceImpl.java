package com.systemManage.service.impl;

import com.systemManage.common.utils.JsonUtils;
import com.systemManage.common.utils.StringUtil;
import com.systemManage.dao.base.MwPlanMapper;
import com.systemManage.dao.ext.MwPlanMapperExt;
import com.systemManage.pojo.base.AmActivity;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.MwPlan;
import com.systemManage.service.MwPlanService;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MwPlanServiceImpl implements MwPlanService {
    @Autowired
    private MwPlanMapper mwPlanMapper;
    @Autowired
    private MwPlanMapperExt mwPlanMapperExt;

    private static final Logger logger = LoggerFactory.getLogger(MwPlanServiceImpl.class);
    
    @Override
    public JSONObject insertSelective(MwPlan mwPlan, HttpServletRequest request) {
        int res = 0;
        JSONObject result = null;
        if(mwPlan!=null){
            String activityId=mwPlan.getPlanId();
            if (StringUtil.isEmpty(activityId)) {
                //设置主键
                mwPlan.setPlanId(StringUtil.getUUID());
                //设置新增时间
                mwPlan.setPlanCreateTime(new Date());
                //设置删除状态（0.未删除1.已删除）
                mwPlan.setPlanDel("0");
            }
            //保存基本信息
            if (StringUtil.isEmpty(activityId)) {
                res = this.mwPlanMapper.insertSelective(mwPlan);
            }else{
                res = this.mwPlanMapper.updateByPrimaryKeySelective(mwPlan);
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
        int count = this.mwPlanMapperExt.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public MwPlan selectByPrimaryKey(String planId) {
        return this.mwPlanMapper.selectByPrimaryKey(planId);
    }

    public List<MwPlan> selectByExample(Criteria example) {
        return this.mwPlanMapperExt.selectByExample(example);
    }

    public int deleteByPrimaryKey(String planId) {
        return this.mwPlanMapper.deleteByPrimaryKey(planId);
    }

    public int updateByPrimaryKeySelective(MwPlan record) {
        return this.mwPlanMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(MwPlan record,String planDel) {
        // baseInfoDel=0:恢复;baseInfoDel=1:删除;baseInfoDel=2:彻底删除
        if(!("2").equals(planDel)){
            //更改数据状态
            record.setPlanDel(planDel);
            return this.mwPlanMapper.updateByPrimaryKey(record);
        }else{
            //从数据库中删除
            return this.mwPlanMapper.deleteByPrimaryKey(record.getPlanId());
        }
    }

    public int deleteByExample(Criteria example) {
        return this.mwPlanMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(MwPlan record, Criteria example) {
        return this.mwPlanMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(MwPlan record, Criteria example) {
        return this.mwPlanMapper.updateByExample(record, example);
    }

    public int insert(MwPlan record) {
        return this.mwPlanMapper.insert(record);
    }

    public int insertSelective(MwPlan record) {
        return this.mwPlanMapper.insertSelective(record);
    }
}