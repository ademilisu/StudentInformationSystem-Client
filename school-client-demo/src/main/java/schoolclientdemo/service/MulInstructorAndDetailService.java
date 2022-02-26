package schoolclientdemo.service;

import schoolclientdemo.model.InstructorDetail;

public interface MulInstructorAndDetailService {

	InstructorDetail saveInstructorDetail(String userId, String instructorId, InstructorDetail detail);

	InstructorDetail findInstructorDetail(String userId, String instructorId);

	InstructorDetail updateInstructorDetail(String userId, String instructorId, InstructorDetail detail);

}
