package com.systemManage.service.impl;

import com.systemManage.common.utils.JsonUtils;
import com.systemManage.common.utils.StringUtil;
import com.systemManage.dao.base.MwRuleMapper;
import com.systemManage.dao.ext.MwRuleMapperExt;
import com.systemManage.pojo.base.AmActivity;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.MwRule;
import com.systemManage.service.MwRuleService;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MwRuleServiceImpl implements MwRuleService {
    @Autowired
    private MwRuleMapper mwRuleMapper;
    @Autowired
    private MwRuleMapperExt mwRuleMapperExt;

    private static final Logger logger = LoggerFactory.getLogger(MwRuleServiceImpl.class);
    
    @Override
    public JSONObject insertSelective(MwRule mwRule, HttpServletRequest request) {
        int res = 0;
        JSONObject result = null;
        if(mwRule!=null){
            String activityId=mwRule.getRuleId();
            if (StringUtil.isEmpty(activityId)) {
                //设置主键
                mwRule.setRuleId(StringUtil.getUUID());
                //设置新增时间
                mwRule.setRuleCreateTime(new Date());
                //设置删除状态（0.未删除1.已删除）
                mwRule.setRuleDel("0");
            }
            //保存基本信息
            if (StringUtil.isEmpty(activityId)) {
                res = this.mwRuleMapper.insertSelective(mwRule);
            }else{
                res = this.mwRuleMapper.updateByPrimaryKeySelective(mwRule);
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
        int count = this.mwRuleMapperExt.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public MwRule selectByPrimaryKey(String ruleId) {
        return this.mwRuleMapper.selectByPrimaryKey(ruleId);
    }

    public List<MwRule> selectByExample(Criteria example) {
        return this.mwRuleMapperExt.selectByExample(example);
    }

    public int deleteByPrimaryKey(String ruleId) {
        return this.mwRuleMapper.deleteByPrimaryKey(ruleId);
    }

    public int updateByPrimaryKeySelective(MwRule record) {
        return this.mwRuleMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(MwRule record,String ruleDel) {
     // baseInfoDel=0:恢复;baseInfoDel=1:删除;baseInfoDel=2:彻底删除
        if(!("2").equals(ruleDel)){
            //更改数据状态
            record.setRuleDel(ruleDel);
            return this.mwRuleMapper.updateByPrimaryKey(record);
        }else{
            //从数据库中删除
            return this.mwRuleMapper.deleteByPrimaryKey(record.getRuleId());
        }
    }

    public int deleteByExample(Criteria example) {
        return this.mwRuleMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(MwRule record, Criteria example) {
        return this.mwRuleMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(MwRule record, Criteria example) {
        return this.mwRuleMapper.updateByExample(record, example);
    }

    public int insert(MwRule record) {
        return this.mwRuleMapper.insert(record);
    }

    public int insertSelective(MwRule record) {
        return this.mwRuleMapper.insertSelective(record);
    }
}