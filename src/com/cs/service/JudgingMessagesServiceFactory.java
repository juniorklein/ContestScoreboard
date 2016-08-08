package com.cs.service;

import com.cs.service.criteria.JudgementCriteria;

public class JudgingMessagesServiceFactory {

	public JudgingMessagesService getJudgingMessagesService(JudgementCriteria judgmentCriteria) {
		return new JudgingMessagesServiceImpl(judgmentCriteria, 10);
	}

}
