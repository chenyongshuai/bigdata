package com.chenys.lucene.ik.service;
/** @author:  v_chenyongshuai@:
  * @date:  2018年7月9日 上午11:06:12 
  * @version：   1.0.0
  * @describe:    
  */

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.List;

import org.apache.lucene.document.Document;
import org.springframework.ui.Model;

import com.chenys.lucene.dto.DocDto;
import com.chenys.lucene.version_36.TextFileFilter;

/**
 * Describe: sevice接口
 * Author: ouym
 * Created Date: 2016/11/30.
 */
public interface IndexService {

    /**
     * 构建索引，传入参数：文档路径
     * @param path
     * @return
     */
    public int createIndex(String indexDirectoryPath,String dataDir, TextFileFilter filter);


    /**
     * 通过query查询索引
     * @param query
     */
    public List<DocDto> searchIndex(String queryStr,String indexDirectoryPath);
    
    
    /**
     * 创建Doc对象
     * @param query
     */
    public Document getDocument(File file);
    
    /**
     * 为File添加索引
     * @param query
     */
    public void indexFile(File file);


	
    
    
}
