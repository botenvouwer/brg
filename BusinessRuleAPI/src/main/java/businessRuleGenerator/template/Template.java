package businessRuleGenerator.template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by william on 31/12/2015.
 */
public abstract class Template {

    public String name;
    public String body;
    public String statement;
    public Map<String, String> comparisonOperator;
    public Map<String, String> logicalOperator;

    public Template(Map<String, String> templateData, String name){
        this.name = name;
        body = templateData.get("body");
        statement = templateData.get("statement");

        comparisonOperator = loadList("comparisonOperators");
        logicalOperator = loadList("logicalOperators");

    }

    protected Map<String, String> loadList(String templateFile){
        Map<String, String> list = new HashMap<String, String>();
        String[] splitList = templateFile.split("\n");

        for(String element : splitList){
            String[] splitElement = element.split(" ");
            list.put(splitElement[0], splitElement[1]);
        }

        return list;
    }

}
