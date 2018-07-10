package com.systemManage.web.controller.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.systemManage.common.exception.SystemException;
import com.systemManage.common.utils.DateUtil;
import com.systemManage.common.utils.FileUtil;
import com.systemManage.common.utils.JsonUtils;
import com.systemManage.common.utils.StringUtil;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.UploadFileInfo;
import com.systemManage.service.UploadFileInfoService;

/**
 * 类     名: CommonController
 * 作     用: 公共访问控制类
 * 作     者: 张金秋
 * 日     期: 2017年6月27日 下午23:04:35
 */
@Controller
@RequestMapping("/common")
public class CommonController {

	// 输出日志
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 查询条件
    Criteria criteria = new Criteria();

	// 获取项目路径
    private static String WEBCLASS_PATH = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    // 获取webinf路径
    private static String WEBINF_PATH = WEBCLASS_PATH.substring(0, WEBCLASS_PATH.lastIndexOf("classes"));
    // 获取upload路径
    private static String UPLOAD_PATH = WEBINF_PATH.substring(0, WEBINF_PATH.lastIndexOf("WEB-INF")) + "upload/";
	
	@Autowired
	private UploadFileInfoService uploadFileInfoService;
	
	/**
     * 方法名: uploadAccessory
     * 描述: 上传附件
     * 参数: @param file 上传的文件
     * 参数: @param pathWay 上传文件路径文件夹名称(upload下的第一级目录名称)
     * 参数: @param fileContent 上传文件描述
     * 参数: @param tableName 附件相关人员对应的表名
     * 参数: @param fileType 上传时选择的类型
     * 参数: @param relevanceId  关联ID
     * 参数: @param request
     * 参数: @param response     
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年6月27日 下午23:04:35
     * 版本号: v1.0   
     * 返回类型: void
     */
    @RequestMapping("/uploadFile")
   // public void uploadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
    public void uploadFile(MultipartFile file, String pathWay,String tableName,String fileFullName, String fileType, String relevanceId,String fileContent, HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = JsonUtils.setError();
    	if(!file.isEmpty()) {
    		// 获取UUID, 截取6位加上日期当作上传附件的文件名称
    		String uuidName = StringUtil.getUUID();
    		// 设置上传后文件存储路径
    		String path = pathWay + "/" + DateUtil.formatDate(new Date(), "yyyy-MM-dd");
    		// 获取上传后的文件名称
    		String fileName = DateUtil.formatDate(new Date(), "yyyyMMdd") + uuidName.substring(uuidName.length()-6,uuidName.length());
    		// 获取文件全称
			String fullName = fileFullName;//file.getOriginalFilename();
			// 获取文件后缀名
			String extString="";
			if (StringUtil.isNotBlank(fullName)) {
				extString = fullName.substring(fullName.lastIndexOf(".") + 1, fullName .length()).toLowerCase();
			}
			boolean flag=true;
			// 获取上传文件大小
    		long fileLen=file.getSize();
    		// 如果是mp3或mp4限制上传100m,其他限制上传2m
    		if(StringUtil.isNotBlank(extString)){
    			if(("mp3").equals(extString) || ("mp4").equals(extString)){
    				if(fileLen >104857600){  
    					result = JsonUtils.setError();
    		            result.put("text", "音频大小限制在100M以内");
    		            flag=false;
    				}
    			}else{
    				if(fileLen >2097152){  
    					result = JsonUtils.setError();
    					result.put("text", "文件大小限制在2M以内");
    					 flag=false;
    				}
    			}
    		}
    		if(flag){
				logger.info("执行文件上传功能, 文件名称：" + fullName);
				// 执行上传操作, 返回上传后的文件路径
	    		String uploadFilePath = "";
				try {
					uploadFilePath = FileUtil.flieUpload(file, path, fileName, request);
				} catch (SystemException e) {
					if("上传文件的类型不匹配".equals(e.getMessage())) {
						result.put("text", "上传文件的类型不匹配");
					}else {
						result.put("text", "上传图片出现错误");
					}
				}
	    		// 判断是否上传成功
	    		if(StringUtil.isNotBlank(uploadFilePath)) {
	    			// 文件上传成功, 进行持久化操作
	    			logger.info("文件上传成功, 进行持久化操作");
	    			// 组装待插入数据
		            UploadFileInfo fiUploadFileInfo = new UploadFileInfo();
		            // 主键
		            fiUploadFileInfo.setFileId(StringUtil.getUUID());
		            // 关联ID
		            fiUploadFileInfo.setRelevanceId(relevanceId);
		            // 对应表名
		            fiUploadFileInfo.setTableName(tableName);
		            // 文件名
		            fiUploadFileInfo.setFileName(fullName);
		            // 文件存储路径
		            fiUploadFileInfo.setFilePath("upload/" + path + "/" + fileName + "." + extString);
		            // 文件类型
		            fiUploadFileInfo.setFileType(fileType);
		            // 文件描述
	                fiUploadFileInfo.setFileContent(fileContent);
	                // 上传时间
	                fiUploadFileInfo.setFileCreateTime(new Date());
		            
		            // 数据库持久化文件信息
		            if(uploadFileInfoService.insert(fiUploadFileInfo) > 0){
		            	result = JsonUtils.setSuccess();
		            }else {
		            	result.put("text", "上传图片出现错误");
		            	// 持久化失败, 删除已上传文件
		            	FileUtil.deleteFile("upload/" + path + "/" + fileName + "." + extString, request);
		            }
	    		}
	    	}
    	}
    	JsonUtils.outJsonString(result.toString(), response);
    }
    
