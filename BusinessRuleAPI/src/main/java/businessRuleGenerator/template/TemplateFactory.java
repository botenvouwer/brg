package businessRuleGenerator.template;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by william on 31/12/2015.
 */
public class TemplateFactory {

    public static Template build(String templateDirectory, String templateName) throws TemplateException {

        Map<String, String> templateFiles = new HashMap<String, String>();

        File templateDir = new File(templateDirectory+"\\"+templateName);

        if(!templateDir.exists() || !templateDir.isDirectory()){
            throw new TemplateException("Template '"+templateName+"' not found");
        }

        File[] listOfFiles = templateDir.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {

                File file = listOfFiles[i];
                FileInputStream fis = null;

                try {
                    fis = new FileInputStream(file);
                } catch (FileNotFoundException e) {
                    throw new TemplateException("Template file: " + file.getName()+ " not found");
                }

                byte[] data = new byte[(int) file.length()];

                try {
                    fis.read(data);
                    fis.close();
                } catch (IOException e) {
                    throw new TemplateException("IOException when loading template file: " + file.getName());
                }

                String str = null;
                try {
                    str = new String(data, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    throw new TemplateException("Unsupported Encoding when loading template file: " + file.getName());
                }

                templateFiles.put(file.getName(), str);
            }
        }

        return initiate(templateFiles, templateName);
    }

    private static Template initiate(Map<String, String> templateFiles, String templateName){

        Template template;
        switch (templateName) {
            case "plsql":
                template = new PLSQLTemplate(templateFiles, templateName);
                break;
            default:
                template = new DefaultTemplate(templateFiles, templateName);
                break;
        }

        return template;
    }

}
