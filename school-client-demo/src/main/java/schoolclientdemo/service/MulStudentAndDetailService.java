package schoolclientdemo.service;

import schoolclientdemo.model.StudentDetail;

public interface MulStudentAndDetailService {

	StudentDetail saveStudentDetail(String userId, String studentId, StudentDetail detail);

	StudentDetail findStudentDetail(String userId, String studentId);

	StudentDetail updateStudentDetail(String userId, String studentId, StudentDetail detail);


}
