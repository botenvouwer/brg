package restService.businessRuleGenerator;

import businessRuleGenerator.domain.BusinessRule;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by william on 17-Dec-15.
 */

@Path("/bsn")
public class BusinessRuleGeneratorService {

    @GET
    public Response index(){

        BusinessRule rule = new BusinessRule();
        rule.category = "testcategory";
        rule.code = "testcode";
        rule.table = "testtable";

        JSONObject ding = new JSONObject();

        try {
            ding.put("test2", "jantje2");
            ding.put("test", "jantje");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject test = new JSONObject();
        try {
            test.put("category", rule.category);
            test.put("type", rule.type);
            test.put("code", rule.code);
            test.put("table", rule.table);
            test.put("CRUDmode", rule.CRUDmode);
            test.put("ruleDescription", rule.ruleDescription);
            test.put("typeDescription", rule.typeDescription);

            test.append("test", ding);
            test.append("test", ding);
            test.append("test", ding);

            test.put("test2", ding);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String ret = "";
        try {
            ret = test.toString(4);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ret = "<pre>"+ret+"</pre>";

        return  Response.status(200).entity(ret).build();

    }

    @GET
    @Path("/generate")
    public Response generateRules(){

        return  Response.status(200).entity("testetst").build();

    }

}
