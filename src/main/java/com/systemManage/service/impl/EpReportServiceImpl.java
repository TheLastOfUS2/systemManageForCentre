package com.systemManage.service.impl;

import com.systemManage.common.utils.JsonUtils;
import com.systemManage.common.utils.StringUtil;
import com.systemManage.dao.base.EpReportMapper;
import com.systemManage.dao.ext.EpReportMapperExt;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.CsTrainConsult;
import com.systemManage.pojo.base.EpReport;
import com.systemManage.service.EpReportService;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EpReportServiceImpl implements EpReportService {
    @Autowired
    private EpReportMapper epReportMapper; //报表
    @Autowired
    private EpReportMapperExt epReportMapperExt; //报表

    private static final Logger logger = LoggerFactory.getLogger(EpReportServiceImpl.class);
    
    @Override
    public JSONObject insertSelective(EpReport epReport, HttpServletRequest request) {
        int res = 0;
        JSONObject result = null;
        if(epReport!=null){
            String reportId=epReport.getReportId();
            if (StringUtil.isEmpty(reportId)) {
                //设置主键
                epReport.setReportId(StringUtil.getUUID());
                //设置新增时间
                epReport.setReportCreateTime(new Date());
                //设置删除状态（0.未删除1.已删除）
                epReport.setReportDel("0");
            }
            //保存基本信息
            if (StringUtil.isEmpty(reportId)) {
                res = this.epReportMapper.insertSelective(epReport);
            }else{
                res = this.epReportMapper.updateByPrimaryKeySelective(epReport);
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
        int count = this.epReportMapperExt.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public EpReport selectByPrimaryKey(String reportId) {
        return this.epReportMapper.selectByPrimaryKey(reportId);
    }

    public List<EpReport> selectByExample(Criteria example) {
        return this.epReportMapperExt.selectByExample(example);
    }

    public int deleteByPrimaryKey(String reportId) {
        return this.epReportMapper.deleteByPrimaryKey(reportId);
    }

    public int updateByPrimaryKeySelective(EpReport record) {
        return this.epReportMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(EpReport record,String reportDel) {
        // reportDel=0:恢复;reportDel=1:删除;reportDel=2:彻底删除
        if(!("2").equals(reportDel)){
            //更改数据状态
            record.setReportDel(reportDel);
            return this.epReportMapper.updateByPrimaryKey(record);
        }else{
            //从数据库中删除
            return this.epReportMapper.deleteByPrimaryKey(record.getReportId());
        }
    }

    public int deleteByExample(Criteria example) {
        return this.epReportMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(EpReport record, Criteria example) {
        return this.epReportMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(EpReport record, Criteria example) {
        return this.epReportMapper.updateByExample(record, example);
    }

    public int insert(EpReport record) {
        return this.epReportMapper.insert(record);
    }

    public int insertSelective(EpReport record) {
        return this.epReportMapper.insertSelective(record);
    }
}