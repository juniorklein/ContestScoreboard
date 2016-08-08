package com.cs.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.cs.model.Contestant;
import com.cs.model.ContestantMessage;
import com.cs.service.criteria.StandardJudgementCriteria;

public class JudgingMessagesServiceImplTest {
	
	private JudgingMessagesServiceFactory factory;

	@Before
	public void setUp() throws Exception {
		factory = new JudgingMessagesServiceFactory();
	}

	@Test
	public void simpleTest() {
		
		List<ContestantMessage> messages = new ArrayList<>();
		messages.add(new ContestantMessage(1, 2, 10, 'I'));
		messages.add(new ContestantMessage(3, 1, 11, 'C'));
		messages.add(new ContestantMessage(1, 2, 19, 'R'));
		messages.add(new ContestantMessage(1, 2, 21, 'C'));
		messages.add(new ContestantMessage(1, 1, 25, 'C'));
		
		JudgingMessagesService service = factory.getJudgingMessagesService(new StandardJudgementCriteria());
		List<Contestant> scoreBoard = service.judgeMessages(messages);
		
		assertNotNull("simpleTest must return not null scoreBoard", scoreBoard);
		assertTrue("simpleTest must return two contestants after judgment", scoreBoard.size() == 2);
		assertTrue("simpleTest first contestant must have code 1", scoreBoard.get(0).getId() == 1);
		assertTrue("simpleTest first contestant must have 2 solvedProblems", scoreBoard.get(0).getSolvedProblemsCount() == 2);
		assertTrue("simpleTest first contestant must have 66 in penaltyTime", scoreBoard.get(0).getPenaltyTime() == 66);
		assertTrue("simpleTest first contestant must have code 1", scoreBoard.get(1).getId() == 3);
		assertTrue("simpleTest first contestant must have 2 solvedProblems", scoreBoard.get(1).getSolvedProblemsCount() == 1);
		assertTrue("simpleTest first contestant must have 66 in penaltyTime", scoreBoard.get(1).getPenaltyTime() == 11);
	}
	
	@Test
	public void simpleTestWithAnUnsolvedLater() {
		
		List<ContestantMessage> messages = new ArrayList<>();
		messages.add(new ContestantMessage(1, 2, 10, 'I'));
		messages.add(new ContestantMessage(3, 1, 11, 'C'));
		messages.add(new ContestantMessage(1, 2, 19, 'R'));
		messages.add(new ContestantMessage(1, 2, 21, 'C'));
		messages.add(new ContestantMessage(1, 1, 25, 'C'));
		messages.add(new ContestantMessage(1, 2, 15, 'I'));
		
		JudgingMessagesService service = factory.getJudgingMessagesService(new StandardJudgementCriteria());
		List<Contestant> scoreBoard = service.judgeMessages(messages);
		
		assertNotNull("simpleTestWithAnUnsolvedLater must return not null scoreBoard", scoreBoard);
		assertTrue("simpleTestWithAnUnsolvedLater must return two contestants after judgment", scoreBoard.size() == 2);
		assertTrue("simpleTestWithAnUnsolvedLater first contestant must have code 1", scoreBoard.get(0).getId() == 1);
		assertTrue("simpleTestWithAnUnsolvedLater first contestant must have 2 solvedProblems", scoreBoard.get(0).getSolvedProblemsCount() == 2);
		assertTrue("simpleTestWithAnUnsolvedLater first contestant must have 66 in penaltyTime", scoreBoard.get(0).getPenaltyTime() == 66);
		assertTrue("simpleTestWithAnUnsolvedLater first contestant must have code 1", scoreBoard.get(1).getId() == 3);
		assertTrue("simpleTestWithAnUnsolvedLater first contestant must have 2 solvedProblems", scoreBoard.get(1).getSolvedProblemsCount() == 1);
		assertTrue("simpleTestWithAnUnsolvedLater first contestant must have 66 in penaltyTime", scoreBoard.get(1).getPenaltyTime() == 11);
	}
	
	@Test
	public void noOneSolvedTest() {
		
		List<ContestantMessage> messages = new ArrayList<>();
		messages.add(new ContestantMessage(1, 2, 10, 'I'));
		messages.add(new ContestantMessage(3, 1, 11, 'R'));
		messages.add(new ContestantMessage(1, 2, 19, 'R'));
		messages.add(new ContestantMessage(1, 2, 21, 'U'));
		messages.add(new ContestantMessage(1, 1, 25, 'E'));
		messages.add(new ContestantMessage(1, 2, 15, 'I'));
		
		JudgingMessagesService service = factory.getJudgingMessagesService(new StandardJudgementCriteria());
		List<Contestant> scoreBoard = service.judgeMessages(messages);
		
		assertNotNull("noOneSolvedTest must return not null scoreBoard", scoreBoard);
		assertTrue("noOneSolvedTest must return two contestants after judgment", scoreBoard.size() == 2);
		assertTrue("simpleTestWithAnUnsolvedLater first contestant must have code 1", scoreBoard.get(0).getId() == 3);
		assertTrue("simpleTestWithAnUnsolvedLater first contestant must have 2 solvedProblems", scoreBoard.get(0).getSolvedProblemsCount() == 0);
		assertTrue("simpleTestWithAnUnsolvedLater first contestant must have 66 in penaltyTime", scoreBoard.get(0).getPenaltyTime() == 0);
		assertTrue("simpleTestWithAnUnsolvedLater first contestant must have code 1", scoreBoard.get(1).getId() == 1);
		assertTrue("simpleTestWithAnUnsolvedLater first contestant must have 2 solvedProblems", scoreBoard.get(1).getSolvedProblemsCount() == 0);
		assertTrue("simpleTestWithAnUnsolvedLater first contestant must have 66 in penaltyTime", scoreBoard.get(1).getPenaltyTime() == 40);
	}

}
