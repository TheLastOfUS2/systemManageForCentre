package com.systemManage.service.impl;

import com.systemManage.common.utils.ExcelUtil;
import com.systemManage.common.utils.JsonUtils;
import com.systemManage.common.utils.StringUtil;
import com.systemManage.dao.base.BiAbroadMapper;
import com.systemManage.dao.base.BiBaseInfoMapper;
import com.systemManage.dao.base.BiEducationalMapper;
import com.systemManage.dao.base.BiJobMapper;
import com.systemManage.dao.base.BiWorkMapper;
import com.systemManage.dao.base.CommonAccountMapper;
import com.systemManage.dao.ext.BiBaseInfoMapperExt;
import com.systemManage.dao.ext.CommonAccountMapperExt;
import com.systemManage.pojo.base.BiAbroad;
import com.systemManage.pojo.base.BiBaseInfo;
import com.systemManage.pojo.base.BiEducational;
import com.systemManage.pojo.base.BiJob;
import com.systemManage.pojo.base.BiWork;
import com.systemManage.pojo.base.CommonAccount;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.dto.BiBaseInfoDto;
import com.systemManage.pojo.dto.CommonAccountDto;
import com.systemManage.service.BiBaseInfoService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BiBaseInfoServiceImpl implements BiBaseInfoService {
    
    //基本信息
    @Autowired
    private BiBaseInfoMapper biBaseInfoMapper;
    //教育经历
    @Autowired
    private BiEducationalMapper biEducationalMapper;
    //工作经历
    @Autowired
    private BiWorkMapper biWorkMapper;
    //社会兼职
    @Autowired
    private BiJobMapper biJobMapper;
    //出国经历
    @Autowired
    private BiAbroadMapper biAbroadMapper;
    
    //基本信息扩展
    @Autowired
    private BiBaseInfoMapperExt biBaseInfoMapperExt;
    // 账号信息扩展
    @Autowired
    private CommonAccountMapperExt commonAccountMapperExt;
    
    //账号信息
    @Autowired
    private CommonAccountMapper commonAccountMapper;

    private static final Logger logger = LoggerFactory.getLogger(BiBaseInfoServiceImpl.class);
    
    public List<BiBaseInfo> selectBaseInfoName(Criteria example) {
        return this.biBaseInfoMapperExt.selectBaseInfoName(example);
    }
    
    @Override
    public JSONObject insertSelective(BiBaseInfoDto biBaseInfoDto, HttpServletRequest request) {
        int res = 0;
        JSONObject result = null;
        if(biBaseInfoDto!=null){
            String baseInfoId=biBaseInfoDto.getBaseInfoId();
            if (StringUtil.isEmpty(baseInfoId)) {
                //设置主键
                biBaseInfoDto.setBaseInfoId(StringUtil.getUUID());
                //设置新增时间
                biBaseInfoDto.setBaseCreateTime(new Date());
                //设置删除状态（0.未删除1.已删除）
                biBaseInfoDto.setBaseInfoDel("0");
                String accountId=request.getParameter("accountId");
                if(StringUtil.isNotBlank(accountId)){
                	// 账号id
                	biBaseInfoDto.setAccountId(accountId);
                	// 根据账号id,将该账号的状态该给已使用
                	CommonAccount ca= commonAccountMapper.selectByPrimaryKey(accountId);
                	if(ca!=null){
                		ca.setAccountStatus("1");
                		commonAccountMapper.updateByPrimaryKeySelective(ca);
                	}
                }
            }
            //保存基本信息
            if (StringUtil.isEmpty(baseInfoId)) {
                res = this.biBaseInfoMapper.insertSelective(biBaseInfoDto);
            }else{
                res = this.biBaseInfoMapper.updateByPrimaryKeySelective(biBaseInfoDto);
            }
          
            //获取原来的教育经历id,如果原来有,本次没有,则删除
            String educationalIds=request.getParameter("educationalIds");//原数据ids
            //保存教育经历
            if(StringUtil.isNotBlank(educationalIds) || (biBaseInfoDto.getBiEducationals()!=null && biBaseInfoDto.getBiEducationals().size()>0)){
                saveBiEducationals(biBaseInfoDto,request);
            }
            
            String workIds=request.getParameter("workIds");//原数据ids
            //保存工作经历
            if(StringUtil.isNotBlank(workIds) || (biBaseInfoDto.getBiWorks()!=null && biBaseInfoDto.getBiWorks().size()>0)){
                saveBiWorks(biBaseInfoDto,request);
            }
            
            //获取原来的社会兼职id,如果原来有,本次没有,则删除
            String jobIds=request.getParameter("jobIds");//原数据ids
            //保存社会兼职
            if(StringUtil.isNotBlank(jobIds) || (biBaseInfoDto.getBiJobs()!=null && biBaseInfoDto.getBiJobs().size()>0)){
                saveBiJobs(biBaseInfoDto,request);
            }
            
            String abroadIds=request.getParameter("abroadIds");//原数据ids
            //保存出国经历
            if(StringUtil.isNotBlank(abroadIds) || (biBaseInfoDto.getBiAbroads()!=null && biBaseInfoDto.getBiAbroads().size()>0)){
                saveBiAbroads(biBaseInfoDto,request);
            }
        }
        if(res > 0){
        	// 从session 中获取角色 ，当用户角色为领导、行政、科研人员或其他人员时，将保存或编辑的信息放到session中
        	   HttpSession session = request.getSession();
        	   CommonAccountDto account = (CommonAccountDto) session.getAttribute("accountInfo");
        	   if(StringUtil.isNotBlank(account.getRoleName()) && !("0").contains(account.getRoleName())){
        			// 更改session中的账号密码
                   CommonAccountDto accountInfo = commonAccountMapperExt.selectCommonAcccount(account.getAccountId());
                   if(accountInfo!=null) {
                       // 向Session中存储用户信息
                       session.setAttribute("accountInfo", accountInfo);
                   }
        	   }
            result = JsonUtils.setSuccess();  
            result.put("baseInfoId", biBaseInfoDto.getBaseInfoId());
            result.put("text", "操作成功");
        }else{
            result = JsonUtils.setError();
            result.put("text", "操作失败");
        }
        return result;
    }
    
    //保存教育经历
    public void saveBiEducationals(BiBaseInfoDto biBaseInfoDto,HttpServletRequest request){
        //获取原来的教育经历id,如果原来有,本次没有,则删除
        String educationalIds=request.getParameter("educationalIds");//原数据ids
        //存放原id
        String [] educationalIdsStr=null;
        //存放该次提交id
        List<String> educationalIdsNewList=new ArrayList<String>();
        if(StringUtil.isNotBlank(educationalIds)){
            educationalIdsStr=educationalIds.split(",");
        }
        // 判断当jobIds不为空,biBaseInfoDto为空时,则删除该人员下的所有的社会兼职
        if(StringUtil.isNotBlank(educationalIds) && (biBaseInfoDto.getBiEducationals()==null || biBaseInfoDto.getBiEducationals().size()==0 )){
            //遍历 删除
            for(String str : educationalIdsStr) {  
                BiEducational biEducational=biEducationalMapper.selectByPrimaryKey(str);
                if(biEducational != null) {
                    biEducationalMapper.deleteByPrimaryKey(biEducational.getEducationalId());
                }
            } 
        }else{
            for(BiEducational be:biBaseInfoDto.getBiEducationals()){
                //判断是新增还是更新
                if(StringUtil.isEmpty(be.getEducationalId())){
                    //设置主键
                    be.setEducationalId(StringUtil.getUUID());
                    //设置外键id
                    be.setBaseInfoId(biBaseInfoDto.getBaseInfoId());
                   //设置新增时间
                    be.setEducationalCreateTime(new Date());
                    this.biEducationalMapper.insertSelective(be);
                }else{
                    //将本次提交的id放到list中
                    educationalIdsNewList.add(be.getEducationalId());
                    this.biEducationalMapper.updateByPrimaryKeySelective(be);
                }
            }
            
            //判断当只有原来有数据的时候,才会去判断
            if(educationalIdsStr != null && educationalIdsStr.length>0){
               List<String> cList= compare(educationalIdsStr,educationalIdsNewList);
               if(cList!=null && cList.size()>0){
                   //遍历 删除
                   for(String str : cList) {  
                       BiEducational biEducational=biEducationalMapper.selectByPrimaryKey(str);
                       if(biEducational != null) {
                           biEducationalMapper.deleteByPrimaryKey(biEducational.getEducationalId());
                       }
                   } 
               }
            }
        }
    }
    
    //保存工作经历
    public void saveBiWorks(BiBaseInfoDto biBaseInfoDto,HttpServletRequest request){
        //获取原来的教育经历id,如果原来有,本次没有,则删除
        String workIds=request.getParameter("workIds");//原数据ids
        //存放原id
        String [] workIdsStr=null;
        //存放该次提交id
        List<String> workIdsNewList=new ArrayList<String>();
        if(StringUtil.isNotBlank(workIds)){
            workIdsStr=workIds.split(",");
        }
        
     // 判断当jobIds不为空,biBaseInfoDto为空时,则删除该人员下的所有的社会兼职
        if(StringUtil.isNotBlank(workIds) && (biBaseInfoDto.getBiWorks()==null || biBaseInfoDto.getBiWorks().size()==0 )){
            //遍历 删除
            for(String str : workIdsStr) {  
                BiWork biWork=biWorkMapper.selectByPrimaryKey(str);
                if(biWork != null) {
                    biJobMapper.deleteByPrimaryKey(biWork.getWorkId());
                }
            } 
        }else{
            for(BiWork be:biBaseInfoDto.getBiWorks()){
                //判断是新增还是更新
                if(StringUtil.isEmpty(be.getWorkId())){
                    //设置主键
                    be.setWorkId(StringUtil.getUUID());
                    //设置外键id
                    be.setBaseInfoId(biBaseInfoDto.getBaseInfoId());
                   //设置新增时间
                    be.setWorkCreateTime(new Date());
                    this.biWorkMapper.insertSelective(be);
                }else{
                    //将本次提交的id放到list中
                    workIdsNewList.add(be.getWorkId());
                    this.biWorkMapper.updateByPrimaryKeySelective(be);
                }
            }
            
            //判断当只有原来有数据的时候,才会去判断
            if(workIdsStr != null && workIdsStr.length>0){
               List<String> cList= compare(workIdsStr,workIdsNewList);
               if(cList!=null && cList.size()>0){
                   //遍历 删除
                   for(String str : cList) {  
                       BiWork biWork=biWorkMapper.selectByPrimaryKey(str);
                       if(biWork != null) {
                           biWorkMapper.deleteByPrimaryKey(biWork.getWorkId());
                       }
                   } 
               }
            }
        }
    }
    
    //保存社会兼职
    public void saveBiJobs(BiBaseInfoDto biBaseInfoDto,HttpServletRequest request){
        //获取原来的社会兼职id,如果原来有,本次没有,则删除
        String jobIds=request.getParameter("jobIds");//原数据ids
        //存放原id
        String [] jobIdsStr=null;
        //存放该次提交id
        List<String> jobIdsNewList=new ArrayList<String>();
        if(StringUtil.isNotBlank(jobIds)){
            jobIdsStr=jobIds.split(",");
        }
        
        // 判断当jobIds不为空,biBaseInfoDto为空时,则删除该人员下的所有的社会兼职
        if(StringUtil.isNotBlank(jobIds) && (biBaseInfoDto.getBiJobs()==null || biBaseInfoDto.getBiJobs().size()==0 )){
            //遍历 删除
            for(String str : jobIdsStr) {  
                BiJob biJob=biJobMapper.selectByPrimaryKey(str);
                if(biJob != null) {
                    biJobMapper.deleteByPrimaryKey(biJob.getJobId());
                }
            } 
        }else{
            for(BiJob be:biBaseInfoDto.getBiJobs()){
                //判断是新增还是更新
                if(StringUtil.isEmpty(be.getJobId())){
                    //设置主键
                    be.setJobId(StringUtil.getUUID());
                    //设置外键id
                    be.setbaseInfoId(biBaseInfoDto.getBaseInfoId());
                   //设置新增时间
                    be.setJobCreateTime(new Date());
                    this.biJobMapper.insertSelective(be);
                }else{
                    //将本次提交的id放到list中
                    jobIdsNewList.add(be.getJobId());
                    this.biJobMapper.updateByPrimaryKeySelective(be);
                }
            }
            
            //判断当只有原来有数据的时候,才会去判断
            if(jobIdsStr != null && jobIdsStr.length>0){
               List<String> cList= compare(jobIdsStr,jobIdsNewList);
               if(cList!=null && cList.size()>0){
                   //遍历 删除
                   for(String str : cList) {  
                       BiJob biJob=biJobMapper.selectByPrimaryKey(str);
                       if(biJob != null) {
                           biJobMapper.deleteByPrimaryKey(biJob.getJobId());
                       }
                   } 
               }
            }
        }
    }
    
    //保存出国经历
    public void saveBiAbroads(BiBaseInfoDto biBaseInfoDto,HttpServletRequest request){
        //获取原来的出国经历id,如果原来有,本次没有,则删除
        String abroadIds=request.getParameter("abroadIds");//原数据ids
        //存放原id
        String [] abroadIdsStr=null;
        //存放该次提交id
        List<String> abroadIdsNewList=new ArrayList<String>();
        if(StringUtil.isNotBlank(abroadIds)){
            abroadIdsStr=abroadIds.split(",");
        }
        
        // 判断当jobIds不为空,biBaseInfoDto为空时,则删除该人员下的所有的社会兼职
        if(StringUtil.isNotBlank(abroadIds) && (biBaseInfoDto.getBiAbroads()==null || biBaseInfoDto.getBiAbroads().size()==0 )){
            //遍历 删除
            for(String str : abroadIdsStr) {  
                BiAbroad biAbroad=biAbroadMapper.selectByPrimaryKey(str);
                if(biAbroad != null) {
                    biJobMapper.deleteByPrimaryKey(biAbroad.getAbroadId());
                }
            } 
        }else{
            for(BiAbroad be:biBaseInfoDto.getBiAbroads()){
                //判断是新增还是更新
                if(StringUtil.isEmpty(be.getAbroadId())){
                    //设置主键
                    be.setAbroadId(StringUtil.getUUID());
                    //设置外键id
                    be.setBaseInfoId(biBaseInfoDto.getBaseInfoId());
                   //设置新增时间
                    be.setAbroadCreateTime(new Date());
                    this.biAbroadMapper.insertSelective(be);
                }else{
                    //将本次提交的id放到list中
                    abroadIdsNewList.add(be.getAbroadId());
                    this.biAbroadMapper.updateByPrimaryKeySelective(be);
                }
            }
            
            //判断当只有原来有数据的时候,才会去判断
            if(abroadIdsStr != null && abroadIdsStr.length>0){
               List<String> cList= compare(abroadIdsStr,abroadIdsNewList);
               if(cList!=null && cList.size()>0){
                   //遍历 删除
                   for(String str : cList) {  
                       BiAbroad biAbroad=biAbroadMapper.selectByPrimaryKey(str);
                       if(biAbroad != null) {
                           biAbroadMapper.deleteByPrimaryKey(biAbroad.getAbroadId());
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
        int count = this.biBaseInfoMapperExt.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }
    
    public BiBaseInfoDto selectByPrimaryKey(String baseInfoId) {
        return this.biBaseInfoMapperExt.selectByPrimaryKey(baseInfoId);
    }
    
    public List<BiBaseInfoDto> selectByExample(Criteria example) {
        return this.biBaseInfoMapperExt.selectByExample(example);
    }
    
    public int insertSelective(BiBaseInfo record) {
        return this.biBaseInfoMapper.insertSelective(record);
    }
   
    public int updateByPrimaryKeySelective(BiBaseInfo record) {
        return this.biBaseInfoMapper.updateByPrimaryKeySelective(record);
    }
   
    public int deleteByPrimaryKey(String baseInfoId) {
        return this.biBaseInfoMapper.deleteByPrimaryKey(baseInfoId);
    }
    
    public BiBaseInfo selectByAccountId(String accountId) {
        return this.biBaseInfoMapperExt.selectByAccountId(accountId);
    }
    
    public int updateByPrimaryKey(BiBaseInfo record,String baseInfoDel) {
        // baseInfoDel=0:恢复;baseInfoDel=1:删除;baseInfoDel=2:彻底删除
        if(!("2").equals(baseInfoDel)){
            //更改数据状态
            record.setBaseInfoDel(baseInfoDel);
            return this.biBaseInfoMapper.updateByPrimaryKey(record);
        }else{
        	
        	// 根据账号id,将该账号的状态该给已使用
        	CommonAccount ca= commonAccountMapper.selectByPrimaryKey(record.getAccountId());
        	if(ca!=null){
        		ca.setAccountStatus("0");
        		commonAccountMapper.updateByPrimaryKeySelective(ca);
        	}
            //从数据库中删除
            return this.biBaseInfoMapper.deleteByPrimaryKey(record.getBaseInfoId());
        }
    }

    public int deleteByExample(Criteria example) {
        return this.biBaseInfoMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(BiBaseInfo record, Criteria example) {
        return this.biBaseInfoMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(BiBaseInfo record, Criteria example) {
        return this.biBaseInfoMapper.updateByExample(record, example);
    }

    public int insert(BiBaseInfo record) {
        return this.biBaseInfoMapper.insert(record);
    }
}