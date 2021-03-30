package lab1.Options;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.nio.file.Path;

public class OptionsProvider implements IOptionsProvider {
    @Override
    public ServerOptions getOptions(Path filePath) {
        JSONParser parser = new JSONParser();
        ServerOptions sOptions = new ServerOptions();
        try{
            JSONObject jsonObject = null;
            jsonObject = (JSONObject) parser.parse(new FileReader(filePath.toString()));
            sOptions = new ServerOptions(((Long) jsonObject.get("port")).intValue(),(String) jsonObject.get("name"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sOptions;
    }
}
