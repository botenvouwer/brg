package businessRuleGenerator.domain.businessRule;

import businessRuleGenerator.domain.Validator;
import businessRuleGenerator.domain.ValidatorException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
    public String errorMessage;

    //op zijn minst 1 moet er zijn
    private ArrayList<Statement> statements = new ArrayList<Statement>();

    public BusinessRule(String category, String type, String code, String table, String CRUDmode, String ruleDescription, String typeDescription, ArrayList<Statement> statements, String errorMessage) {
        this.category = category;
        this.type = type;
        this.code = code;
        this.table = table;
        this.CRUDmode = CRUDmode;
        this.ruleDescription = ruleDescription;
        this.typeDescription = typeDescription;
        this.statements = statements;
        this.errorMessage = errorMessage;
        sortStatements();
    }

    public BusinessRule() {

    }

    public ArrayList<Statement> getStatements() {
        return statements;
    }

    private void sortStatements() {
        Collections.sort(statements, new Comparator<Statement>() {
            @Override public int compare(Statement s1, Statement s2) {
                return s1.order - s2.order; // Ascending
            }
        });
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
