package schoolclientdemo.service;

import java.util.List;

import schoolclientdemo.model.Lecture;
import schoolclientdemo.model.Transcript;

public interface TranscriptService {

	List<Transcript> findMyAllTranscripts(String userId, String studentId);

	List<Lecture> findMylecturesInTranscript(String userId, String studentId);

}
