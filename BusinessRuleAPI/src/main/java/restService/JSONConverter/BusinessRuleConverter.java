package restService.JSONConverter;

import businessRuleGenerator.domain.BusinessRule;
import businessRuleGenerator.domain.Statement;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melvin on 28-12-2015.
 */
public class BusinessRuleConverter implements JSONConverter {

    private Statement getStatementFromJSON(JSONObject obj) {
        try {
            int code = obj.getInt("code");
            String attribute = obj.getString("attribute");
            int order = obj.getInt("order");
            String logicalOperator = obj.getString("logicalOperator");
            String comparisonOperator = obj.getString("comparisonOperator");

            return new Statement(code, attribute, order, logicalOperator, comparisonOperator);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Statement();
    }

    private BusinessRule getBusinessRuleFromJSON(JSONObject obj) {
        try {
            String category = obj.getString("category");
            String type = obj.getString("type");
            String code = obj.getString("code");
            String table = obj.getString("table");
            String CRUDmode = obj.getString("CRUDmode");
            String ruleDescription = obj.getString("ruleDescription");
            String typeDescription = obj.getString("typeDescription");

            return new BusinessRule(category, type, code, table, CRUDmode, ruleDescription, typeDescription);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new BusinessRule();
    }

    @Override
    public void importObject(String json) {
        try {

        BusinessRule rule = new BusinessRule();
        rule.category = "testcategory";
        rule.type = "testype";
        rule.code = "testcode";
        rule.table = "testtable";
        rule.CRUDmode = "testCRUD";
        rule.ruleDescription = "testRuleDesc";
        rule.typeDescription = "testTypeDesc";

        JSONObject test = new JSONObject();

            test.put("category", rule.category);
            test.put("type", rule.type);
            test.put("code", rule.code);
            test.put("table", rule.table);
            test.put("CRUDmode", rule.CRUDmode);
            test.put("ruleDescription", rule.ruleDescription);
            test.put("typeDescription", rule.typeDescription);


            JSONObject statement_obj = new JSONObject();
            statement_obj.put("test2", "jantje2");
            statement_obj.put("test", "jantje");

            // test.append("test", ding);
            //test.append("test", ding);



            JSONObject br = new JSONObject(test.toString(4));

            BusinessRule businessRule_res = getBusinessRuleFromJSON(br);
            /*
            List<String> list = new ArrayList<String>();
            JSONArray statements = br.getJSONArray("statements");
            for(int i = 0 ; i < statements.length() ; i++){
                list.add(statements.getJSONObject(i).getString("interestKey"));
            }
            */
            System.out.println("result: " + businessRule_res.typeDescription);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public JSONObject exportObject(Object object) {

        if(object instanceof BusinessRule){



        }

        return null;
    }

}
