package com.cs.reader;

public class JudgingMessagesReaderFactory {

	public JudgingMessagesReader getJudgingMessagesReader(JudgingMessagesReaderType type) {

		switch (type) {
		case SYSTEM_IN:
			return new SysInJudgingMessagesReader();
		default:
			throw new RuntimeException("Cannot find reader for " + type.name());
		}
	}

}
