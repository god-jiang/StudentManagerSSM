package god.jiang.programmer.entity;

import org.springframework.stereotype.Component;

/**
 * 班级实体
 * @author llq
 *
 */
@Component
public class Clazz {
	private Long id;
	private Long gradeId;//年级id
	private String name;
	private String remark;//备注
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getGradeId() {
		return gradeId;
	}
	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
	}
	
}
