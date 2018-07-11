package com.chenys.solr.util;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import com.chenys.solr.entity.Person;

/** @author:  v_chenyongshuai@:
  * @date:  2018年7月10日 下午2:29:43 
  * @version：   1.0.0
  * @describe:    
  */
public class SolrUtils {
	private static final String SolrURL = "http://192.168.126.128:8983/solr/mycore";
	
	public static HttpSolrClient getHttpSolrClient(){
		HttpSolrClient client = new HttpSolrClient.Builder(SolrURL)
                .withConnectionTimeout(10000)
                .withSocketTimeout(60000)
                .build();
		return client;
		
	}
	public static int solrAdd(Person person){
		//创建solrClient同时指定超时时间，不指定走默认配置
		HttpSolrClient client = SolrUtils.getHttpSolrClient();
        
      //[2]创建文档doc
        SolrInputDocument doc = new SolrInputDocument();
        int status=0;
      //[3]添加内容
        doc.addField("p_id", person.getP_id());
        doc.addField("p_name", person.getP_name());
        doc.addField("p_hobby", person.getP_hobby());
        //[4]添加到client
        UpdateResponse updateResponse;
		try {
			updateResponse = client.add(doc);
			status = updateResponse.getStatus();
	        //[5] 索引文档必须commit
	        client.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return status;
	}
	public static void solrQuery(String field, String queryStr){
		//创建solrClient同时指定超时时间，不指定走默认配置
		HttpSolrClient client = SolrUtils.getHttpSolrClient();
        //[2]封装查询参数
        SolrQuery query = new SolrQuery(field+":"+queryStr);
        
        //[3]添加需要回显得内容
        query.addField("p_id");
        query.addField("p_name");
        query.addField("p_hobby");
        query.setRows(20);//设置每页显示多少条
        //[4]执行查询返回QueryResponse
        QueryResponse response;
		try {
			response = client.query(query);
			//[5]获取doc文档
	        SolrDocumentList documents = response.getResults();
	        //[6]内容遍历
	        for(SolrDocument doc : documents) {
	            System.out.println("id:"+doc.get("p_id")+", name:"+doc.get("p_name")+", hobby:"+doc.get("p_hobby"));
	        }
	        client.close();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static int solrDelete(String field, String queryStr){
		//创建solrClient同时指定超时时间，不指定走默认配置
		HttpSolrClient client = SolrUtils.getHttpSolrClient();
		
		int status = 0;
		try {
			UpdateResponse response = client.deleteByQuery(field+":"+queryStr);
			status = response.getStatus();
			//[3]提交操作
            client.commit();
            //[4]关闭资源
            client.close(); 
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return status;
	}
}