    /**
     * 方法名: downloadFile
     * 描述: 下载附件
     * 参数: @param request
     * 参数: @param response     
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年6月28日 下午00:05:28
     * 版本号: v1.0   
     * 返回类型: void
     */
    @RequestMapping("/downloadFile")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	// 获取要下载的文件名称
    	String fileId = request.getParameter("fileId");
    	// 根据ID查询到具体内容
    	UploadFileInfo uploadFileInfo = uploadFileInfoService.selectByPrimaryKey(fileId);
    	if(uploadFileInfo != null) {
    		//设置响应头和客户端保存文件名
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("content-disposition", "attachment;fileName=" + new String(uploadFileInfo.getFileName().getBytes("gb2312"), "ISO8859-1"));
           // response.setHeader("Content-Disposition", "attachment;filename=" + new String(("email.zip").getBytes(), "UTF-8"));
            try {
            	String filePath = request.getSession().getServletContext().getRealPath("/" + uploadFileInfo.getFilePath());
            	
                //打开本地文件流
                InputStream inputStream = new FileInputStream(filePath);
                //激活下载操作
                OutputStream os = response.getOutputStream();
                //循环写入输出流
                byte[] b = new byte[2048];
                int length;
                while ((length = inputStream.read(b)) > 0) {
                    os.write(b, 0, length);
                }
                // 关闭流
                os.close();
                inputStream.close();
            } catch (IOException e) {
            	throw e;
			}
    	}
    }
    
    
	/**
     * 方法名: downloadAllFiles
     * 描述: 批量下载文件
     * 参数: @param request
     * 参数: @param response
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年7月31日 下午10:03:14
     * 版本号: v1.0   
     * 抛出异常:
     * 返回类型: void
     */
    @RequestMapping("/downloadAllFiles")
    public void downloadAllFiles(HttpServletRequest request, HttpServletResponse response) {
        // 获取要下载的文件对应的信息ID-选中文件ID拼接的字符串
        String lessionIdStr = request.getParameter("fileIds");
        // 第一个文件的文件名
        String firstFileName = "";
        List<UploadFileInfo> downLoadFiles = new LinkedList<UploadFileInfo>();
        if (StringUtil.isNotEmpty(lessionIdStr)) {
            int end = lessionIdStr.lastIndexOf(",");
            if (end > 0) {
                if (end == lessionIdStr.length() - 1) {
                    lessionIdStr = lessionIdStr.substring(0, end);
                }
                String[] ids = lessionIdStr.split(",");
                for (int i = 0; i < ids.length; i++) {
                    // 循环获取每一个文件信息
                    UploadFileInfo fileInfo = uploadFileInfoService.selectByPrimaryKey(ids[i]);
                    if (fileInfo != null) {
                        downLoadFiles.add(fileInfo);
                    }
                    if (i == 0) {
                        firstFileName = fileInfo.getFileName().substring(0, fileInfo.getFileName().lastIndexOf("."));
                    }
                }
            }else {
                // 循环获取每一个文件信息
                UploadFileInfo fileInfo = uploadFileInfoService.selectByPrimaryKey(lessionIdStr);
                if (fileInfo != null) {
                    downLoadFiles.add(fileInfo);
                }
                firstFileName = fileInfo.getFileName().substring(0, fileInfo.getFileName().lastIndexOf("."));
            }
        }
        
        // 有数据可以下载 
        if (downLoadFiles.size() != 0) {
            // 进行预处理 
            preProcess(firstFileName, response);
            // 压缩处理
            writeZip(downLoadFiles);
            // 下载文件
            downFile(response);
            // 删除临时压缩文件  
            afterProcess();
        }
    }
    
    // 压缩文件输出流
    private ZipOutputStream out;
    // 临时压缩文件存储路径
    private String filePath;
    
    /**
     * 方法名: preProcess
     * 描述: 预处理
     * 参数: @param firseFileName 批量下载的第一个文件名
     * 参数: @param response     
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年8月1日 下午11:27:35
     * 版本号: v1.0   
     * 返回类型: void
     */
    private void preProcess(String firseFileName, HttpServletResponse response) {
        // 压缩文件名称
        String zipName = "【批量下载】" + firseFileName + "等.zip";
        filePath = UPLOAD_PATH + zipName;
        try {
            // 初始化压缩文件输出流
            out = new ZipOutputStream(new FileOutputStream(filePath));
            // 清空输出流(在迅雷下载不会出现一长窜)
            response.reset();
            //设置响应头和客户端保存文件名
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName=" + new String(zipName.getBytes("GBK"), "8859_1"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 方法名: writeZip
     * 描述: 压缩处理
     * 参数: @param downloadFiles 批量下载的文件数据集合
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年8月1日 下午11:28:24
     * 版本号: v1.0   
     * 返回类型: void
     */
    private void writeZip(List<UploadFileInfo> downloadFiles) {
        byte[] buf = new byte[2048];
        int len = 0;
        try {
            for (UploadFileInfo fileInfo : downloadFiles) {
                // 获取上传文件
                File file = new File(UPLOAD_PATH.substring(0, UPLOAD_PATH.lastIndexOf("upload")) + fileInfo.getFilePath());
                if (!file.isFile()) {
                    continue;
                }
                    // 设置编码
                    out.setEncoding(System.getProperty("sun.jnu.encoding"));
                    // 设置压缩文件名称
                    ZipEntry ze = new ZipEntry(fileInfo.getFileName());
                    // 加入到输出流中
                    out.putNextEntry(ze);
                    // 对源文件进行读取并输出
                    FileInputStream fis = new FileInputStream(file);
                    while ((len = fis.read(buf)) != -1) {
                        out.write(buf, 0, len);
                    }
                    // 刷新（必须要有）
                    out.flush();
                    out.closeEntry();
                    fis.close();
            }
            if (out != null) {
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }  
    }
    
    /**
     * 方法名: downFile
     * 描述: 下载临时压缩文件
     * 参数: @param response
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年8月1日 下午11:30:52
     * 版本号: v1.0
     * 返回类型: void
     */
    private void downFile(HttpServletResponse response) {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                InputStream ins = new FileInputStream(filePath);
                // 放到缓冲流里面
                BufferedInputStream bins = new BufferedInputStream(ins);
                // 获取文件输出IO流
                OutputStream outs = response.getOutputStream();  
                BufferedOutputStream bouts = new BufferedOutputStream(outs);
                int bytesRead = 0;
                byte[] buffer = new byte[1024];
                // 开始向网络传输文件流   
                while ((bytesRead = bins.read(buffer)) != -1) {
                    bouts.write(buffer, 0, bytesRead);
                }
                // 这里一定要调用flush()方法 
                bouts.flush(); 
                ins.close();
                bins.close();
                outs.close();
                bouts.close();
            }
        } catch (IOException e) {   
            logger.error("文件下载出错", e);
        }   
    }
    
    /**
     * 方法名: afterProcess
     * 描述: 删除临时压缩文件
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年8月1日 下午11:33:17
     * 版本号: v1.0
     * 返回类型: void
     */
    private void afterProcess() {
        // 删除源文件
        File tempZip=new File(filePath);
        if(tempZip.exists()) {
            tempZip.delete();
        }
    }

    /**
     * 方法名: baseInfoList
     * 描述: 根据表名和id查询附件
     * 参数: @param tableName 表名
     * 参数: @param relevanceId id
     * 参数: @param request
     * 参数: @param response
     * 参数: @return     
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年7月3日 下午4:45:31
     * 版本号: v1.0   
     * 抛出异常:
     * 返回类型: Object
     */
    @RequestMapping("/fileList")
    @ResponseBody
    public Object fileList(String tableName,String relevanceId,HttpServletRequest request, HttpServletResponse response) {
        criteria.clear();
        criteria.put("tableName", request.getParameter("tableName"));
        criteria.put("relevanceId", request.getParameter("relevanceId"));
        criteria.setOrderByClause(" file_create_time desc");
        List<UploadFileInfo> uploadFileInfo=uploadFileInfoService.selectByExample(criteria);
        return uploadFileInfo;
    }
	
    @RequestMapping("/delFile")
    public void delFile(String tableName,String relevanceId,HttpServletRequest request, HttpServletResponse response) {
    	JSONObject json = null;
        String fileId=request.getParameter("fileId");
        UploadFileInfo uploadFileInfo=uploadFileInfoService.selectByPrimaryKey(fileId);
        if(uploadFileInfo != null) {
        	uploadFileInfoService.deleteByPrimaryKey(fileId);
            json = JsonUtils.setSuccess();
            json.put("text", "操作成功");
        }else {
            json = JsonUtils.setError();
            json.put("text", "操作失败");
        }
        JsonUtils.outJsonString(json.toString(), response);
    }
    
    /**
     * 跳转到全文检索页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/toQuery")
    public String toQuery(HttpServletRequest request, HttpServletResponse response){
        return "common/toQuery";
    }
}
