package com.chenys.util;

import java.util.UUID;

import com.chenys.solr.entity.Person;
import com.chenys.solr.util.SolrUtils;

/** @author:  v_chenyongshuai@:
  * @date:  2018年7月10日 下午2:34:16 
  * @version：   1.0.0
  * @describe:    
  */
public class SolrUtilsTest {
	public static void main(String[] args) {
		Person person = new Person(UUID.randomUUID().toString(),"Amazon Kindle Paperwhite","打篮球看电影吃火锅");
		int solrAdd = SolrUtils.solrAdd(person);
		System.out.println(solrAdd);
		SolrUtils.solrQuery("p_hobby", "电影");
		int solrDelete = SolrUtils.solrDelete("p_id", "b4b337e3-16ae-4042-8bd2-751290259870");
		System.out.println(solrDelete);
	}
}
