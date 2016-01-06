package restService.businessRuleGenerator;

import businessRuleGenerator.domain.BusinessRule;
import businessRuleGenerator.template.Template;
import businessRuleGenerator.template.TemplateException;
import businessRuleGenerator.template.TemplateFactory;
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

/**
 * Created by william on 17-Dec-15.
 */

@Path("/bsn")
public class BusinessRuleGeneratorService {


    @Context
    ServletContext servletContext;

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

        JSONConverter jsonconv = new BusinessRuleConverter();
        jsonconv.importObject("wat");

        return  Response.status(200).entity(ret).build();

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
            result = simple.toString();
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
    @Path("runningdir")
    public Response getRunDir(){

        String res = System.getProperty( "catalina.base" );

        String root = servletContext.getContextPath();
        String root2 = servletContext.getRealPath("templates");

        res += "<br><br> deze: "+ root+"<br><br> of die: "+ root2;

        Template temp = null;
        try {
            temp = TemplateFactory.build(root2, "plsql");
        } catch (TemplateException e) {
            e.printStackTrace();
        }

        res += "<br><br> " + temp.body;

        return Response.status(200).entity(res).build();
    }

}
