package com.cs.model;

public class Contestant {

	private int id;
	private int solvedProblemsCount;
	private int penaltyTime;
	private boolean participating;
	private boolean[] solvedProblems;

	public Contestant(int id, boolean participating, int maxProblemsCount) {
		super();
		this.id = id;
		this.participating = participating;
		solvedProblems = new boolean[maxProblemsCount];
	}

	public void registerSolvedProblem(int problem) {
		if (problem >= solvedProblems.length) {
			throw new RuntimeException("The problem number: " + problem + " is out of the specified max problems. Contestant: " + id);
		}
		solvedProblems[problem - 1] = true;
		solvedProblemsCount += 1;
	}

	public void incrementPenaltyTime(int amount) {
		penaltyTime += amount;
	}

	public boolean verifySolvedProblem(int problem) {
		if (problem >= solvedProblems.length) {
			throw new RuntimeException("The problem number: " + problem + " is out of the specified max problems. Contestant: " + id);
		}
		return solvedProblems[problem - 1];
	}

	public void setParticipating(boolean participating) {
		this.participating = participating;
	}

	public int getId() {
		return id;
	}

	public int getPenaltyTime() {
		return penaltyTime;
	}

	public boolean isParticipating() {
		return participating;
	}

	public int getSolvedProblemsCount() {
		return solvedProblemsCount;
	}

}
