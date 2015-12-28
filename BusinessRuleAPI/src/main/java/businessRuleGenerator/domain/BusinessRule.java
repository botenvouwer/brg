package businessRuleGenerator.domain;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by melvin on 18-12-2015.
 */
public class BusinessRule {
    public String category;
    public String type;
    public String code;
    public String table;
    public String CRUDmode;
    public String ruleDescription;
    public String typeDescription;
    ArrayList<Statement> statements;

    public BusinessRule() {

    }

    public String toString(){
        return "boeh";
    }
}
