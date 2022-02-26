package schoolclientdemo.service;

import java.util.List;

import schoolclientdemo.model.Result;

public interface ResultService {

	List<Result> findLectureResults(String userId, String lectureId);

	List<Result> updateAllResults(String userId, List<Result> results);

	void deleteResult(String userId, String resultId);

	void deleteAllResult(String userId, String lectureId);


}
