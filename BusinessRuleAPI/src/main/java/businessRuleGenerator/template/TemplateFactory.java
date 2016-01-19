package businessRuleGenerator.template;

import java.io.*;
import java.util.ArrayList;
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

        //todo: exeptions beter afvangen (terug geven zodat we een fatsoenlijke error kunnen weergeven)
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {

                File file = listOfFiles[i];
                FileInputStream fis = null;

                try {
                    fis = new FileInputStream(file);
                } catch (FileNotFoundException e) {}

                byte[] data = new byte[(int) file.length()];

                try {
                    fis.read(data);
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String str = null;
                try {
                    str = new String(data, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                templateFiles.put(file.getName(), str);
            }
        }

        //todo: switch maken en door alle templates loopen
        Template template = new PLSQLTemplate(templateFiles, templateName);

        return template;
    }

}
