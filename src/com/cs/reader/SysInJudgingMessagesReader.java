package com.cs.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.cs.model.ContestantMessage;

public class SysInJudgingMessagesReader implements JudgingMessagesReader {

	/*
	 * Read judging queue from System.in and generate a list of messages to process. Returns null when user doesn't want continue.
	 * <see>JudgingMessagesReader</see>
	 * 
	 */
	@Override
	public List<List<ContestantMessage>> readJudgingMessages() {

		int judgingQueueCount = 0, currentJudgingQueue = 0;

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter a judging queue sequence or a double blank line to quit.");

		try {

			System.out.print("> ");
			String input = bufferedReader.readLine();
			input = input.trim();

			try {
				judgingQueueCount = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println("The input size is wrong in the format of judging queue.");
				return null;
			}

			List<List<ContestantMessage>> judgingMessagesQueue = new ArrayList<>(judgingQueueCount);
			List<ContestantMessage> judgingMessages = null;

			boolean lastLineBlankLine = false;

			while (true) {

				System.out.print("> ");
				input = bufferedReader.readLine();
				input = input.trim();

				// Test for a double blank lines to exit or a complete judging queue cases to break
				if ("".equals(input)) {
					if (lastLineBlankLine) {
						return null;
					}
					
					currentJudgingQueue++;
					
					if(currentJudgingQueue > judgingQueueCount){
						return judgingMessagesQueue;
					}
					
					judgingMessages = new ArrayList<>();
					judgingMessagesQueue.add(judgingMessages);

					lastLineBlankLine = true;
					continue;
				}
				
				if (judgingMessages == null) {
					System.out.println("The input line is wrong in the format of judging queue.");
					return null;
				}

				lastLineBlankLine = false;

				ContestantMessage contestantMessage = parseInputMessage(input);
				if (contestantMessage == null) {
					return null;
				}

				judgingMessages.add(contestantMessage);

			}
			
		} catch (IOException e) {
			System.out.println("Error when reading user input: " + e.getMessage());
			return null;
		} finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				System.out.println("Error closing input buffered reader.");
			}
		}
	}

	/*
	 * Parse a input line into ContestantMessage. If a input is in wrong format, prints a error and return a null value.
	 */
	private ContestantMessage parseInputMessage(String input) {
		String[] lineComponents = input.split(" ");
		if (lineComponents.length != 4) {
			System.out.println("The input line is wrong in the format of judging queue.");
			return null;
		}

		int contestantId, problem, time;
		char status;

		try {
			contestantId = Integer.parseInt(lineComponents[0]);
			problem = Integer.parseInt(lineComponents[1]);
			time = Integer.parseInt(lineComponents[2]);
		} catch (NumberFormatException e) {
			System.out.println("The input line is wrong in the format of judging queue.");
			return null;
		}

		if (lineComponents[3].length() != 1) {
			System.out.println("The input line is wrong in the format of judging queue.");
			return null;
		}

		status = Character.toUpperCase(lineComponents[3].charAt(0));
		if (status != 'C' && status != 'I' && status != 'R' && status != 'U' && status != 'E') {
			System.out.println("The input line is wrong in the format of judging queue.");
			return null;
		}

		return new ContestantMessage(contestantId, problem, time, status);
	}

}
