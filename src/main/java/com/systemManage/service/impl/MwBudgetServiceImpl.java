package com.systemManage.service.impl;

import com.systemManage.common.utils.JsonUtils;
import com.systemManage.common.utils.StringUtil;
import com.systemManage.dao.base.MwBudgetMapper;
import com.systemManage.dao.ext.MwBudgetMapperExt;
import com.systemManage.pojo.base.AmActivity;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.MwBudget;
import com.systemManage.service.MwBudgetService;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MwBudgetServiceImpl implements MwBudgetService {
    @Autowired
    private MwBudgetMapper mwBudgetMapper;
    @Autowired
    private MwBudgetMapperExt mwBudgetMapperExt;

    private static final Logger logger = LoggerFactory.getLogger(MwBudgetServiceImpl.class);
    
    @Override
    public JSONObject insertSelective(MwBudget mwBudget, HttpServletRequest request) {
        int res = 0;
        JSONObject result = null;
        if(mwBudget!=null){
            String activityId=mwBudget.getBudgetId();
            if (StringUtil.isEmpty(activityId)) {
                //设置主键
                mwBudget.setBudgetId(StringUtil.getUUID());
                //设置新增时间
                mwBudget.setBudgetCreateTime(new Date());
                //设置删除状态（0.未删除1.已删除）
                mwBudget.setBudgetDel("0");
            }
            //保存基本信息
            if (StringUtil.isEmpty(activityId)) {
                res = this.mwBudgetMapper.insertSelective(mwBudget);
            }else{
                res = this.mwBudgetMapper.updateByPrimaryKeySelective(mwBudget);
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
        int count = this.mwBudgetMapperExt.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public MwBudget selectByPrimaryKey(String budgetId) {
        return this.mwBudgetMapper.selectByPrimaryKey(budgetId);
    }

    public List<MwBudget> selectByExample(Criteria example) {
        return this.mwBudgetMapperExt.selectByExample(example);
    }

    public int deleteByPrimaryKey(String budgetId) {
        return this.mwBudgetMapper.deleteByPrimaryKey(budgetId);
    }

    public int updateByPrimaryKeySelective(MwBudget record) {
        return this.mwBudgetMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(MwBudget record,String budgetDel) {
     // budgetDel=0:恢复;budgetDel=1:删除;budgetDel=2:彻底删除
        if(!("2").equals(budgetDel)){
            //更改数据状态
            record.setBudgetDel(budgetDel);
            return this.mwBudgetMapper.updateByPrimaryKey(record);
        }else{
            //从数据库中删除
            return this.mwBudgetMapper.deleteByPrimaryKey(record.getBudgetId());
        }
    }

    public int deleteByExample(Criteria example) {
        return this.mwBudgetMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(MwBudget record, Criteria example) {
        return this.mwBudgetMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(MwBudget record, Criteria example) {
        return this.mwBudgetMapper.updateByExample(record, example);
    }

    public int insert(MwBudget record) {
        return this.mwBudgetMapper.insert(record);
    }

    public int insertSelective(MwBudget record) {
        return this.mwBudgetMapper.insertSelective(record);
    }
}