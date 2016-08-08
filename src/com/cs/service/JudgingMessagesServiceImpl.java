package com.cs.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cs.model.Contestant;
import com.cs.model.ContestantMessage;
import com.cs.service.criteria.JudgementCriteria;

public class JudgingMessagesServiceImpl implements JudgingMessagesService {

	private JudgementCriteria judgementCriteria;
	private int maxProblems;

	public JudgingMessagesServiceImpl(JudgementCriteria judgementCriteria, int maxProblems) {
		super();
		this.judgementCriteria = judgementCriteria;
		this.maxProblems = maxProblems;
	}

	@Override
	public List<Contestant> judgeMessages(List<ContestantMessage> contestantMessages) {

		List<Contestant> contestantsScoreboard = new ArrayList<>();
		// Use a map to index but maintain a list to preserves the input order
		Map<Integer, Contestant> contestants = new HashMap<>();
		for (ContestantMessage message : contestantMessages) {

			Contestant contestant = contestants.get(message.getContestantId());
			if (contestant == null) {
				contestant = new Contestant(message.getContestantId(), false, maxProblems);
				contestants.put(contestant.getId(), contestant);
				contestantsScoreboard.add(contestant);
			}

			judgementCriteria.computeMessageEntry(contestant, message);
		}

		contestantsScoreboard.sort(judgementCriteria.reversed());

		return contestantsScoreboard;
	}

}
