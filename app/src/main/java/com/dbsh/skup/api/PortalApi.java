package com.dbsh.skup.api;

import com.dbsh.skup.dto.RequestAttendanceData;
import com.dbsh.skup.dto.RequestAttendanceDetailData;
import com.dbsh.skup.dto.RequestGradeTermData;
import com.dbsh.skup.dto.RequestGradeTermSubjectData;
import com.dbsh.skup.dto.RequestGradeTotalCreditData;
import com.dbsh.skup.dto.RequestGradeTotalData;
import com.dbsh.skup.dto.RequestGraduateBasicData;
import com.dbsh.skup.dto.RequestGraduateNoneSubjectData;
import com.dbsh.skup.dto.RequestGraduateSubjectData;
import com.dbsh.skup.dto.RequestInformationChangeData;
import com.dbsh.skup.dto.RequestInformationChangeEnglishNameData;
import com.dbsh.skup.dto.RequestInformationData;
import com.dbsh.skup.dto.RequestLectureData;
import com.dbsh.skup.dto.RequestLecturePlanBookData;
import com.dbsh.skup.dto.RequestLecturePlanSummaryData;
import com.dbsh.skup.dto.RequestLecturePlanData;
import com.dbsh.skup.dto.RequestLecturePlanWeekData;
import com.dbsh.skup.dto.RequestScholarshipData;
import com.dbsh.skup.dto.RequestTuitionData;
import com.dbsh.skup.dto.ResponseAttendance;
import com.dbsh.skup.dto.ResponseAttendanceDetail;
import com.dbsh.skup.dto.ResponseGradeTerm;
import com.dbsh.skup.dto.ResponseGradeTermSubject;
import com.dbsh.skup.dto.ResponseGradeTotal;
import com.dbsh.skup.dto.ResponseGradeTotalCredit;
import com.dbsh.skup.dto.ResponseGraduateBasic;
import com.dbsh.skup.dto.ResponseGraduateNoneSubject;
import com.dbsh.skup.dto.ResponseGraduateSubject;
import com.dbsh.skup.dto.ResponseInformation;
import com.dbsh.skup.dto.ResponseInformationChange;
import com.dbsh.skup.dto.ResponseLecture;
import com.dbsh.skup.dto.ResponseLecturePlanBook;
import com.dbsh.skup.dto.ResponseLecturePlanSummary;
import com.dbsh.skup.dto.ResponseLecturePlan;
import com.dbsh.skup.dto.ResponseLecturePlanWeek;
import com.dbsh.skup.dto.ResponseScholar;
import com.dbsh.skup.dto.ResponseTuition;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface PortalApi {

	/* 강의 리스트 */
	@Headers({"Accept: application/json", "content-type: application/json"})
	@POST("common/selectList.sku")
	Call<ResponseLecture> getLectureData(@Body RequestLectureData requestLectureData);

	/* 등록금 */
	@Headers({"Accept: application/json", "content-type: application/json"})
	@POST("common/selectOne.sku")
	Call<ResponseTuition> getTuitionData(@Body RequestTuitionData requestTuitionData);

	/* 출결 조회 */
	@Headers({"Accept: application/json", "content-type: application/json"})
	@POST("common/selectList.sku")
	Call<ResponseAttendance> getAttendanceData(@Body RequestAttendanceData requestAttendanceData);

	/* 출결 상세조회 */
	@Headers({"Accept: application/json", "content-type: application/json"})
	@POST("common/selectList.sku")
	Call<ResponseAttendanceDetail> getAttendanceDetailData(@Body RequestAttendanceDetailData requestAttendanceDetailData);

	/* 장학 조회 */
	@Headers({"Accept: application/json", "content-type: application/json"})
	@POST("common/selectList.sku")
	Call<ResponseScholar> getScholarshipData(@Body RequestScholarshipData requestScholarshipData);

	/* 졸업이수학점 기준조회 -> 기본 정보 */
	@Headers({"Accept: application/json", "content-type: application/json"})
	@POST("common/selectOne.sku")
	Call<ResponseGraduateBasic> getGraduateBasicData(@Body RequestGraduateBasicData requestGraduateBasicData);

	/* 졸업요건 취득현황 -> 교과영역 이수 확인 */
	@Headers({"Accept: application/json", "content-type: application/json"})
	@POST("common/selectList.sku")
	Call<ResponseGraduateSubject> getGraduateSubjectData(@Body RequestGraduateSubjectData requestGraduateSubjectData);

	/* 졸업요건 취득현황 -> 비교과인증 이수 확인 */
	@Headers({"Accept: application/json", "content-type: application/json"})
	@POST("common/selectOne.sku")
	Call<ResponseGraduateNoneSubject> getGraduateNoneSubjectData(@Body RequestGraduateNoneSubjectData requestGraduateNoneSubjectData);

	/* 강의계획서 */
	@Headers({"Accept: application/json", "content-type: application/json"})
	@POST("common/selectList.sku")
	Call<ResponseLecturePlan> getLecturePlanData(@Body RequestLecturePlanData requestLectureplanData);

	/* 강의계획서 -> 개요 */
	@Headers({"Accept: application/json", "content-type: application/json"})
	@POST("common/selectOne.sku")
	Call<ResponseLecturePlanSummary> getLecturePlanSummaryData(@Body RequestLecturePlanSummaryData requestLecturePlanSummaryData);

	/* 강의계획서 -> 책 */
	@Headers({"Accept: application/json", "content-type: application/json"})
	@POST("common/selectList.sku")
	Call<ResponseLecturePlanBook> getLecturePlanBookData(@Body RequestLecturePlanBookData requestLecturePlanBookData);

	/* 강의계획서 -> 주차별 진도계획 */
	@Headers({"Accept: application/json", "content-type: application/json"})
	@POST("common/selectList.sku")
	Call<ResponseLecturePlanWeek> getLecturePlanWeekData(@Body RequestLecturePlanWeekData requestLecturePlanWeekData);

	/* 성적 -> 이수 구분 별 신청/취득 학점 */
	@Headers({"Accept: application/json", "content-type: application/json"})
	@POST("common/selectList.sku")
	Call<ResponseGradeTotalCredit> getGradeTotalCredit(@Body RequestGradeTotalCreditData requestGradeTotalCreditData);

	/* 성적 -> 총 신청/취득 학점, 백분위, 총평점평균 */
	@Headers({"Accept: application/json", "content-type: application/json"})
	@POST("common/selectOne.sku")
	Call<ResponseGradeTotal> getGradeTotal(@Body RequestGradeTotalData requestGradeTotalData);

	/* 성적 -> 학기 별 성적 */
	@Headers({"Accept: application/json", "content-type: application/json"})
	@POST("common/selectList.sku")
	Call<ResponseGradeTerm> getGradeTerm(@Body RequestGradeTermData requestGradeTermData);

	/* 성적 -> 학기 별 과목 별 성적 */
	@Headers({"Accept: application/json", "content-type: application/json"})
	@POST("common/selectList.sku")
	Call<ResponseGradeTermSubject> getGradeTermSubject(@Body RequestGradeTermSubjectData requestGradeTermSubjectData);

	/* 개인정보 수정 -> 정보 가져오기 */
	@Headers({"Accept: application/json", "content-type: application/json"})
	@POST("common/selectOne.sku")
	Call<ResponseInformation> getInformation(@Body RequestInformationData requestInformationData);

	/* 개인정보 수정 -> 주소, 이메일 수정 요청하기 */
	@Headers({"Accept: application/json", "content-type: application/json"})
	@POST("common/singleProcessing.sku")
	Call<ResponseInformationChange> changeInformation(@Body RequestInformationChangeData requestInformationChangeData);

	/* 개인정보 수정 -> 영어이름 수정 요청하기 */
	@Headers({"Accept: application/json", "content-type: application/json"})
	@POST("common/singleProcessing.sku")
	Call<ResponseInformationChange> changeEnglishName(@Body RequestInformationChangeEnglishNameData requestInformationChangeEnglishNameData);
}
