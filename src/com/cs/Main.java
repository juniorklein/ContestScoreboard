package com.cs;

import java.util.List;

import com.cs.model.Contestant;
import com.cs.model.ContestantMessage;
import com.cs.reader.JudgingMessagesReader;
import com.cs.reader.JudgingMessagesReaderFactory;
import com.cs.reader.JudgingMessagesReaderType;
import com.cs.service.JudgingMessagesService;
import com.cs.service.JudgingMessagesServiceFactory;
import com.cs.service.criteria.StandardJudgementCriteria;

public class Main {

	private JudgingMessagesReaderFactory judgingMessagesReaderFactory;
	private JudgingMessagesServiceFactory judgingMessagesServiceFactory;

	public Main(JudgingMessagesReaderFactory judgingMessagesReaderFactory, JudgingMessagesServiceFactory judgingMessagesServiceFactory) {
		super();
		assert (judgingMessagesReaderFactory != null);
		assert (judgingMessagesServiceFactory != null);
		this.judgingMessagesReaderFactory = judgingMessagesReaderFactory;
		this.judgingMessagesServiceFactory = judgingMessagesServiceFactory;
	}

	public static void main(String[] args) {
		new Main(new JudgingMessagesReaderFactory(), new JudgingMessagesServiceFactory()).start();
	}

	public void start() {
		JudgingMessagesReader reader = judgingMessagesReaderFactory.getJudgingMessagesReader(JudgingMessagesReaderType.SYSTEM_IN);
		JudgingMessagesService service = judgingMessagesServiceFactory.getJudgingMessagesService(new StandardJudgementCriteria());

		System.out.println("Start reading judgment queue");

		List<List<ContestantMessage>> messagesQueue = reader.readJudgingMessages();
		if (messagesQueue == null) {
			System.out.println("Finishing after an error in input messages. Try again.");
			return;
		}
		
		int i = 1;
		for (List<ContestantMessage> messages : messagesQueue) {
			List<Contestant> contestantScoreboard = service.judgeMessages(messages);
			System.out.println("Printing output for the case: " + i++);
			for (Contestant contestant : contestantScoreboard) {
				System.out.println(contestant.getId() + " " + contestant.getSolvedProblemsCount() + " " + contestant.getPenaltyTime());
			}
		}
	}

}
