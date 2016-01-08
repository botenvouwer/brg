package businessRuleGenerator.domain.businessRule;

import businessRuleGenerator.domain.Validator;
import businessRuleGenerator.domain.ValidatorException;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by melvin on 8-1-2016.
 */
public class BusinessRuleBuilder implements Validator {
    private String category;
    private String type;
    private String code;
    private String table;
    private String CRUDmode;
    private String ruleDescription; //opt
    private String typeDescription; //opt
    private String errorMessage;

    //op zijn minst 1 moet er zijn
    private ArrayList<Statement> statements = new ArrayList<Statement>();

    public BusinessRuleBuilder() {

    }

    public BusinessRuleBuilder setCategory(String category) {
        this.category = category;
        return this;
    }

    public BusinessRuleBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public BusinessRuleBuilder setCode(String code) {
        this.code = code;
        return this;
    }

    public BusinessRuleBuilder setTable(String table) {
        this.table = table;
        return this;
    }

    public BusinessRuleBuilder setCRUDmode(String CRUDmode) {
        this.CRUDmode = CRUDmode;
        return this;
    }

    public BusinessRuleBuilder setRuleDescription(String ruleDescription) {
        this.ruleDescription = ruleDescription;
        return this;
    }

    public BusinessRuleBuilder setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
        return this;
    }

    public BusinessRuleBuilder setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public BusinessRuleBuilder setStatements(ArrayList<Statement> statements) {
        this.statements = statements;
        return this;
    }

    @Override
    public void validate() throws ValidatorException {
        Map<String, String> attributes = new HashMap<String, String>();
        attributes.put("category", category);
        attributes.put("type", type);
        attributes.put("code", code);
        attributes.put("table", table);
        attributes.put("CRUDmode", CRUDmode);

        for(Map.Entry<String, String> attribute : attributes.entrySet()) if(attribute.getValue() == null) throw new ValidatorException("A valid businessrule requires a " + attribute.getKey());
        if(statements.size() == 0) throw new ValidatorException("A valid businessrule requires atleast 1 statement.");

        Set<Integer> set = new HashSet<Integer>();
        for(Statement s : statements) set.add(s.order);
        if(set.size() < statements.size()) throw new ValidatorException("Found duplicates in statement-order values.\nA valid businessrule requires statements with unique order-values");
    }

    public BusinessRule build() throws ValidatorException {
        validate();
        return new BusinessRule(category, type, code, table, CRUDmode, ruleDescription, typeDescription, statements, errorMessage);
    }
}
