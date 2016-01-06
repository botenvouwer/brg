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
    public ArrayList<Statement> statements = new ArrayList<Statement>();

    public BusinessRule(String category, String type, String code, String table, String CRUDmode, String ruleDescription, String typeDescription, ArrayList<Statement> statements) {
        this.category = category;
        this.type = type;
        this.code = code;
        this.table = table;
        this.CRUDmode = CRUDmode;
        this.ruleDescription = ruleDescription;
        this.typeDescription = typeDescription;
        this.statements = statements;
    }

    public BusinessRule() {

    }

    public void addStatements(ArrayList<Statement> statements) {
        this.statements = statements;
    }

    public ArrayList<Statement> getStatements() {
        return statements;
    }

    public String toString(){
        return "boeh";
    }
}
