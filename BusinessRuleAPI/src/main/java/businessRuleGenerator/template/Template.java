package businessRuleGenerator.template;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by william on 31/12/2015.
 */
public abstract class Template {

    public String name;
    public String body;
    public String statement;
    public ArrayList<String> comparisonOperator;
    public ArrayList<String> logicalOperator;

    public Template(Map<String, String> templateData, String name){
        this.name = name;
        body = templateData.get("body");
        statement = templateData.get("statement");
    }

}
