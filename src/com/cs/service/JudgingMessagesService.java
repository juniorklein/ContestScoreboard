package com.cs.service;

import java.util.List;

import com.cs.model.Contestant;
import com.cs.model.ContestantMessage;

public interface JudgingMessagesService {

	public List<Contestant> judgeMessages(List<ContestantMessage> contestantMessages);

}
