package god.jiang.programmer.service;

import god.jiang.programmer.entity.Grade;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * Äê¼¶service
 * @author llq
 *
 */
@Service
public interface GradeService {
	public int add(Grade grade);
	public int edit(Grade grade);
	public int delete(String ids);
	public List<Grade> findList(Map<String,Object> queryMap);
	public List<Grade> findAll();
	public int getTotal(Map<String,Object> queryMap);
}
