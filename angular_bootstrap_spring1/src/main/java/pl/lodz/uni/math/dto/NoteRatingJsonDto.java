package pl.lodz.uni.math.dto;

import java.util.List;

public class NoteRatingJsonDto {

	private int iTotalRecords;

	private int iTotalDisplayRecords;

	private String sEcho;

	private String sColumns;

	private List<NoteRatingDto> aaData;

	public int getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public String getsColumns() {
		return sColumns;
	}

	public void setsColumns(String sColumns) {
		this.sColumns = sColumns;
	}

	public List<NoteRatingDto> getAaData() {
		return aaData;
	}

	public void setAaData(List<NoteRatingDto> aaData) {
		this.aaData = aaData;
	}

}