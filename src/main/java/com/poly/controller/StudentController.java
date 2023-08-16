package com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.DAO.StudentRepo;
import com.poly.bean.Student;
import com.poly.bean.StudentMap;



@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	StudentRepo dao;
	
	@RequestMapping("/index")
	public String doGetHome(Model model) {
		Student student = new Student();
		model.addAttribute("student",student);
		StudentMap list = dao.findAll();
		model.addAttribute("items",list);
		return "student/index";
	}
	
	@GetMapping("/edit/{key}")
	public String edit(Model model,@PathVariable("key") String key) {
		model.addAttribute("key", key);
		Student student = dao.findBykey(key);
		model.addAttribute("student", student);
		StudentMap list = dao.findAll();
		model.addAttribute("items",list);
		return "student/index";
	}
	
	@PostMapping("/create")
	public String doPostCreate(Student student) {
		dao.create(student);
		return "redirect:/student/index";
	}
	
	@PostMapping("/update/{key}")
	public String doPostUpdate(@PathVariable("key") String key,Student student) {
		dao.update(key,student);
		return "redirect:/student/edit/" + key;
	}
	
	@RequestMapping("/delete/{key}")
	public String doPostDelete(@PathVariable("key") String key) {
		dao.delete(key);
		return "redirect:/student/index";
	}
	
}
