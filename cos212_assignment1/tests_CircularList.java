public class tests_CircularList extends UnitTest{

	public boolean run() {

		boolean result = true;
		result = result & test_addToFront_toString("testing CircularList::addToFront and toString()");
		result = result & test_deleteFromBack("testing CircularList::deleteFromBack");
		return result;
	}

	public boolean test_addToFront_toString(String title) {
		printHeader(title);
		boolean result = true;

		printSubTest("Adding negative elements");
		result = result & assertCustom(true, true);
		printSubTestFooter(result);

		printSubTest("Adding positive elements");
		result = result & assertCustom(false, false);
		printSubTestFooter(result);

		printFooter(title, result);

		return result;
	}

	public boolean test_deleteFromBack(String title) {
		printHeader(title);
		boolean result = true;
		boolean testResult = true;

		printSubTest("when Empty");
		testResult = assertCustom(true, true);
		result = result & testResult;
		printSubTestFooter(testResult);

		printSubTest("When full");
		testResult = assertCustom(true, true);
		result = result & testResult;
		printSubTestFooter(testResult);

		printFooter(title, result);

		return result;
	}
}
