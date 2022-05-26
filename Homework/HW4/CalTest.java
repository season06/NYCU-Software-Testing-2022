import org.junit.Assert;
import org.junit.Test;

public class CalTest {
    @Test
    public void test1() {
        Assert.assertEquals(364, Cal.cal(1, 1, 12, 31, 2021));
    }

    @Test
    public void test2() {
        // leap year
        Assert.assertEquals(365, Cal.cal(1, 1, 12, 31, 2020));
    }

    @Test
    public void test3() {
        // same month
        Assert.assertEquals(30, Cal.cal(1, 1, 1, 31, 2020));
    }

    @Test
    public void test4() {
        // zero day
        Assert.assertEquals(0, Cal.cal(1, 1, 1, 1, 2020));
    }

    @Test
    public void test5() {
        // month1 > month2
        Assert.assertEquals(29, Cal.cal(8, 8, 7, 7, 2020));
    }

    @Test
    public void test6() {
        Assert.assertEquals(89, Cal.cal(1, 1, 3, 31, 1700));
    }

    @Test
    public void test7() {
        // (m4 == 0) (m100 == 0) (m400 == 0)
        Assert.assertEquals(90, Cal.cal(2, 1, 5, 1, 1600));
    }

    @Test
    public void test8() {
        // (m4 < 0) (m100 < 0) (m400 < 0)
        Assert.assertEquals(89, Cal.cal(2, 1, 5, 1, -1));
    }

    @Test
    public void test9() {
        // (m4 == 0) (m100 < 0) (m400 < 0)
        Assert.assertEquals(89, Cal.cal(2, 1, 5, 1, -4));
    }

    @Test
    public void test10() {
        // (m4 == 0) (m100 == 0) (m400 < 0)
        Assert.assertEquals(89, Cal.cal(2, 1, 5, 1, -500));
    }
}