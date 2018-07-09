package com.chenys.util;

import java.io.IOException;

import org.apache.mahout.math.neighborhood.Searcher;
import org.springframework.expression.spel.ast.Indexer;

import com.chenys.lucene.ik.service.IndexService;
import com.chenys.lucene.ik.service.impl.IndexServiceImpl;
import com.chenys.lucene.version_36.LuceneConstants;
import com.chenys.lucene.version_36.TextFileFilter;

/** @author:  v_chenyongshuai@:
  * @date:  2018年7月9日 下午1:46:45 
  * @version：   1.0.0
  * @describe:    
  */
public class IndexServiceTest {
	public static void main(String[] args) {
		IndexService is = new IndexServiceImpl();
		//is.createIndex(LuceneConstants.INDEX_PATH, LuceneConstants.DATA_PATH, new TextFileFilter());
		is.searchIndex("三", LuceneConstants.INDEX_PATH );
	}
}
