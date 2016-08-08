package com.cs.service.criteria;

import com.cs.model.Contestant;
import com.cs.model.ContestantMessage;

/**
 * A Judgemt criteria that validate only messages Corrects and Incorrects. The incorrect message increase the penalty time in 20 if the problem isn't
 * solved previously. The correct message increases only the spent time to the solution. In the comparassion of two contestants the first criteria is
 * the solved problemens count and to tiebreaker the criteria is the penalty time.
 * 
 * @author marioklein
 *
 */
public class StandardJudgementCriteria implements JudgementCriteria {

	@Override
	public void computeMessageEntry(Contestant contestant, ContestantMessage message) {
		// if the contestant have a message, he is participating
		contestant.setParticipating(true);

		// The problem is previously solved
		if (contestant.verifySolvedProblem(message.getProblem())) {
			return;
		}

		if (message.getStatus() == 'I') {
			contestant.incrementPenaltyTime(20);
		}

		if (message.getStatus() == 'C') {
			contestant.incrementPenaltyTime(message.getTime());
			contestant.registerSolvedProblem(message.getProblem());
		}
	}

	@Override
	public int compare(Contestant c1, Contestant c2) {

		if (c1.getSolvedProblemsCount() > c2.getSolvedProblemsCount()) {
			return 1;
		} else if (c1.getSolvedProblemsCount() < c2.getSolvedProblemsCount()) {
			return -1;
		}

		if (c1.getPenaltyTime() < c2.getPenaltyTime()) {
			return 1;
		} else if (c1.getPenaltyTime() > c2.getPenaltyTime()) {
			return -1;
		}

		return 0;
	}

}
