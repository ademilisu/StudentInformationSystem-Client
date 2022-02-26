package schoolclientdemo.model;

import java.util.List;

public class ResultList {
	
	private List<Result> results;
	
	public ResultList() {}

	public ResultList(List<Result> results) {
		this.results = results;
	}

	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}
	
}
