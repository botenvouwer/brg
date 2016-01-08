package businessRuleGenerator.domain.businessRule;

import java.util.ArrayList;

/**
 * Created by melvin on 8-1-2016.
 */
public class BusinessRuleBuilder {
    private String category;
    private String type;
    private String code;
    private String table;
    private String CRUDmode;
    private String ruleDescription; //opt
    private String typeDescription; //opt

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

    public BusinessRuleBuilder setStatements(ArrayList<Statement> statements) {
        this.statements = statements;
        return this;
    }
}
