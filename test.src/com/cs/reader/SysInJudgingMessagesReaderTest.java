package com.cs.reader;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.cs.model.ContestantMessage;

public class SysInJudgingMessagesReaderTest {

	private JudgingMessagesReaderFactory factory;

	@Before
	public void setUp() throws Exception {
		factory = new JudgingMessagesReaderFactory();
	}

	// @formatter:off
	private static final String VALID_ONE_CASE_INPUT = "1\n" +
			"\n" +
			"1 2 10 I\n" +
			"3 1 11 C\n" +
			"1 2 19 R\n" +
			"1 2 21 C\n" +
			"1 1 25 C\n" +
			"\n";
	// @formatter:on

	@Test
	public void testValidOneCaseInput() {
		System.setIn(new ByteArrayInputStream(VALID_ONE_CASE_INPUT.getBytes()));
		JudgingMessagesReader reader = factory.getJudgingMessagesReader(JudgingMessagesReaderType.SYSTEM_IN);

		List<List<ContestantMessage>> messagesQueue = reader.readJudgingMessages();
		assertNotNull("For a VALID_ONE_CASE_INPUT the queue cases must be not null.", messagesQueue);
		assertTrue("For a VALID_ONE_CASE_INPUT the size of queue cases is one.", messagesQueue.size() == 1);
		assertTrue("For a VALID_ONE_CASE_INPUT the size of messages is five.", messagesQueue.get(0).size() == 5);

		System.setIn(System.in);

	}

	// @formatter:off
	private static final String VALID_TWO_CASES_INPUT = "2\n" +
			"\n" +
			"1 2 10 I\n" +
			"3 1 11 C\n" +
			"1 2 19 R\n" +
			"1 2 21 C\n" +
			"1 1 25 C\n" +
			"\n" +
			"1 2 10 I\n" +
			"3 1 11 C\n" +
			"1 2 19 R\n" +
			"\n";
	// @formatter:on

	@Test
	public void testValidTwoCasesInput() {
		System.setIn(new ByteArrayInputStream(VALID_TWO_CASES_INPUT.getBytes()));
		JudgingMessagesReader reader = factory.getJudgingMessagesReader(JudgingMessagesReaderType.SYSTEM_IN);

		List<List<ContestantMessage>> messagesQueue = reader.readJudgingMessages();
		assertNotNull("For a VALID_ONE_CASE_INPUT the queue cases must be not null.", messagesQueue);
		assertTrue("For a VALID_ONE_CASE_INPUT the size of queue cases is one.", messagesQueue.size() == 2);
		assertTrue("For a VALID_ONE_CASE_INPUT the size of first case messages is five.", messagesQueue.get(0).size() == 5);
		assertTrue("For a VALID_ONE_CASE_INPUT the size of second case messages is three.", messagesQueue.get(1).size() == 3);

		System.setIn(System.in);

	}

	// @formatter:off
	private static final String TWO_BLANK_LINES_INPUT = "1\n" +
			"\n" +
			"\n" +
			"1 2 10 I\n" +
			"3 1 11 C\n" +
			"1 2 19 R\n" +
			"1 2 21 C\n" +
			"1 1 25 C\n" +
			"\n";
	// @formatter:on

	@Test
	public void testTwoBlankLinesInput() {
		System.setIn(new ByteArrayInputStream(TWO_BLANK_LINES_INPUT.getBytes()));
		JudgingMessagesReader reader = factory.getJudgingMessagesReader(JudgingMessagesReaderType.SYSTEM_IN);

		List<List<ContestantMessage>> messagesQueue = reader.readJudgingMessages();
		assertTrue("For a TWO_BLANK_LINES_INPUT the queue cases must be null.", messagesQueue == null);

		System.setIn(System.in);
	}

}
