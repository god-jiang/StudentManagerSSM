package god.jiang.programmer.controller;

import god.jiang.programmer.entity.Clazz;
import god.jiang.programmer.entity.Grade;
import god.jiang.programmer.entity.Student;
import god.jiang.programmer.page.Page;
import god.jiang.programmer.service.ClazzService;
import god.jiang.programmer.service.StudentService;
import god.jiang.programmer.util.StringUtil;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
/**
 * 学生信息管理
 * @author llq
 *
 */
@RequestMapping("/student")
@Controller
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	@Autowired
	private ClazzService clazzService;
	/**
	 * 学生列表页
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		model.setViewName("student/student_list");
		List<Clazz> clazzList = clazzService.findAll();
		model.addObject("clazzList",clazzList );
		model.addObject("clazzListJson",JSONArray.fromObject(clazzList));
		return model;
	}
	
	/**
	 * 获取学生列表
	 * @param name
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/get_list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList(
			@RequestParam(value="name",required=false,defaultValue="") String name,
			@RequestParam(value="clazzId",required=false) Long clazzId,
			HttpServletRequest request,
			Page page
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("username", "%"+name+"%");
		Object attribute = request.getSession().getAttribute("userType");
		if("2".equals(attribute.toString())){
			//说明是学生
			Student loginedStudent = (Student)request.getSession().getAttribute("user");
			queryMap.put("username", "%"+loginedStudent.getUsername()+"%");
		}
		if(clazzId != null){
			queryMap.put("clazzId", clazzId);
		}
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", studentService.findList(queryMap));
		ret.put("total", studentService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * 编辑学生信息
	 * @param clazz
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(Student student){
		Map<String, String> ret = new HashMap<String, String>();
		if(StringUtils.isEmpty(student.getUsername())){
			ret.put("type", "error");
			ret.put("msg", "学生姓名不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(student.getPassword())){
			ret.put("type", "error");
			ret.put("msg", "学生登录密码不能为空！");
			return ret;
		}
		if(student.getClazzId() == null){
			ret.put("type", "error");
			ret.put("msg", "请选择所属班级！");
			return ret;
		}
		if(isExist(student.getUsername(), student.getId())){
			ret.put("type", "error");
			ret.put("msg", "该姓名已存在！");
			return ret;
		}
		student.setSn(StringUtil.generateSn("S", ""));
		if(studentService.edit(student) <= 0){
			ret.put("type", "error");
			ret.put("msg", "学生添加失败！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "学生修改成功！");
		return ret;
	}
	
	
	/**
	 * 添加学生信息
	 * @param student
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Student student){
		Map<String, String> ret = new HashMap<String, String>();
		if(StringUtils.isEmpty(student.getUsername())){
			ret.put("type", "error");
			ret.put("msg", "学生姓名不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(student.getPassword())){
			ret.put("type", "error");
			ret.put("msg", "学生登录密码不能为空！");
			return ret;
		}
		if(student.getClazzId() == null){
			ret.put("type", "error");
			ret.put("msg", "请选择所属班级！");
			return ret;
		}
		if(isExist(student.getUsername(), null)){
			ret.put("type", "error");
			ret.put("msg", "该姓名已存在！");
			return ret;
		}
		student.setSn(StringUtil.generateSn("S", ""));
		if(studentService.add(student) <= 0){
			ret.put("type", "error");
			ret.put("msg", "学生添加失败！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "学生添加成功！");
		return ret;
	}
	
	/**
	 * 删除学生信息
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(
			@RequestParam(value="ids[]",required=true) Long[] ids
			){
		Map<String, String> ret = new HashMap<String, String>();
		if(ids == null || ids.length == 0){
			ret.put("type", "error");
			ret.put("msg", "请选择要删除的数据！");
			return ret;
		}
		try {
			if(studentService.delete(StringUtil.joinString(Arrays.asList(ids), ",")) <= 0){
				ret.put("type", "error");
				ret.put("msg", "删除失败！");
				return ret;
			}
		} catch (Exception e) {
			// TODO: handle exception
			ret.put("type", "error");
			ret.put("msg", "该学生下存在其他信息，请勿冲动！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "学生删除成功！");
		return ret;
	}
	
	/**
	 * 上传用户头像图片
	 * @param photo
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/upload_photo",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> uploadPhoto(MultipartFile photo,
			HttpServletRequest request,
			HttpServletResponse response
			) throws IOException{
		Map<String, String> ret = new HashMap<String, String>();
		if(photo == null){
			//文件没有选择
			ret.put("type", "error");
			ret.put("msg", "请选择文件！");
			return ret;
		}
		if(photo.getSize() > 10485760){
			//文件没有选择
			ret.put("type", "error");
			ret.put("msg", "文件大小超过10M，请上传小于10M的图片！");
			return ret;
		}
		String suffix = photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".") + 1,photo.getOriginalFilename().length());
		if(!"jpg,png,gif,jpeg".contains(suffix.toLowerCase())){
			ret.put("type", "error");
			ret.put("msg", "文件格式不正确，请上传jpg,png,gif,jpeg格式的图片！");
			return ret;
		}
		String savePath = request.getServletContext().getRealPath("/") + "\\upload\\";
		System.out.println(savePath);
		File savePathFile = new File(savePath);
		if(!savePathFile.exists()){
			savePathFile.mkdir();//如果不存在，则创建一个文件夹upload
		}
		//把文件转存到这个文件夹下
		String filename = new Date().getTime() + "." + suffix;
		photo.transferTo(new File(savePath + filename ));
		ret.put("type", "success");
		ret.put("msg", "图片上传成功！");
		ret.put("src", request.getServletContext().getContextPath() + "/upload/" + filename);
		return ret;
	}
	
	private boolean isExist(String username,Long id){
		Student student = studentService.findByUserName(username);
		if(student != null){
			if(id == null){
				return true;
			}
			if(student.getId().longValue() != id.longValue()){
				return true;
			}
		}
		return false;
	}
}
