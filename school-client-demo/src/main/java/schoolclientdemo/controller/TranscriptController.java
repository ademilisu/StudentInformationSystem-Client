package schoolclientdemo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import schoolclientdemo.config.ManageCookies;
import schoolclientdemo.model.Lecture;
import schoolclientdemo.model.Transcript;
import schoolclientdemo.service.TranscriptService;

@Controller
@RequestMapping("/")
public class TranscriptController {
	
	@Autowired
	private ManageCookies manageCookie;
	
	@Autowired
	private TranscriptService transcriptService;
	
	@GetMapping("/showMyTranscript")
	public String showMyTranscript(HttpServletRequest request, 
			Model theModel) {
		String userId = manageCookie.getUserId(request);
		String typeId = manageCookie.getTypeId(request);
		List<Transcript> transcripts = transcriptService.findMyAllTranscripts(userId, typeId);
		List<Lecture> lectures = transcriptService.findMylecturesInTranscript(userId, typeId);
		Map<Integer, Lecture> lectureMap = new HashMap<>();
		for(Lecture temp : lectures) {
			lectureMap.put(temp.getId(), temp);
		}
		theModel.addAttribute("lectures", lectureMap);
		theModel.addAttribute("transcripts", transcripts);
		return "myTranscript";
	}
}
