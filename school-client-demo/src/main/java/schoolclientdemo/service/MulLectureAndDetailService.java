package schoolclientdemo.service;

import schoolclientdemo.model.LectureDetail;

public interface MulLectureAndDetailService {

	LectureDetail findLectureDetail(String userId, int lectureId);

	LectureDetail addLectureDetail(String userId, String lectureId, LectureDetail lectureDetail);

	LectureDetail updateLectureDetail(String userId, String lectureId, LectureDetail lectureDetail);


}
