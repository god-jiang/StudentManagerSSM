package god.jiang.programmer.page;

import org.springframework.stereotype.Component;
/**
 * 分页类封装
 * @author llq
 *
 */
@Component
public class Page {
	private int page;//当前页面
	
	private int rows;//每页显示数量
	
	private int offset;

	

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int row) {
		this.rows = row;
	}

	public int getOffset() {
		this.offset = (page -1)*rows;
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = (page -1)*rows;
	}
	
	
}
