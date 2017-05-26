public class JavaFitchRunner_white extends JavaFitch{

	public static void main(String[] args) {
		System.out.println("Starting Java Fitch Runner...");

		JavaFitchUnitTest suite_Task1 = new tests_white_Task1("Task 1 white box testing");
		addSuite(suite_Task1);
		runAllTests();
	}

}
