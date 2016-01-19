package restService.businessRuleGenerator;

import businessRuleGenerator.dao.DAO;
import businessRuleGenerator.dao.OracleDAO;
import businessRuleGenerator.domain.ValidatorException;
import businessRuleGenerator.domain.businessRule.BusinessRule;
import businessRuleGenerator.domain.businessRule.DynamicAttribute;
import businessRuleGenerator.domain.businessRule.Statement;
import businessRuleGenerator.domain.businessRule.StaticAttribute;
import businessRuleGenerator.domain.database.ConnectionDetails;
import com.sun.xml.internal.ws.developer.SerializationFeature;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by william on 19-Jan-16.
 */
@Path("test")
public class testService {

    @GET
    @Path("/businessrulejson")
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
    @Path("homo")
    @Consumes("application/json")
    @Produces("application/json")
    public ConnectionDetails testdeziekeshit(){

        ConnectionDetails dbcon = new ConnectionDetails();
        dbcon.dbDriver = "oracle.jdbc.driver.OracleDriver";
        dbcon.dbName = "TOSAD_2015_2A_TEAM1_TARGET";
        dbcon.dbPassword = "732r78tt3vngy873";
        dbcon.dbUrl = "jdbc:oracle:thin:@ondora02.hu.nl:8521/cursus02.hu.nl";
        dbcon.dbUsername = "tosad_2015_2a_team1_target";

        //DAO dao = new OracleDAO(dbcon);
        //dao.getTables();

        return dbcon;
    }

}
