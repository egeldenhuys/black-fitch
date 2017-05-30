public class JavaFitchRunner_task2 extends JavaFitch{

	public static void main(String[] args) {
		System.out.println("Starting Java Fitch " + VERSION + "...");

		JavaFitchUnitTest suite_Task2 = new tests_Task2("Task 2");
		addSuite(suite_Task2);
		runAllTests();

		System.exit(testsPassed);
	}

}
