package god.jiang.programmer.service.impl;

import god.jiang.programmer.dao.GradeDao;
import god.jiang.programmer.entity.Grade;
import god.jiang.programmer.service.GradeService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class GradeServiceImpl implements GradeService {

	@Autowired
	private GradeDao gradeDao;
	
	@Override
	public int add(Grade grade) {
		// TODO Auto-generated method stub
		return gradeDao.add(grade);
	}

	@Override
	public int edit(Grade grade) {
		// TODO Auto-generated method stub
		return gradeDao.edit(grade);
	}

	@Override
	public int delete(String ids) {
		// TODO Auto-generated method stub
		return gradeDao.delete(ids);
	}

	@Override
	public List<Grade> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return gradeDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return gradeDao.getTotal(queryMap);
	}

	@Override
	public List<Grade> findAll() {
		// TODO Auto-generated method stub
		return gradeDao.findAll();
	}

}
