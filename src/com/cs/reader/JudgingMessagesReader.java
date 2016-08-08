package com.cs.reader;

import java.util.List;

import com.cs.model.ContestantMessage;

public interface JudgingMessagesReader {

	/**
	 * Read judging queue from a input and generate a list of messages to process. Returns null when user doesn't want continue.
	 * 
	 * @return A list of judging queue sequence, or a null if an problem occours.
	 */
	public List<List<ContestantMessage>> readJudgingMessages();

}
