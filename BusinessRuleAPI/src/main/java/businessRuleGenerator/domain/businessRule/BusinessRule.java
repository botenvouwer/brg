package businessRuleGenerator.domain.businessRule;

import businessRuleGenerator.domain.Validator;
import businessRuleGenerator.domain.ValidatorException;

import java.util.*;

/**
 * Created by melvin on 18-12-2015.
 */
public class BusinessRule implements Validator {
    public String category;
    public String type;
    public String code;
    public String table;
    public String CRUDmode;
    public String ruleDescription; //opt
    public String typeDescription; //opt
    public String errorMessage;

    //op zijn minst 1 moet er zijn
    public ArrayList<Statement> statements = new ArrayList<Statement>();

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
    }

    public BusinessRule() {

    }

    public ArrayList<Statement> getStatements() {
        return statements;
    }

    public void setStatements(ArrayList<Statement> statements) {
        this.statements = statements;
    }

    public void sortStatements() {
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

    @Override
    public void validate() throws ValidatorException {

        sortStatements();

        Map<String, String> attributes = new HashMap<String, String>();
        attributes.put("category", category);
        attributes.put("type", type);
        attributes.put("code", code);
        attributes.put("table", table);
        attributes.put("CRUDmode", CRUDmode);

        for(Map.Entry<String, String> attribute : attributes.entrySet()) {
            if(attribute.getValue() == null) {
                throw new ValidatorException("A valid businessrule requires a " + attribute.getKey());
            }
        }
        if(statements.size() == 0) {
            throw new ValidatorException("A valid businessrule requires atleast 1 statement.");
        }

        Set<Integer> set = new HashSet<Integer>();
        int order = 0;
        for(Statement statement : statements){
            statement.validate();

            if(statements.size() > 1 && statement.logicalOperator == null){
                throw new ValidatorException("When rule has multiple statements there must be a logicalOperator to compare. Problem found at statement with " + order + ".");
            }

            set.add(statement.order);
            order++;
        }

        if(set.size() < statements.size()) {
            throw new ValidatorException("Found duplicates in statement-order values.\nA valid businessrule requires statements with unique order-values");
        }

    }
}
