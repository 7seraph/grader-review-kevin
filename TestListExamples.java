import static org.junit.Assert.*;
import org.junit.*;
import java.util.Arrays;
import java.util.List;

class IsA implements StringChecker {
  public boolean checkString(String s) {
    return s.equalsIgnoreCase("a");
  }
}

public class TestListExamples {
  @Test(timeout = 500)
  public void testMergeRightEnd() {
    List<String> left = Arrays.asList("a", "b", "c");
    List<String> right = Arrays.asList("a", "d");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("a", "a", "b", "c", "d");
    assertEquals(expected, merged);
  }

  @Test(timeout=500)
  public void testMergeEmpty() {
    List<String> a  = Arrays.asList();
    List<String> b = Arrays.asList();
    List<String> merged = ListExamples.merge(a, b);
    List<String> expected = Arrays.asList();
    assertEquals(expected, merged);
  }

  @Test(timeout=500)
  public void testFilter() {
	List<String> a =  Arrays.asList("a", "b", "c");
	List<String> b = Arrays.asList("b", "c", "d");
	List<String> aExpected = Arrays.asList("a");
	List<String> bExpected = Arrays.asList();
	List<String> c = ListExamples.filter(a, new IsA());
	List<String> d = ListExamples.filter(b, new IsA());
	assertEquals(aExpected, c);
	assertEquals(bExpected, d);
  }	

  @Test(timeout=500)
  public void testFilerEmpty() {
    List<String> a = Arrays.asList();
    List<String> aExpected = ListExamples.filter(a, new IsA());
    assertEquals(aExpected, a);
    
  }
}
