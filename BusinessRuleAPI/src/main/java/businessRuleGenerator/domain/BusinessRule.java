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
    public String ruleDescription; //opt
    public String typeDescription; //opt

    //op zijn minst 1 moet er zijn
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

    @Override
    public String toString() {
        return "BusinessRule{" +
                "category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", code='" + code + '\'' +
                ", table='" + table + '\'' +
                ", CRUDmode='" + CRUDmode + '\'' +
                ", ruleDescription='" + ruleDescription + '\'' +
                ", typeDescription='" + typeDescription + '\'' +
                ", statements=" + statements +
                '}';
    }
}
