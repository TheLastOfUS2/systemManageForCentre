package com.systemManage.service.impl;

import com.systemManage.common.utils.JsonUtils;
import com.systemManage.common.utils.StringUtil;
import com.systemManage.dao.base.CsTrainConsultMapper;
import com.systemManage.dao.ext.CsTrainConsultMapperExt;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.CsTrainConsult;
import com.systemManage.pojo.base.PcTeaching;
import com.systemManage.service.CsTrainConsultService;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CsTrainConsultServiceImpl implements CsTrainConsultService {
    @Autowired
    private CsTrainConsultMapper csTrainConsultMapper;
    @Autowired
    private CsTrainConsultMapperExt csTrainConsultMapperExt;

    private static final Logger logger = LoggerFactory.getLogger(CsTrainConsultServiceImpl.class);
    
    @Override
    public JSONObject insertSelective(CsTrainConsult csTrainConsult, HttpServletRequest request) {
        int res = 0;
        JSONObject result = null;
        if(csTrainConsult!=null){
            String consultId=csTrainConsult.getConsultId();
            if (StringUtil.isEmpty(consultId)) {
                //设置主键
                csTrainConsult.setConsultId(StringUtil.getUUID());
                //设置新增时间
                csTrainConsult.setConsultCreateTime(new Date());
                //设置删除状态（0.未删除1.已删除）
                csTrainConsult.setConsultDel("0");
            }
            //保存基本信息
            if (StringUtil.isEmpty(consultId)) {
                res = this.csTrainConsultMapper.insertSelective(csTrainConsult);
            }else{
                res = this.csTrainConsultMapper.updateByPrimaryKeySelective(csTrainConsult);
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
        int count = this.csTrainConsultMapperExt.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public CsTrainConsult selectByPrimaryKey(String consultId) {
        return this.csTrainConsultMapper.selectByPrimaryKey(consultId);
    }

    public List<CsTrainConsult> selectByExample(Criteria example) {
        return this.csTrainConsultMapperExt.selectByExample(example);
    }

    public int deleteByPrimaryKey(String consultId) {
        return this.csTrainConsultMapper.deleteByPrimaryKey(consultId);
    }

    public int updateByPrimaryKeySelective(CsTrainConsult record) {
        return this.csTrainConsultMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(CsTrainConsult record,String consultDel) {
        // consultDel=0:恢复;consultDel=1:删除;consultDel=2:彻底删除
        if(!("2").equals(consultDel)){
            //更改数据状态
            record.setConsultDel(consultDel);
            return this.csTrainConsultMapper.updateByPrimaryKey(record);
        }else{
            //从数据库中删除
            return this.csTrainConsultMapper.deleteByPrimaryKey(record.getConsultId());
        }
    }

    public int deleteByExample(Criteria example) {
        return this.csTrainConsultMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(CsTrainConsult record, Criteria example) {
        return this.csTrainConsultMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(CsTrainConsult record, Criteria example) {
        return this.csTrainConsultMapper.updateByExample(record, example);
    }

    public int insert(CsTrainConsult record) {
        return this.csTrainConsultMapper.insert(record);
    }

    public int insertSelective(CsTrainConsult record) {
        return this.csTrainConsultMapper.insertSelective(record);
    }
}