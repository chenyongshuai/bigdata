package com.chenys.lucene.version_36;
/** @author:  v_chenyongshuai@:
  * @date:  2018年7月8日 下午3:31:23 
  * @version：   1.0.0
  * @describe:    
  */
import java.io.File;
import java.io.FileFilter;

public class TextFileFilter implements FileFilter {

   public boolean accept(File pathname) {
      return pathname.getName().toLowerCase().endsWith(".txt");
   }
}
