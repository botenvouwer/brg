package businessRuleGenerator.domain;

/**
 * Created by melvin on 18-12-2015.
 */
public class TestClass {
    private int haha;

    public TestClass(int haha) {
        this.haha = haha;
    }

    public int getHaha() {
        return haha;
    }

    public void setHaha(int haha) {
        this.haha = haha;
    }

    @Override
    public String toString() {
        return "TestClass [haha=" + haha + "]";
    }
}
