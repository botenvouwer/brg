package businessRuleGenerator.generator;

import businessRuleGenerator.domain.businessRule.BusinessRule;
import businessRuleGenerator.domain.businessRule.Statement;
import businessRuleGenerator.domain.template.PLSQLTemplate;

import java.util.*;

/**
 * Created by melvin on 28-12-2015.
 */
public class PLSQLGenerator extends BusinessRuleGenerator {

    protected PLSQLTemplate template;

    public PLSQLGenerator(PLSQLTemplate template) {
        super(template);
        this.template = template;
    }

    @Override
    protected String buildBody(String tableName, ArrayList<BusinessRule> rules) {

        String code = template.body;

        code = code.replace("{$table_name}", tableName);
        code = code.replace("{$trigger_name}", "BRG_TRIGGER_"+tableName);

        Set<String> CRUDModes = new HashSet<>();
        for(BusinessRule rule : rules){

            for (int i = 0; i < rule.CRUDmode.length(); i++){
                char c = rule.CRUDmode.charAt(i);
                CRUDModes.add(template.CRUDMode.get(c+"_Header"));
            }

            if(CRUDModes.size() > 3){
                break;
            }
        }

        String CRUDHeader = String.join(" "+template.logicalOperator.get("OR")+" ", CRUDModes);
        code = code.replace("{$CRUDMode}", CRUDHeader);

        Map<String, ArrayList<BusinessRule>> rulesByCRUDMode = sortRulesByCRUDMode(rules);

        //generate rules for every crud mode
        String ruleCode = "";
        for(Map.Entry<String, ArrayList<BusinessRule>> entry : rulesByCRUDMode.entrySet()){
            ruleCode += buildCRUDStatement(entry.getKey(), entry.getValue());
            ruleCode += "\n\n";
        }

        code = code.replace("{$body}", ruleCode);

        return code;
    }

    protected Map<String, ArrayList<BusinessRule>> sortRulesByCRUDMode(ArrayList<BusinessRule> rules) {
        Map<String, ArrayList<BusinessRule>> rulesByCRUDMode = new HashMap<String, ArrayList<BusinessRule>>();

        String previous = "";
        for(BusinessRule rule : rules){

            if(!rulesByCRUDMode.containsKey(rule.CRUDmode)){
                rulesByCRUDMode.put(rule.CRUDmode, new ArrayList<BusinessRule>());
            }

            ArrayList<BusinessRule> list = null;
            if(!previous.equals(rule.CRUDmode)){
                list = rulesByCRUDMode.get(rule.CRUDmode);
            }

            list.add(rule);

        }
        return rulesByCRUDMode;
    }

    protected String buildCRUDStatement(String CRUDMode, ArrayList<BusinessRule> rules){

        Set<String> CRUDModes = new HashSet<>();
        for (int i = 0; i < CRUDMode.length(); i++){
            char c = CRUDMode.charAt(i);
            CRUDModes.add(template.CRUDMode.get(c+"_Body"));
        }

        String statement = String.join(" "+template.logicalOperator.get("OR")+" ", CRUDModes);
        statement = template.CRUDStatement.replace("{$CRUDMode}", statement);

        String statementBody = "";
        for(BusinessRule rule : rules){
            statementBody += buildRule(rule);
            statementBody += "\n\n";

        }

        statement = statement.replace("{$rules}", statementBody);

        return statement;
    }

    @Override
    protected String buildRule(BusinessRule rule) {
        String ruleBody = template.statement;

        ruleBody = ruleBody.replace("{$category}", rule.category);
        ruleBody = ruleBody.replace("{$typeDiscription}", rule.type + (rule.typeDescription == null ? "" : " ("+rule.typeDescription+")"));
        ruleBody = ruleBody.replace("{$ruleDiscription}", (rule.ruleDescription == null ? "No description" : rule.ruleDescription));


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

        statementCode += ":new."+statement.attribute+" ";
        statementCode += template.comparisonOperator.get(statement.comparisonOperator);
        statementCode += " ";

        if (statement.dynamicAttribute != null){
            //assume that dynamicAttribute must be used
            //todo: dynamicAttribute wordt nog niet voor andere tabbellen ondersteund
            statementCode += statement.dynamicAttribute.attribute;
        }
        else{
            //assume that static attribute must be used
            statementCode += buildStaticValue(statement.staticAttribute.value, statement.staticAttribute.dataType);
        }

        if(statement.logicalOperator != null){
            statementCode += " ";
            statementCode += template.logicalOperator.get(statement.logicalOperator);
        }

        return statementCode;
    }

}
