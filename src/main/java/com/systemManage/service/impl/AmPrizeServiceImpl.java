package com.systemManage.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.systemManage.dao.base.AmCollaboratorMapper;
import com.systemManage.dao.base.AmPrizeMapper;
import com.systemManage.dao.ext.AmPrizeMapperExt;
import com.systemManage.pojo.base.AmCollaborator;
import com.systemManage.pojo.base.AmPaper;
import com.systemManage.pojo.base.AmPrize;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.dto.AmOpusDto;
import com.systemManage.pojo.dto.AmPrizeDto;
import com.systemManage.service.AmPrizeService;

@Service
public class AmPrizeServiceImpl implements AmPrizeService {
    @Autowired
    private AmPrizeMapper amPrizeMapper; // 获奖
    @Autowired
    private AmPrizeMapperExt amPrizeMapperExt; // 获奖扩展
    @Autowired
    private AmCollaboratorMapper amCollaboratorMapper; // 合作者
    
    private static final Logger logger = LoggerFactory.getLogger(AmPrizeServiceImpl.class);
    
    public AmPrizeDto selectByPrimaryKeyExt(String prizeId){
        return this.amPrizeMapperExt.selectByPrimaryKeyExt(prizeId);
    }
    
    @Override
    public JSONObject insertSelective(AmPrizeDto amPrizeDto, HttpServletRequest request) {
        int res = 0;
        JSONObject result = null;
        if(amPrizeDto!=null){
            String prizeId=amPrizeDto.getPrizeId();
            if (StringUtil.isEmpty(prizeId)) {
                //设置主键
                amPrizeDto.setPrizeId(StringUtil.getUUID());
                //设置新增时间
                amPrizeDto.setPrizeCreateTime(new Date());
                //设置删除状态（0.未删除1.已删除）
                amPrizeDto.setPrizeDel("0");
            }
            //保存基本信息
            if (StringUtil.isEmpty(prizeId)) {
                res = this.amPrizeMapper.insertSelective(amPrizeDto);
            }else{
                res = this.amPrizeMapper.updateByPrimaryKeySelective(amPrizeDto);
            }
            //获取原来的合作者id,如果原来有,本次没有,则删除
            String collaboratorIds=request.getParameter("collaboratorIds");//原数据ids
            //保存教育经历
            if(StringUtil.isNotBlank(collaboratorIds) || (amPrizeDto.getAmCollaborators()!=null && amPrizeDto.getAmCollaborators().size()>0)){
                saveCollaborators(amPrizeDto,request);
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
    
    //保存合作者
    public void saveCollaborators(AmPrizeDto amPrizeDto,HttpServletRequest request){
        //获取原来的教育经历id,如果原来有,本次没有,则删除
        String collaboratorIds=request.getParameter("collaboratorIds");//原数据ids
        //存放原id
        String [] collaboratorIdsStr=null;
        //存放该次提交id
        List<String> collaboratorIdsNewList=new ArrayList<String>();
        if(StringUtil.isNotBlank(collaboratorIds)){
            collaboratorIdsStr=collaboratorIds.split(",");
        }
        // 判断当jobIds不为空,biBaseInfoDto为空时,则删除该人员下的所有的社会兼职
        if(StringUtil.isNotBlank(collaboratorIds) && (amPrizeDto.getAmCollaborators()==null || amPrizeDto.getAmCollaborators().size()==0 )){
            //遍历 删除
            for(String str : collaboratorIdsStr) {  
                AmCollaborator amCollaborator=amCollaboratorMapper.selectByPrimaryKey(str);
                if(amCollaborator != null) {
                    amCollaboratorMapper.deleteByPrimaryKey(amCollaborator.getCollaboratorId());
                }
            } 
        }else{
            for(AmCollaborator be:amPrizeDto.getAmCollaborators()){
                //判断是新增还是更新
                if(StringUtil.isEmpty(be.getCollaboratorId())){
                    //设置主键
                    be.setCollaboratorId(StringUtil.getUUID());
                    //设置外键id
                   // be.setBaseInfoId(biBaseInfoDto.getBaseInfoId());
                    //类型
                    be.setCollaboratorType(request.getParameter("collaboratorType"));
                    be.setAchievementId(amPrizeDto.getPrizeId());
                    be.setCollaboratorCreateTime(new Date());
                    this.amCollaboratorMapper.insertSelective(be);
                }else{
                    //将本次提交的id放到list中
                    collaboratorIdsNewList.add(be.getCollaboratorId());
                    this.amCollaboratorMapper.updateByPrimaryKeySelective(be);
                }
            }
            
            //判断当只有原来有数据的时候,才会去判断
            if(collaboratorIdsStr != null && collaboratorIdsStr.length>0){
               List<String> cList= compare(collaboratorIdsStr,collaboratorIdsNewList);
               if(cList!=null && cList.size()>0){
                   //遍历 删除
                   for(String str : cList) {  
                       AmCollaborator amCollaborator=amCollaboratorMapper.selectByPrimaryKey(str);
                       if(amCollaborator != null) {
                           amCollaboratorMapper.deleteByPrimaryKey(amCollaborator.getCollaboratorId());
                       }
                   } 
               }
            }
        }
    }
    
  //比较获取不同的值
    public  List<String> compare(String[] rfid,List<String> rs){
        //将两个数组合并  
        String[] twoArray = new String[rfid.length+rs.size()];  
        System.arraycopy(rfid, 0, twoArray, 0, rfid.length);  
        System.arraycopy(rs.toArray(new String[0]), 0, twoArray, rfid.length, rs.size());  
  
        //得到相同元素  
        rs.retainAll(Arrays.asList(rfid));  
  
        List<String> cList = new ArrayList<String>();  
        cList.addAll(Arrays.asList(twoArray));  
        cList.removeAll(rs);  
        return cList;
    }  
    
    public int countByExample(Criteria example) {
        int count = this.amPrizeMapperExt.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public AmPrize selectByPrimaryKey(String prizeId) {
        return this.amPrizeMapper.selectByPrimaryKey(prizeId);
    }

    public List<AmPrizeDto> selectByExample(Criteria example) {
        return this.amPrizeMapperExt.selectByExample(example);
    }

    public int deleteByPrimaryKey(String prizeId) {
        return this.amPrizeMapper.deleteByPrimaryKey(prizeId);
    }

    public int updateByPrimaryKeySelective(AmPrize record) {
        return this.amPrizeMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(AmPrize record,String prizeDel) {
     // baseInfoDel=0:恢复;baseInfoDel=1:删除;baseInfoDel=2:彻底删除
        if(!("2").equals(prizeDel)){
            //更改数据状态
            record.setPrizeDel(prizeDel);
            return this.amPrizeMapper.updateByPrimaryKey(record);
        }else{
            //从数据库中删除
            return this.amPrizeMapper.deleteByPrimaryKey(record.getPrizeId());
        }
    }

    public int deleteByExample(Criteria example) {
        return this.amPrizeMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(AmPrize record, Criteria example) {
        return this.amPrizeMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(AmPrize record, Criteria example) {
        return this.amPrizeMapper.updateByExample(record, example);
    }

    public int insert(AmPrize record) {
        return this.amPrizeMapper.insert(record);
    }

    public int insertSelective(AmPrize record) {
        return this.amPrizeMapper.insertSelective(record);
    }
}