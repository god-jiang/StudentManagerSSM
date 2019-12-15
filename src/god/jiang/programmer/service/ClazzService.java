package god.jiang.programmer.service;

import god.jiang.programmer.entity.Clazz;
import god.jiang.programmer.entity.Grade;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * °à¼¶service
 * @author llq
 *
 */
@Service
public interface ClazzService {
	public int add(Clazz clazz);
	public int edit(Clazz clazz);
	public int delete(String ids);
	public List<Clazz> findList(Map<String,Object> queryMap);
	public List<Clazz> findAll();
	public int getTotal(Map<String,Object> queryMap);
}
