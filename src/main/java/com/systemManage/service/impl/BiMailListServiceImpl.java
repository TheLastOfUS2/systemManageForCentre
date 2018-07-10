package com.systemManage.service.impl;

import com.systemManage.common.utils.JsonUtils;
import com.systemManage.common.utils.StringUtil;
import com.systemManage.dao.base.BiBaseInfoMapper;
import com.systemManage.dao.base.BiMailListMapper;
import com.systemManage.dao.ext.BiMailListMapperExt;
import com.systemManage.pojo.base.BiBaseInfo;
import com.systemManage.pojo.base.BiMailList;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.dto.BiMailListDto;
import com.systemManage.service.BiMailListService;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BiMailListServiceImpl implements BiMailListService {
    @Autowired
    private BiMailListMapper biMailListMapper; // 通讯录
    @Autowired
    private BiMailListMapperExt biMailListMapperExt; // 通讯录扩展
    @Autowired
    private BiBaseInfoMapper biBaseInfoMapper; // 基本信息

    private static final Logger logger = LoggerFactory.getLogger(BiMailListServiceImpl.class);

    public int countByExample(Criteria example) {
        int count = this.biMailListMapperExt.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }
    
    public List<BiMailListDto> selectByExample(Criteria example) {
        return this.biMailListMapperExt.selectByExample(example);
    }

    public BiMailList selectByPrimaryKey(String mailListId) {
        return this.biMailListMapper.selectByPrimaryKey(mailListId);
    }
    
    @Override
    public JSONObject insertSelective(BiMailListDto biMailListDto, HttpServletRequest request) {
        int res = 0;
        JSONObject result = null;
        //更新基本信息
        if(StringUtil.isNotBlank(biMailListDto.getBaseInfoId())){
            BiBaseInfo biBaseInfo = biBaseInfoMapper.selectByPrimaryKey(biMailListDto.getBaseInfoId());
            if(biBaseInfo!=null){
                BeanUtils.copyProperties(biMailListDto, biBaseInfo); 
                res = this.biBaseInfoMapper.updateByPrimaryKeySelective(biBaseInfo);
            }
        }
        //保存通讯信息
        if(biMailListDto!=null){
            String mailListId=biMailListDto.getMailListId();
            if (StringUtil.isEmpty(mailListId)) {
                //设置主键
                biMailListDto.setMailListId(StringUtil.getUUID());
                //关联基本信息ID
                biMailListDto.setBaseInfoId(biMailListDto.getBaseInfoId());
                //设置新增时间
                biMailListDto.setMailListCreateTime(new Date());
                //设置删除状态（0.未删除1.已删除）
                biMailListDto.setMailListDel("0");
            }
            //保存基本信息
            if (StringUtil.isEmpty(mailListId)) {
                res = this.biMailListMapper.insertSelective(biMailListDto);
            }else{
                res = this.biMailListMapper.updateByPrimaryKeySelective(biMailListDto);
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
    
    @Override
    public BiMailListDto selectByPrimaryKeyExt(String mailListId) {
        return this.biMailListMapperExt.selectByPrimaryKeyExt(mailListId);
    }
    
    @Override
    public BiMailListDto selectByBaseInfoIdExt(String baseInfoId) {
        return this.biMailListMapperExt.selectBaseInfoIdExt(baseInfoId);
    }
    
    @Override
    public BiMailListDto selectByMailListId(String mailListId) {
        return this.biMailListMapperExt.selectByMailListIdExt(mailListId);
    }
    
    @Override
    public BiMailListDto selectByAccountId(String accountId) {
        return this.biMailListMapperExt.selectByAccountId(accountId);
    }
    
    public int deleteByPrimaryKey(String mailListId) {
        return this.biMailListMapper.deleteByPrimaryKey(mailListId);
    }

    public int updateByPrimaryKeySelective(BiMailList record) {
        return this.biMailListMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(BiMailList record,String mailListDel) {
        // mailListDel=0:恢复;mailListDel=1:删除;mailListDel=2:彻底删除
        if(!("2").equals(mailListDel)){
            //更改数据状态
            record.setMailListDel(mailListDel);
            return this.biMailListMapper.updateByPrimaryKey(record);
        }else{
            //从数据库中删除
            return this.biMailListMapper.deleteByPrimaryKey(record.getMailListId());
        }
    }

    public int deleteByExample(Criteria example) {
        return this.biMailListMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(BiMailList record, Criteria example) {
        return this.biMailListMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(BiMailList record, Criteria example) {
        return this.biMailListMapper.updateByExample(record, example);
    }

    public int insert(BiMailList record) {
        return this.biMailListMapper.insert(record);
    }

    public int insertSelective(BiMailList record) {
        return this.biMailListMapper.insertSelective(record);
    }
}