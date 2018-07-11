package com.chenys.solr.entity;

import java.io.Serializable;

/** @author:  v_chenyongshuai@:
  * @date:  2018年7月10日 下午2:35:10 
  * @version：   1.0.0
  * @describe:    
  */
public class Person implements Serializable{
	private String p_id;
	private String p_name;
	private String p_hobby;
	@Override
	public String toString() {
		return "Person [p_id=" + p_id + ", p_name=" + p_name + ", p_hobby=" + p_hobby + "]";
	}
	public String getP_id() {
		return p_id;
	}
	public void setP_id(String p_id) {
		this.p_id = p_id;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getP_hobby() {
		return p_hobby;
	}
	public void setP_hobby(String p_hobby) {
		this.p_hobby = p_hobby;
	}
	public Person() {
		super();
		
	}
	public Person(String p_id, String p_name, String p_hobby) {
		super();
		this.p_id = p_id;
		this.p_name = p_name;
		this.p_hobby = p_hobby;
	}
	
}
