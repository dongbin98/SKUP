package com.dbsh.skup.tmpstructure.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseLectureDetail {
	@SerializedName("CLASSDAY")
	@Expose
	private String lectureDay;

	@SerializedName("CLSS_NUMB")
	@Expose
	private String lectureNumber;

	@SerializedName("SUBJ_CD")
	@Expose
	private String lectureCd;

	@SerializedName("SUBJ_TIME")
	@Expose
	private String lectureTime;

	@SerializedName("CLASS_NAME")
	@Expose
	private String lectureName;

	@SerializedName("PROF_NO")
	@Expose
	private String professor;

	@SerializedName("CLASSHOUR_START_TIME")
	@Expose
	private String lectureStartTime;

	@SerializedName("CLASSHOUR_END_TIME")
	@Expose
	private String lectureEndTime;

	@SerializedName("LECT_YEAR")
	@Expose
	private String year;

	@SerializedName("LECT_TERM")
	@Expose
	private String term;

	public String getLectureDay() {
		return lectureDay;
	}

	public void setLectureDay(String lectureDay) {
		this.lectureDay = lectureDay;
	}

	public String getLectureNumber() {
		return lectureNumber;
	}

	public void setLectureNumber(String lectureNumber) {
		this.lectureNumber = lectureNumber;
	}

	public String getLectureCd() {
		return lectureCd;
	}

	public void setLectureCd(String lectureCd) {
		this.lectureCd = lectureCd;
	}

	public String getLectureTime() {
		return lectureTime;
	}

	public void setLectureTime(String lectureTime) {
		this.lectureTime = lectureTime;
	}

	public String getLectureName() {
		return lectureName;
	}

	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public String getLectureStartTime() {
		return lectureStartTime;
	}

	public void setLectureStartTime(String lectureStartTime) {
		this.lectureStartTime = lectureStartTime;
	}

	public String getLectureEndTime() {
		return lectureEndTime;
	}

	public void setLectureEndTime(String lectureEndTime) {
		this.lectureEndTime = lectureEndTime;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	@Override
	public String toString() {
		return "ResponseLectureDetail{" +
				"lectureDay='" + lectureDay + '\'' +
				", lectureNumber='" + lectureNumber + '\'' +
				", lectureCd='" + lectureCd + '\'' +
				", lectureTime='" + lectureTime + '\'' +
				", lectureName='" + lectureName + '\'' +
				", professor='" + professor + '\'' +
				", lectureStartTime='" + lectureStartTime + '\'' +
				", lectureEndTime='" + lectureEndTime + '\'' +
				", year='" + year + '\'' +
				", term='" + term + '\'' +
				'}';
	}
}
