package com.cs.service.criteria;

import java.util.Comparator;

import com.cs.model.Contestant;
import com.cs.model.ContestantMessage;

public interface JudgementCriteria extends Comparator<Contestant> {

	/**
	 * Validate a input message e compute a contestant time;
	 * 
	 * @param contestant
	 *            The owner of a message that is computed.
	 * @param message
	 *            The message that will be computed.
	 */
	public void computeMessageEntry(Contestant contestant, ContestantMessage message);

}
