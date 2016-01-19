package businessRuleGenerator.domain.database;

import java.util.ArrayList;

/**
 * Created by william on 19-Jan-16.
 */
public class Table {

    public String name;
    public ArrayList<Column> columns;

    public Table(String name) {
        this.name = name;
    }
}
