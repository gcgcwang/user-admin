package com.vfc.useradmin.temple;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vfc.useradmin.core.util.StringUtil;
import com.vfc.useradmin.jpa.enums.PkGeneratorEnum;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class FreemarkerUtil {

	private static final Logger logger = LoggerFactory.getLogger(FreemarkerUtil.class);
	
	public static String CLASS_PATH = FreemarkerUtil.class.getResource("/").getPath();
	public static String ROOT_PATH = CLASS_PATH.substring(0,CLASS_PATH.indexOf("/WEB-INF"));
	

	
	public static String jdbc_driver;
	public static String jdbc_url;
	public static String jdbc_username;
	public static String jdbc_password;
	
	
	static{
		Properties prop = new Properties();
		try {
			prop.load(FreemarkerUtil.class.getResourceAsStream("/config/spring/prop.properties"));
		} catch (IOException e) {
			throw new RuntimeException("JDBC读取prop.properties文件异常",e);
		}
		jdbc_driver=prop.getProperty("jdbc_driver");
		jdbc_url=prop.getProperty("jdbc_url");
		jdbc_username=prop.getProperty("jdbc_username");
		jdbc_password=prop.getProperty("jdbc_password");
	}

	
	
	 public static void main(String[] args) {

	    	BuilderSetting setting = new BuilderSetting();
	    	setting.setTableName("sys_dic");
	    	setting.setPackageName("com.vfc.useradmin.module.system.vo");
	    	setting.setClassName("SysDic");
	    	setting.setPkGenType(PkGeneratorEnum.AUTO_INCREASING);
	    	setting.setSeqName(null);
	    	
	    	BuilderService service =new BuilderService();
	    	service.dataMappingToTemple(setting);
	    	buildAndWriteTemple("tmpVO.ftl",  CLASS_PATH+"/com/vfc.useradmin/temple/ftl"
	    			, setting.getClassName()+"VO.java"  
	    			,ROOT_PATH.replaceFirst("/WebRoot", "/src")+convertToPath(setting.getPackageName())
	    			, setting);
	    	logger.info("=======BUILDER    SUCCESS ================");
		}
	
	 
	 
	 
	 private static String convertToPath(String packageName) {
		// TODO 
		 try {
		 if(packageName==null)throw new Exception("packageName不为空");
		 packageName=packageName.replace(".", "/");
		 return "/"+packageName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}




	/**
	     * 输出HTML文件
	     *  url 默认为WRITED_PAGE_PATH
	     * @param outFile
	     */
	    public static void buildAndWriteTemple(String temp_name, String temp_path,
	    		String file_name, String file_path, Object entry) {
	        FileWriter out = null;
	        try {
	            // 通过一个文件输出流，就可以写到相应的文件中，此处用的是绝对路径
	        	if(temp_path==null || file_path==null)throw new Exception("参数url不能为空");
	        	logger.info("======temp_path路径==="+ temp_path+temp_name);
	        	logger.info("======file_path路径==="+ file_path+file_name);
	        	 File file=new File( file_path+"/"+file_name);
	        	 if(!file.exists()){
	        		if(file.isDirectory()){
	        			file.mkdirs();
	        		}else{
	        			File parent_file = file.getParentFile();
		        		if(!parent_file.exists()){
		        			parent_file.mkdirs();
		        		}
	        			file.createNewFile();
	        		}
	        	}
	            out = new FileWriter(file);
	            Template temp = getTemplate(temp_name,temp_path);
	            temp.process(entry, out);
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (out != null)
	                    out.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	 
	 
	
    public static Template getTemplate(String temp_name, String temp_path) {
        try {
            // 通过Freemaker的Configuration读取相应的ftl
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
            // 在模板文件目录中找到名称为name的文件
            logger.info("========全路径 ====" +temp_path+temp_name);
            cfg.setDirectoryForTemplateLoading(new File(temp_path));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);
            Template temp = cfg.getTemplate(temp_name);
            return temp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

	public  static String humpedColName(String col_name)throws Exception{

		if(col_name==null || "".equals(col_name))throw new Exception("参数col_name不为空");
		if(col_name.indexOf("_")==-1)return col_name.toLowerCase();

		String[] block_arr = col_name.toLowerCase().split("_");
		StringBuffer sb =new StringBuffer();
		for(int i=0 ; i< block_arr.length ; i++){
			if(i!=0){
				block_arr[i]=StringUtil.toUpperFristChar(block_arr[i]);
			}
			sb.append(block_arr[i]);
		}
		
		return sb.toString();
	}

   
   
}