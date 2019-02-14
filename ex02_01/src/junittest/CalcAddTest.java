package junittest;

import static org.junit.Assert.*;
import org.junit.*;

public class CalcAddTest
{
	@Test
	public void testCase()
	{
	    CalcAdd add = new CalcAdd();
	    int result = add.add(1, 2);
	    
	    // 計算の結果
	    assertEquals(result, 3);
	  }
}
