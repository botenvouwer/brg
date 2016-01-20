package businessRuleGenerator.domain.database;

/**
 * Created by william on 19-Jan-16.
 */
public class Column {

    public String name;
    public String dataType;

    public Column(String dataType, String name) {
        this.dataType = dataType;
        this.name = name;
    }

    public Column() {
    }
}
