package com.cs.model;

public class ContestantMessage {

	private int contestantId;
	private int problem;
	private int time;
	private char status;

	public ContestantMessage(int contestantId, int problem, int time, char status) {
		super();
		this.contestantId = contestantId;
		this.problem = problem;
		this.time = time;
		this.status = status;
	}

	public int getContestantId() {
		return contestantId;
	}

	public int getProblem() {
		return problem;
	}

	public int getTime() {
		return time;
	}

	public char getStatus() {
		return status;
	}

}
