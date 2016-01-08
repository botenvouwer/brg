package restService.businessRuleGenerator;

import businessRuleGenerator.domain.businessRule.BusinessRule;
import businessRuleGenerator.domain.businessRule.DynamicAttribute;
import businessRuleGenerator.domain.businessRule.Statement;
import businessRuleGenerator.domain.businessRule.StaticAttribute;
import businessRuleGenerator.domain.template.Template;
import businessRuleGenerator.domain.template.TemplateException;
import businessRuleGenerator.domain.template.TemplateFactory;
import org.json.JSONException;
import org.json.JSONObject;
import restService.JSONConverter.BusinessRuleConverter;
import restService.JSONConverter.JSONConverter;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Created by william on 17-Dec-15.
 */

@Path("/bsn")
public class BusinessRuleGeneratorService {

    @Context
    ServletContext servletContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
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

        JSONConverter jsonconv = new BusinessRuleConverter();
        jsonconv.importObject("wat");

        return  Response.status(200).entity(ret).build();

    }

    @GET
    @Path("/example")
    @Produces(MediaType.APPLICATION_JSON)
    public Response example() {

        BusinessRule rule = new BusinessRule("category_test", "type_test", "code_test", "table_test", "CRUD_test", "ruleDesc_test", "typeDesc_test", new ArrayList<Statement>());

        StaticAttribute sta1 = new StaticAttribute("30", "int");
        DynamicAttribute da1 = new DynamicAttribute("attribute_test", "test_foreignKey", "tableTest");
        Statement statement0 = new Statement("attribute_test", 0, "logicalOperator_test", "comparisonOperator_test", da1, sta1);
        Statement statement1 = new Statement("attribute_test12", 0, "logicalOperator_test12", "comparisonOperator_test12", da1, sta1);
        Statement statement2 = new Statement("attribute2", 0, "logicalOperator2", "comparisonOperator2", da1, sta1);

        JSONObject businessRules = new JSONObject();

        JSONObject br = new JSONObject();
        String result = null;
        try {
            br.put("category", rule.category);
            br.put("type", rule.type);
            br.put("code", rule.code);
            br.put("table", rule.table);
            br.put("CRUDmode", rule.CRUDmode);
            br.put("ruleDescription", rule.ruleDescription);
            br.put("typeDescription", rule.typeDescription);

            JSONObject statements = new JSONObject();
            statements.put("attribute", statement0.attribute);
            statements.put("order", statement0.order);
            statements.put("logicalOperator", statement0.logicalOperator);
            statements.put("comparisonOperator", statement0.comparisonOperator);

            JSONObject dynamicAttribute0 = new JSONObject();
            dynamicAttribute0.put("attribute", da1.attribute);
            dynamicAttribute0.put("foreignKey", da1.foreignKey);
            dynamicAttribute0.put("table", da1.table);
            statements.put("dynamicAttribute", dynamicAttribute0);

            JSONObject staticAttribute0 = new JSONObject();
            staticAttribute0.put("value", sta1.value);
            staticAttribute0.put("dataType", sta1.dataType);
            statements.put("staticAttribute", staticAttribute0);

            br.append("statements", statements);
            br.append("statements", statements);


        JSONObject br2 = new JSONObject();

            br2.put("category", rule.category);
            br2.put("type", rule.type);
            br2.put("code", rule.code);
            br2.put("table", rule.table);
            br2.put("CRUDmode", rule.CRUDmode);
            br2.put("ruleDescription", rule.ruleDescription);
            br2.put("typeDescription", rule.typeDescription);

            JSONObject statements2 = new JSONObject();
            statements2.put("attribute", statement0.attribute);
            statements2.put("order", statement0.order);
            statements2.put("logicalOperator", statement0.logicalOperator);
            statements2.put("comparisonOperator", statement0.comparisonOperator);

            JSONObject dynamicAttribute1 = new JSONObject();
            dynamicAttribute1.put("attribute", da1.attribute);
            dynamicAttribute1.put("foreignKey", da1.foreignKey);
            dynamicAttribute1.put("table", da1.table);
            statements2.put("dynamicAttribute", dynamicAttribute1);

            JSONObject staticAttribute1 = new JSONObject();
            staticAttribute1.put("value", sta1.value);
            staticAttribute1.put("dataType", sta1.dataType);
            statements2.put("staticAttribute", staticAttribute1);

            br2.append("statements", statements2);

            businessRules.append("businessRules", br);
            businessRules.append("businessRules", br2);

            result = businessRules.toString(4);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return  Response.status(200).entity(result).build();

    }

    @GET
    @Path("/raw")
    public Response rawResponse(){
        String tekst = "create or replace trigger \"VBMG_KLANTEN_T1\"\n" +
                "        BEFORE\n" +
                "        insert or update on \"VBMG_KLANTEN\"\n" +
                "        for each row\n" +
                "        begin\n" +
                "\n" +
                "        IF :new.GESLACHT NOT IN('M', 'V', 'O') THEN\n" +
                "        raise_application_error(-20001, 'error rule overtreden');\n" +
                "        END IF;\n" +
                "\n" +
                "        end;";

        return  Response.status(200).entity(tekst).build();
    }

    @GET
    @Path("/simple")
    @Produces(MediaType.APPLICATION_JSON)
    public Response simpleResponse(){
        String result = null;
        try {
        JSONObject simple = new JSONObject();

            simple.put("testeasy1", "dit_is_makkelijk");
            simple.put("testeasy2", "12321");
            result = simple.toString(4);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  Response.status(200).entity(result).build();
    }

    @GET
    @Path("/generate")
    public Response generateRules(){

        return  Response.status(200).entity("testetst").build();

    }

    @GET
    @Path("/testThingy")
    public Response testThingy(){
        JSONConverter converter = new BusinessRuleConverter();
        ArrayList<BusinessRule> list = (ArrayList<BusinessRule>) converter.importObject("{}");

        return  Response.status(200).entity("<pre>"+list.get(0).toString()).build();
    }

    @GET
    @Path("/test")
    public Response test(){

        String plsql = "create or replace trigger \"VBMG_KLANTEN_T1\"\n" +
                "BEFORE\n" +
                "insert or update on \"VBMG_KLANTEN\"\n" +
                "for each row\n" +
                "begin\n" +
                "\n" +
                "IF :new.GESLACHT NOT IN('M', 'V', 'O') THEN\n" +
                "    raise_application_error(-20001, 'error rule overtreden');\n" +
                "END IF;\n" +
                "\n" +
                "end;\u200B";

        JSONObject test = new JSONObject();
        try {
            test.put("trigger", plsql);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String res = "";
        try {
            res = test.toString(4);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //res = "<pre>"+res+"</pre>";

        return  Response.status(200).entity(res).build();
    }

    @GET
    @Path("runningdir")
    public Response getRunDir(){

        //haal het path naar de template directory op
        String templateRoot = servletContext.getRealPath("templates");

        Template template = null;
        try {
            template = TemplateFactory.build(templateRoot, "plsql");
        } catch (TemplateException e){
            e.printStackTrace();
        }

        String res = "";
        res += "template directory: "+ templateRoot;
        res += "<br><br> <pre>" + template;

        return Response.status(200).entity(res).build();
    }

}
