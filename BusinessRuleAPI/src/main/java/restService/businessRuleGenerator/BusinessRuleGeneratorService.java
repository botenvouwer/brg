package restService.businessRuleGenerator;
import businessRuleGenerator.domain.JDBC;
import businessRuleGenerator.domain.ValidatorException;
import businessRuleGenerator.domain.businessRule.BusinessRule;
import businessRuleGenerator.domain.businessRule.Statement;
import businessRuleGenerator.domain.businessRule.DynamicAttribute;
import businessRuleGenerator.domain.businessRule.StaticAttribute;
import businessRuleGenerator.domain.template.Template;
import businessRuleGenerator.domain.template.TemplateException;
import businessRuleGenerator.domain.template.TemplateFactory;
import businessRuleGenerator.generator.BusinessRuleGenerator;
import businessRuleGenerator.generator.GeneratorException;
import businessRuleGenerator.generator.GeneratorFactory;
import businessRuleGenerator.util.ListParser;
import com.sun.webkit.Utilities;
import org.json.JSONException;
import org.json.JSONObject;
import businessRuleGenerator.domain.businessRule.BusinessRuleList;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Map;

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
        return  Response.status(200).entity("index page").build();

    }

    @GET
    @Path("/example")
    @Produces(MediaType.APPLICATION_JSON)
    public Response example() throws ValidatorException {

        //BusinessRule rule = new BusinessRule("category_test", "type_test", "code_test", "table_test", "CRUD_test", "ruleDesc_test", "typeDesc_test", new ArrayList<Statement>(), null);
        BusinessRule rule = new BusinessRule("category_test", "type_test", "code_test", "table_test", "CRUD_test", "ruleDesc_test", "typeDesc_test", null, null);

        StaticAttribute sta1 = new StaticAttribute("30", "int");
        DynamicAttribute da1 = new DynamicAttribute("attribute_test", "test_foreignKey", "tableTest");
        Statement statement0 = new Statement("attribute_test", 0, "logicalOperator_test", "comparisonOperator_test", null, sta1);
        Statement statement1 = new Statement("attribute_test12", 0, "logicalOperator_test12", "comparisonOperator_test12", da1, null);
        Statement statement2 = new Statement("attribute2", 0, "logicalOperator2", "comparisonOperator2", da1, null);

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
            //statements.put("staticAttribute", staticAttribute0);

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
            //statements2.put("dynamicAttribute", dynamicAttribute1);

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

    @POST
    @Path("/convert")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getJSON(BusinessRuleList json) throws Exception {
        JDBC jdbc = new JDBC();
        jdbc.insertTrigger("create or replace TRIGGER TOSAD_2015_2A_TEAM1_TARGET.VBMG_KLANTEN_T2 BEFORE INSERT OR UPDATE ON TOSAD_2015_2A_TEAM1_TARGET.VBMG_KLANTEN FOR EACH ROW BEGIN IF :new.GESLACHT NOT IN('M', 'V', 'O') THEN raise_application_error(-20001, 'error rule overtreden'); END IF; END;");
        try {
            json.validate();
        } catch (ValidatorException e) {
            return Response.status(200).entity(e.getMessage()).build();
        }
        return Response.status(200).entity("succesfully converted the JSON object!" + json.toString()).build();
    }

    @GET
    @Path("/raw")
    public Response rawResponse(){
        String tekst = "[{\"code\" : \"create or replace trigger \\\"VBMG_KLANTEN_T1\\\"\n" +
                "        BEFORE\n" +
                "        insert or update on \\\"VBMG_KLANTEN\\\"\n" +
                "        for each row\n" +
                "        begin\n" +
                "\n" +
                "        IF :new.GESLACHT NOT IN('M', 'V', 'O') THEN\n" +
                "        raise_application_error(-20001, 'error rule overtreden');\n" +
                "        END IF;\n" +
                "\n" +
                "        end;\"}]";

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
    @Path("generatetest/{templateName}/{generatorName}")
    public Response getRunDir(@PathParam("templateName") String templateName, @PathParam("generatorName") String generatorName) throws TemplateException, ValidatorException {

        //haal het path naar de template directory op
        String templateRoot = servletContext.getRealPath("templates");

        //Maak BusinessRule aan
        BusinessRule rule = new BusinessRule();
        rule.table = "dinkie";
        rule.category = "AAA";
        rule.code = "AAA";
        rule.CRUDmode = "CU";
        rule.type = "test rule";
        rule.errorMessage = "Naam mag geen piet zijn";

        //maak statements aan voor rule
        Statement statement = new Statement();
        statement.attribute = "name";
        statement.order = 1;
        statement.comparisonOperator = "NotEqual";
        statement.staticAttribute = new StaticAttribute("piet", "String");

        Statement statement3 = new Statement();
        statement3.attribute = "name";
        statement3.order = 2;
        statement3.comparisonOperator = "GreaterThan";
        statement3.dynamicAttribute = new DynamicAttribute("name2");

        ArrayList<Statement> statements = new ArrayList<Statement>();
        statements.add(statement);

        rule.setStatements(statements);

        BusinessRule rule2 = new BusinessRule();
        rule2.table = "dinkie";
        rule2.category = "AAA";
        rule2.code = "AAA";
        rule2.CRUDmode = "CU";
        rule2.type = "test rule";
        rule2.errorMessage = "Naam mag geen piet zijn";
        rule2.typeDescription = "Jan hoekman kan timmertenernebrnb";
        rule2.ruleDescription = "Melvin fuck you";

        //maak statements aan voor rule
        Statement statement2 = new Statement();
        statement2.attribute = "name";
        statement2.order = 1;
        statement2.comparisonOperator = "NotEqual";
        statement2.staticAttribute = new StaticAttribute("piet", "String");

        ArrayList<Statement> statements2 = new ArrayList<Statement>();
        statements2.add(statement2);

        rule2.setStatements(statements2);

        ArrayList<BusinessRule> rules = new ArrayList<BusinessRule>();
        rules.add(rule);
        rules.add(rule2);

        BusinessRuleList rulesList = new BusinessRuleList();
        rulesList.businessRules = rules;

        try{
            rulesList.validate();
        }
        catch (ValidatorException e){
            return Response.status(500).entity("Error: "+ e.getMessage()).build();
        }

        //Maak een template aan
        Template template = null;
        try {
            template = TemplateFactory.build(templateRoot, templateName);
        }
        catch (TemplateException e){
            return Response.status(500).entity("Error: "+ e.getMessage()).build();
        }
        catch (ValidatorException e) {
            return Response.status(500).entity("Error: "+ e.getMessage()).build();
        }

        //Maak generator aan en genereer
        ArrayList<String> code = null;
        BusinessRuleGenerator generator = null;
        try {
            generator = GeneratorFactory.build(generatorName,template);
            code = generator.generate(rulesList);
        }
        catch (GeneratorException e) {
            e.printStackTrace();
        }
        catch (TemplateException e) {
            e.printStackTrace();
        }

        String res = "";

        for (String c : code){
            res += c;
            res += "\n\n";
        }

        return Response.status(200).entity("<pre>"+res).build();
    }

    @GET
    @Path("homo")
    public Response testdeziekeshit() throws Exception {

        String ding = "TemplateClass:-:plsqlAA\nTemplateName:-:plsqlBB";

        Map<String, String> list = ListParser.loadList(ding, ":-:");

        return Response.status(200).entity("<pre>"+list.get("templateclass")).build();
    }

}
