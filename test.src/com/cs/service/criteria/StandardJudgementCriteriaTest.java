package com.cs.service.criteria;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cs.model.Contestant;
import com.cs.model.ContestantMessage;

public class StandardJudgementCriteriaTest {
	
	private StandardJudgementCriteria criteria;

	@Before
	public void setUp() throws Exception {
		criteria = new StandardJudgementCriteria();
	}

	@Test
	public void simpleTest() {
		
		Contestant contestant = new Contestant(1, false, 9);
		
		// Incorrect increase penalty time in 20 
		ContestantMessage message = new ContestantMessage(1, 2, 10, 'I');
		criteria.computeMessageEntry(contestant, message);
		assertTrue("After 1st compute in simpleTest penaltyTime must be 20", contestant.getPenaltyTime() == 20);
		assertTrue("After 1st compute in simpleTest solvedProblems must be 0", contestant.getSolvedProblemsCount() == 0);
		
		// Clarification request doesn't increase penalty time
		message = new ContestantMessage(3, 1, 11, 'R');
		criteria.computeMessageEntry(contestant, message);
		assertTrue("After 2nd compute in simpleTest penaltyTime must be 20", contestant.getPenaltyTime() == 20);
		assertTrue("After 2nd compute in simpleTest solvedProblems must be 0", contestant.getSolvedProblemsCount() == 0);
		
		// Undjuged doesn't increase penalty time
		message = new ContestantMessage(1, 2, 21, 'U');
		criteria.computeMessageEntry(contestant, message);
		assertTrue("After 3rd compute in simpleTest penaltyTime must be 20", contestant.getPenaltyTime() == 20);
		assertTrue("After 3rd compute in simpleTest solvedProblems must be 0", contestant.getSolvedProblemsCount() == 0);
		
		// Erroneous submission doesn't increase penalty time
		message = new ContestantMessage(1, 1, 25, 'E');
		criteria.computeMessageEntry(contestant, message);
		assertTrue("After 4th compute in simpleTest penaltyTime must be 20", contestant.getPenaltyTime() == 20);
		assertTrue("After 4th compute in simpleTest solvedProblems must be 0", contestant.getSolvedProblemsCount() == 0);
		
		// Correct increase penalty time only by the time spent (10) and increase solved problems count
		message = new ContestantMessage(1, 2, 10, 'C');
		criteria.computeMessageEntry(contestant, message);
		assertTrue("After 5th compute in simpleTest penaltyTime must be 30", contestant.getPenaltyTime() == 30);
		assertTrue("After 5th compute in simpleTest solvedProblems must be 1", contestant.getSolvedProblemsCount() == 1);
		
		// Incorrect of a previously solved problem doesn't increase anything
		message = new ContestantMessage(1, 2, 19, 'I');
		criteria.computeMessageEntry(contestant, message);
		assertTrue("After 6th compute in simpleTest penaltyTime must be 30", contestant.getPenaltyTime() == 30);
		assertTrue("After 6th compute in simpleTest solvedProblems must be 1", contestant.getSolvedProblemsCount() == 1);
	}

}
