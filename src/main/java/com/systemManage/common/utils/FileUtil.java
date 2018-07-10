package com.systemManage.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.systemManage.common.exception.SystemException;

/**
 * 类     名:FileUtil
 * 作     用:文件操作共通类, 对于文件的共通操作
 * 作     者:张金秋
 * 日     期:2017年6月27日 下午22:10:58
 */
public class FileUtil {
	
	//日志
	public static Logger logger = LoggerFactory.getLogger(FileUtil.class);
	
	//允许上传的后缀名
	public static String[] ALLOWEXTS = new String[]{"jpg","jpeg","gif","png","bmp","pdf","docx","pptx","xlsx","doc","mp3","mp4"};

    /**
     * 新建文件
     * @param filePath 文件路径
     * @throws SystemException
     */
    public static void createFile(String filePath) throws SystemException {
        File file = new File(filePath);
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
        	e.printStackTrace();
            throw new SystemException("新建文件失败");
        }
    }

    /**
     * 新建文件夹
     * @param filePath 文件路径
     */
    public static void createDir(String filePath) {
        File file = new File(filePath);
        file.mkdirs();
    }

    /**
     * 删除指定路径的文件
     * @param filePath 文件路径
     */
    public static void deleteFile(String filePath,HttpServletRequest request) {
        if(StringUtil.isNotBlank(filePath)) {
            String target = request.getSession().getServletContext().getRealPath("/"+filePath);
            File file = new File(target);
            if (!file.isDirectory()) {
                file.delete();
            }
            else {
                deleteDir(file);
            }
        }
    }

    /**
     * 递归删除目录所有内容
     * @param dir
     * @return
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            File[] listFiles = dir.listFiles();
            for (int i = 0; i < listFiles.length && deleteDir(listFiles[i]); i++) {
            }
        }
        return dir.delete();
    }

    /**
     * 文件拷贝
     * @param resourceFimeName 源文件的路径名称
     * @param targetFileName 目的文件的路径名称
     * @return
     * @throws IOException
     */
    public static boolean copyFile(String resourceFimeName, String targetFileName) throws IOException {
        return copyFile(new File(resourceFimeName), new File(targetFileName));
    }

    /**
     * 文件拷贝
     * @param resourceFimeName 源文件的路径名称
     * @param targetFile 目的文件
     * @return
     * @throws IOException
     */
    public static boolean copyFile(String resourceFimeName, File targetFile) throws IOException {
        return copyFile(new File(resourceFimeName), targetFile);
    }

    /**
     * 文件拷贝
     * @param resourceFile 源文件
     * @param targetFileName 目的文件的路径名称
     * @return
     * @throws IOException
     */
    public static boolean copyFile(File resourceFile, String targetFileName) throws IOException {
        return copyFile(resourceFile, new File(targetFileName));
    }

    /**
     * 文件拷贝
     * @param resourceFile 源文件
     * @param targetFile 目的文件
     * @return
     * @throws IOException
     */
    public static boolean copyFile(File resourceFile, File targetFile){
        if (resourceFile == null || targetFile == null)
            return false;
        try {
        	 if (resourceFile.exists()) {
                 if (!targetFile.exists()) {
                     File parentFile = targetFile.getParentFile();
                     if (!parentFile.exists())
                         parentFile.mkdirs();
                     targetFile.createNewFile();
                 }
                 FileInputStream in = new FileInputStream(resourceFile);
                 FileOutputStream out = new FileOutputStream(targetFile);
                 byte[] buffer = new byte[1024 * 8];
                 int i = 0;
                 while ((i = in.read(buffer)) != -1) {
                     out.write(buffer, 0, i);
                 }
                 out.flush();
                 in.close();
                 out.close();
                 return true;
             }
             else {
                 return false;
             }
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("copyFile 出现异常!");
			return false;
		}
       
    }
    
    /**
	 * 方法名:	getExt
	 * 方法描述：获得文件后缀名
	 * 参数：	@param tempName
	 * 参数：	@return 
	 * 返回值：	String
	 * @exception 
	 * @since  1.0.0
	 */
	public static String getExt(String fullName) {
		if (StringUtils.isNotBlank(fullName)) {
			return fullName.substring(fullName.lastIndexOf(".") + 1, fullName .length()).toLowerCase();
		}
		return null;
	}
	
	/**
     * 方法名: comparExt
     * 方法描述：比较后缀名
     * 参数：  @param path
     * 参数：  @return 
     * 返回值： boolean
     * @exception 
     * @since  1.0.0
     */
    public static boolean comparExt(String path){
        //获得后缀
        String ext = getExt(path);
        Pattern p = Pattern.compile("^(jpg|jpeg|png|gif|bmp|pdf|docx|pptx|xlsx)$",Pattern.CASE_INSENSITIVE);
        Matcher m =p.matcher(ext);
        return m.matches();
    }
	
	/**
	 *方法名:flieUpload
	 *返回值:String 图片名称
	 *作     用:springMVC图片上传并返回上传后存储路径
	 *参     数:@param file 文件
	 *参     数:@param path 上传到路径
	 *参     数:@param fileName 外部传递组装文件名称
	 *参     数:@param request 
	 *作     者:张金秋
	 *日     期:2017年06月27日 下午22:21:45
	 * @throws SystemException 
	 */
	public static String flieUpload(MultipartFile file, String path, String fileName, HttpServletRequest request) throws SystemException {
		String newName = null;
		if(!file.isEmpty()){
			//文件全名
			String fullName = file.getOriginalFilename();
			//文件后缀名
			String extStr = FileUtil.getExt(fullName);
			//判断是否该类型的文件允许上传
			if(!ArrayUtils.contains(ALLOWEXTS, extStr)){
				logger.error(fullName + "上传文件的类型不匹配!");
				throw new SystemException("上传文件的类型不匹配");
			}else{
				//生成文件名
				newName = fileName + "." + extStr;
				// 获取目标服务器真实路径
				String target = request.getSession().getServletContext().getRealPath("/upload/"+path) + "/" + newName; 
				try {
					FileUtils.copyInputStreamToFile(file.getInputStream(), new File(target));
				} catch (IOException e) {
					newName = null;
					logger.error("上传图片出现错误");
					e.printStackTrace();
					throw new SystemException("上传图片出现错误");
				}
			}
		}
 		return newName;
	}

}
