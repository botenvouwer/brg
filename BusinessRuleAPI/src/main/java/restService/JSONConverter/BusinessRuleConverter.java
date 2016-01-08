package restService.JSONConverter;
import businessRuleGenerator.domain.BusinessRule;
import businessRuleGenerator.domain.DynamicAttribute;
import businessRuleGenerator.domain.Statement;
import businessRuleGenerator.domain.StaticAttribute;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by melvin on 28-12-2015.
 */
public class BusinessRuleConverter implements JSONConverter {

    private BusinessRule getBusinessRuleFromJSON(JSONObject obj) {

        try {
            String category = obj.getString("category");
            String type = obj.getString("type");
            String code = obj.getString("code");
            String table = obj.getString("table");
            String CRUDmode = obj.getString("CRUDmode");
            String ruleDescription = obj.getString("ruleDescription");
            String typeDescription = obj.getString("typeDescription");

            ArrayList<Statement> statements = new ArrayList<Statement>();
            JSONArray statementsJSON = obj.getJSONArray("statements");
            for (int i = 0; i < statementsJSON.length(); i++) {
                JSONObject statement = statementsJSON.getJSONObject(i);
                statements.add(getStatementFromJSON(statement));
            }

            return new BusinessRule(category, type, code, table, CRUDmode, ruleDescription, typeDescription, statements);

        } catch(Exception jce) {

        }
        return new BusinessRule();
    }

    private Statement getStatementFromJSON(JSONObject obj) {
        try {
            String attribute = obj.getString("attribute");
            int order = obj.getInt("order");
            String logicalOperator = obj.getString("logicalOperator");
            String comparisonOperator = obj.getString("comparisonOperator");
            DynamicAttribute dynamicAttribute = getDynamicAttributeFromJSON(obj.getJSONObject("dynamicAttribute"));
            StaticAttribute staticAttribute = getStaticAttributeFromJSON(obj.getJSONObject("staticAttribute"));

            return new Statement(attribute, order, logicalOperator, comparisonOperator, dynamicAttribute, staticAttribute);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Statement();
    }

    private StaticAttribute getStaticAttributeFromJSON(JSONObject obj) {
        try {
            String value = obj.getString("value");
            String dataType = obj.getString("dataType");
            return new StaticAttribute(value, dataType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new StaticAttribute();
    }

    private DynamicAttribute getDynamicAttributeFromJSON(JSONObject obj) {
        try {
            String attribute = obj.getString("attribute");
            String foreignKey = obj.getString("foreignKey");
            String table = obj.getString("table");
            return new DynamicAttribute(attribute, foreignKey, table);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DynamicAttribute();
    }

    @Override
    public ArrayList<BusinessRule> importObject(String json) {

        ArrayList<BusinessRule> businessRules = null;

        try {

        String teststr = "{\"businessRules\": [\n" +
                "    {\n" +
                "        \"CRUDmode\": \"CRUD_test\",\n" +
                "        \"category\": \"category_test\",\n" +
                "        \"code\": \"code_test\",\n" +
                "        \"ruleDescription\": \"ruleDesc_test\",\n" +
                "        \"statements\": [{\n" +
                "            \"attribute\": \"attribute_test\",\n" +
                "            \"comparisonOperator\": \"comparisonOperator_test\",\n" +
                "            \"dynamicAttribute\": {\n" +
                "                \"attribute\": \"attribute_test\",\n" +
                "                \"foreignKey\": \"test_foreignKey\",\n" +
                "                \"table\": \"tableTest\"\n" +
                "            },\n" +
                "            \"logicalOperator\": \"logicalOperator_test\",\n" +
                "            \"order\": 0,\n" +
                "            \"staticAttribute\": {\n" +
                "                \"dataType\": \"int\",\n" +
                "                \"value\": \"30\"\n" +
                "            }\n" +
                "        }],\n" +
                "        \"table\": \"table_test\",\n" +
                "        \"type\": \"type_test\",\n" +
                "        \"typeDescription\": \"typeDesc_test\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"CRUDmode\": \"CRUD_test\",\n" +
                "        \"category\": \"category_test\",\n" +
                "        \"code\": \"code_test\",\n" +
                "        \"ruleDescription\": \"ruleDesc_test\",\n" +
                "        \"statements\": [{\n" +
                "            \"attribute\": \"attribute_test\",\n" +
                "            \"comparisonOperator\": \"comparisonOperator_test\",\n" +
                "            \"dynamicAttribute\": {\n" +
                "                \"attribute\": \"attribute_test\",\n" +
                "                \"foreignKey\": \"test_foreignKey\",\n" +
                "                \"table\": \"tableTest\"\n" +
                "            },\n" +
                "            \"logicalOperator\": \"logicalOperator_test\",\n" +
                "            \"order\": 0,\n" +
                "            \"staticAttribute\": {\n" +
                "                \"dataType\": \"int\",\n" +
                "                \"value\": \"30\"\n" +
                "            }\n" +
                "        }],\n" +
                "        \"table\": \"table_test\",\n" +
                "        \"type\": \"type_test\",\n" +
                "        \"typeDescription\": \"typeDesc_test\"\n" +
                "    }\n" +
                "]}";


            JSONObject brs = new JSONObject(teststr);


            businessRules = new ArrayList<BusinessRule>();
            JSONArray businessrules = brs.getJSONArray("businessRules");
            for(int i = 0 ; i < businessrules.length() ; i++) {
                JSONObject businessRule = businessrules.getJSONObject(i);
                businessRules.add(getBusinessRuleFromJSON(businessRule));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return businessRules;

    }

    @Override
    public JSONObject exportObject(Object object) {

        if(object instanceof BusinessRule){



        }

        return null;
    }

}
