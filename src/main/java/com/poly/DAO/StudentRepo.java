package com.poly.DAO;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.bean.Student;
import com.poly.bean.StudentMap;
@Repository
public class StudentRepo {
	private RestTemplate rest = new RestTemplate();
	private String url="https://java6cuoimon-default-rtdb.firebaseio.com/students.json";
	public String getUrl(String key) {
		return url.replace(".json","/"+key+".json");
	}
	public StudentMap findAll() {
		return rest.getForObject(url, StudentMap.class);	
		}
	public Student findBykey(String key) {
		return rest.getForObject(getUrl(key), Student.class);
	}
	public String create(Student data) {
		HttpEntity<Student> entity=new HttpEntity<>(data);
		JsonNode resp= rest.postForObject(url, entity,JsonNode.class);
		return resp.get("name").asText();
	}
	public Student update(String key,Student data) {
		HttpEntity<Student> entity=new HttpEntity<>(data);
		rest.put(getUrl(key), entity);
		return data;
	}
	public void delete(String key) {
		rest.delete(getUrl(key));
		
	}
}
