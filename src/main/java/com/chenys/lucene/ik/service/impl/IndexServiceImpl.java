package com.chenys.lucene.ik.service.impl;
import org.apache.commons.io.FileUtils;
/** @author:  v_chenyongshuai@:
  * @date:  2018年7月9日 上午11:11:24 
  * @version：   1.0.0
  * @describe:    
  */
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.chenys.lucene.dto.DocDto;
import com.chenys.lucene.ik.service.IndexService;
import com.chenys.lucene.version_36.LuceneConstants;
import com.chenys.lucene.version_36.TextFileFilter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


@Service("indexService")
public class IndexServiceImpl implements IndexService {
	private IndexWriter writer;
	private Analyzer analyzer = new IKAnalyzer(true);
	
	@Override
    public int createIndex(String indexDirectoryPath ,String dataDir, TextFileFilter filter) {
    	try {
			Directory indexDirectory =  FSDirectory.open(new File(indexDirectoryPath));
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_CURRENT,analyzer);
			writer = new IndexWriter(indexDirectory, config);
			File[] files = new File(dataDir).listFiles();
			
			for (File file : files) {
		         if(!file.isDirectory()
		            && !file.isHidden()
		            && file.exists()
		            && file.canRead()
		            && filter.accept(file)
		         ){
		            indexFile(file);
		         }
		      }
			int i = writer.numDocs();
			writer.close();
			return i;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
    }
    @Override
    public List<DocDto> searchIndex(String queryStr, String indexDirectoryPath) {
    	String prefixHTML = "<font color='red'>";
        String suffixHTML = "</font>";
    	try {
			Directory indexDirectory =  FSDirectory.open(new File(indexDirectoryPath));
			DirectoryReader ireader = DirectoryReader.open(indexDirectory);
			IndexSearcher isearcher = new IndexSearcher(ireader);
			QueryParser parser = new QueryParser(Version.LUCENE_CURRENT,LuceneConstants.CONTENTS, analyzer);
			Query query = parser.parse(queryStr);
			ScoreDoc[] hits = isearcher.search(query, null, 10).scoreDocs;
			for (int i = 0; i < hits.length; i++) {
                Document hitDoc = isearcher.doc(hits[i].doc);
                //自动摘要，查询关键词高亮
                SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter(prefixHTML, suffixHTML);
                Highlighter highlighter = new Highlighter(simpleHTMLFormatter,new QueryScorer(query));
                String highLightText = highlighter.getBestFragment(analyzer,LuceneConstants.CONTENTS,hitDoc.get(LuceneConstants.CONTENTS));
                System.out.println("===="+highLightText+"====");
            }
		} catch (IOException | InvalidTokenOffsetsException | ParseException e) {
			e.printStackTrace();
		}
    	
		return null;
    	
    }

	@Override
	public Document getDocument(File file) {
		Document document = new Document();

	      //index file contents
		try {
			Field contentField = new Field(LuceneConstants.CONTENTS, FileUtils.readFileToString(file),Field.Store.YES,Field.Index.ANALYZED);
			Field fileNameField = new Field(LuceneConstants.FILE_NAME,
			         file.getName(),Field.Store.YES,Field.Index.ANALYZED);
			      //index file path
			Field filePathField = new Field(LuceneConstants.FILE_PATH,
						     file.getCanonicalPath(),Field.Store.YES,Field.Index.ANALYZED);
			
			document.add(contentField);
		    document.add(fileNameField);
		    document.add(filePathField);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	      //index file name
	      return document;
	}
	
	@Override
	public void indexFile(File file) {
		try {
			System.out.println("Indexing "+file.getCanonicalPath());
			Document document = getDocument(file);
			writer.addDocument(document);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
   
}