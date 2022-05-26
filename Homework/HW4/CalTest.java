import org.junit.Assert;
import org.junit.Test;

public class CalTest {
    @Test
    public void test1() {
        Assert.assertEquals(365, Cal.cal(1, 1, 12, 31, 2012));
    }
    
    @Test
    public void test2() {
        Assert.assertEquals(90, Cal.cal(1, 1, 3, 31, 396));
    }
}