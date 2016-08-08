# Contestant Scoreboard

The problem
-----------

Want to compete in the ACM ICPC? Then you had better know how to keep score! Contestants are ranked first by the number of problems solved (the more the better), then by decreasing amounts of penalty time. If two or more contestants are tied in both problems solved and penalty time, they are displayed in order of increasing team numbers.


A problem is considered solved by a contestant if any of the submissions for that problem was judged correct. Penalty time is computed as the number of minutes it took until the first correct submission for a problem was received, plus 20 minutes for each incorrect submission prior to the correct solution. Unsolved problems incur no time penalties.

**Input**
The input begins with a single positive integer on a line by itself indicating the number of cases, each described as below. This line is followed by a blank line. There is also a blank line between two consecutive inputs.

The input consists of a snapshot of the judging queue, containing entries from some or all of contestants 1 through 100 solving problems 1 through 9. Each line of input consists of three numbers and a letter in the format contestant problem time L, where L can be C, I, R, U, or E. These stand for Correct, Incorrect, clarification Request, Unjudged, and Erroneous submission. The last three cases do not affect scoring.

The lines of input appear in the order in which the submissions were received.

**Output**
The output for each test case will consist of a scoreboard, sorted by the criteria described above. Each line of output will contain a contestant number, the number of problems solved by the contestant and the total time penalty accumulated by the contestant. Since not all contestants are actually participating, only display those contestants who have made a submission.

Explanation of the implemetantion
-----------

First of all, I have implemented an interface for read the inputs. This interface is implemented by a concrete class that reads from the standard input (System.in).
Because we can have more implementations that reads the input from other sources (like a file in a folder), I have implemented a factory that receive the type of reader the caller needs.

After, I have implemented a service that judge the input messages for one case. This service needs a Criteria (implemented separately) to judge the messages and compute the score (penalty time and solved problems count) in the class that represents a Contestant. This service can have more implementations of judgement, then I have created a interface for that and a class that implements a simple judgment case. Again I have created a factory to encapsulate the instantiation process of the sevice concrete class.
And than I have created a Main class to simulate the entire process like the requested in problem description.

In the test.src source folder I have created a test class for the main processes described above (reader, criteria and jugment).