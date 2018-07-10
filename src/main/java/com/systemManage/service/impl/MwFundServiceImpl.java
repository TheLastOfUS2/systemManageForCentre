package com.systemManage.service.impl;

import com.systemManage.common.utils.JsonUtils;
import com.systemManage.common.utils.StringUtil;
import com.systemManage.dao.base.MwFundMapper;
import com.systemManage.dao.ext.MwFundMapperExt;
import com.systemManage.pojo.base.AmActivity;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.MwFund;
import com.systemManage.service.MwFundService;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MwFundServiceImpl implements MwFundService {
    @Autowired
    private MwFundMapper mwFundMapper;
    @Autowired
    private MwFundMapperExt mwFundMapperExt;

    private static final Logger logger = LoggerFactory.getLogger(MwFundServiceImpl.class);
    
    @Override
    public JSONObject insertSelective(MwFund mwFund, HttpServletRequest request) {
        int res = 0;
        JSONObject result = null;
        if(mwFund!=null){
            String fundId=mwFund.getFundId();
            if (StringUtil.isEmpty(fundId)) {
                //设置主键
                mwFund.setFundId(StringUtil.getUUID());
                //设置新增时间
                mwFund.setFundCreateTime(new Date());
                //设置删除状态（0.未删除1.已删除）
                mwFund.setFundDel("0");
            }
            //保存基本信息
            if (StringUtil.isEmpty(fundId)) {
                res = this.mwFundMapper.insertSelective(mwFund);
            }else{
                res = this.mwFundMapper.updateByPrimaryKeySelective(mwFund);
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
        int count = this.mwFundMapperExt.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public MwFund selectByPrimaryKey(String fundId) {
        return this.mwFundMapper.selectByPrimaryKey(fundId);
    }

    public List<MwFund> selectByExample(Criteria example) {
        return this.mwFundMapperExt.selectByExample(example);
    }

    public int deleteByPrimaryKey(String fundId) {
        return this.mwFundMapper.deleteByPrimaryKey(fundId);
    }

    public int updateByPrimaryKeySelective(MwFund record) {
        return this.mwFundMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(MwFund record,String fundDel) {
        // baseInfoDel=0:恢复;baseInfoDel=1:删除;baseInfoDel=2:彻底删除
        if(!("2").equals(fundDel)){
            //更改数据状态
            record.setFundDel(fundDel);
            return this.mwFundMapper.updateByPrimaryKey(record);
        }else{
            //从数据库中删除
            return this.mwFundMapper.deleteByPrimaryKey(record.getFundId());
        }
    }

    public int deleteByExample(Criteria example) {
        return this.mwFundMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(MwFund record, Criteria example) {
        return this.mwFundMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(MwFund record, Criteria example) {
        return this.mwFundMapper.updateByExample(record, example);
    }

    public int insert(MwFund record) {
        return this.mwFundMapper.insert(record);
    }

    public int insertSelective(MwFund record) {
        return this.mwFundMapper.insertSelective(record);
    }
}