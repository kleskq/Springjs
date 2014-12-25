package pl.lodz.uni.math.dto;

import java.util.Date;

public class NoteLDto {
	private String noteTitle;
	private String categoryName;
	private Date createDate;
	private long code;

	public String getNoteTitle() {
		return noteTitle;
	}

	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public NoteLDto() {
		super();
	}

	public NoteLDto(String noteTitle, String categoryName, Date createDate,
			long code) {
		super();
		this.noteTitle = noteTitle;
		this.categoryName = categoryName;
		this.createDate = createDate;
		this.code = code;
	}

}
