package businessRuleGenerator.generator;

import businessRuleGenerator.domain.businessRule.BusinessRule;
import businessRuleGenerator.domain.businessRule.Statement;
import businessRuleGenerator.domain.template.DefaultTemplate;

import java.util.*;

/**
 * Created by william on 20-Jan-16.
 */
public class DefaultGenerator extends BusinessRuleGenerator{

    protected DefaultTemplate template;
    private String tableName;

    public DefaultGenerator(DefaultTemplate template) {
        super(template);
        this.template = template;
    }

    @Override
    protected String buildBody(String tableName, ArrayList<BusinessRule> rules) {

        String tableNameCamelCase = Character.toUpperCase(tableName.charAt(0)) + tableName.substring(1);
        this.tableName = tableName.toLowerCase();

        String code = template.body;
        code = code.replace("{$table_name}", this.tableName);
        code = code.replace("{$validator_name}", "Validate"+tableNameCamelCase);

        String ruleCode = "";
        for(BusinessRule rule : rules){
            ruleCode += buildRule(rule);
            ruleCode += "\n\n";
        }

        code = code.replace("{$body}", ruleCode);

        return code;
    }

    @Override
    protected String buildRule(BusinessRule rule){
        String ruleBody = template.statement;

        ruleBody = ruleBody.replace("{$category}", rule.category);
        ruleBody = ruleBody.replace("{$typeDiscription}", rule.type + (rule.typeDescription == null ? "" : " ("+rule.typeDescription+")"));
        ruleBody = ruleBody.replace("{$ruleDiscription}", (rule.ruleDescription == null ? "No description" : rule.ruleDescription));
        ruleBody = ruleBody.replace("{$crud}", rule.CRUDmode + " - CRUD rules are not supported in default generator!");

        if(rule.errorMessage == null){
            rule.errorMessage = "Business rule violation on table: "+rule.table+" ???";
        }

        ruleBody = ruleBody.replace("{$error}", rule.errorMessage);

        ArrayList<String> statementCodes = new ArrayList<>();
        for(Statement statement : rule.getStatements()){
            statementCodes.add(buildStatements(statement));
        }

        ruleBody = ruleBody.replace("{$statements}", String.join(" ", statementCodes));

        return ruleBody;
    }

    @Override
    protected String buildStatements(Statement statement) {
        String statementCode = "";

        statementCode += buildValue(tableName +template.languageOperator.get("Chain")+statement.attribute, "var");
        statementCode += " ";
        statementCode += template.comparisonOperator.get(statement.comparisonOperator);
        statementCode += " ";

        if (statement.dynamicAttribute != null){
            //assume that dynamicAttribute must be used
            statementCode += buildValue(tableName +template.languageOperator.get("Chain")+statement.dynamicAttribute.attribute, "var");
        }
        else{
            //assume that static attribute must be used
            statementCode += buildValue(statement.staticAttribute.value, statement.staticAttribute.dataType);
        }

        if(statement.logicalOperator != null && !statement.logicalOperator.equals("")){
            statementCode += " ";
            statementCode += template.logicalOperator.get(statement.logicalOperator);
        }

        return statementCode;
    }

}
