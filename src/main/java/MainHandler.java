import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.nio.file.Files;

public class MainHandler {
    private File getterJson = new File("src/main/resources/files/getterJson.json");
    private File answerJson = new File("src/main/resources/files/jsonAnswer.json");
    private CountMaxSum countMaxSum = new CountMaxSum();

    public void writeFile(String jsonText) {
        try (OutputStream outputStream = Files.newOutputStream(getterJson.toPath())) {
            outputStream.write(jsonText.getBytes());
        } catch (Exception e) {
            System.err.println("writeFile err " + e.getMessage());
        }
    }

    public void parseFile() {
        try (Reader reader = new FileReader(getterJson)) {
            JSONParser jsonParser = new JSONParser();
            JSONObject jo = (JSONObject) jsonParser.parse(reader);
            countMaxSum.parseTsvFile();
            countMaxSum.addProduct((String) jo.get("title"), (long) jo.get("sum"));
        } catch (Exception e) {
            System.err.println("MainHandler err " + e.getMessage());
        }
    }

    public void writeJsonAnswer() {
        try (FileWriter file = new FileWriter(answerJson)) {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            CategoryInfo ci = new CategoryInfo();
            ci.setMaxCategory(countMaxSum.getMaxCategory());
            file.write(gson.toJson(ci));
            file.flush();
        } catch (Exception e) {
            System.err.println("returnJsonAnswer err " + e.getMessage());
        }
    }

    public String returnJsonAnswer() {
        writeJsonAnswer();
        String result = null;
        try (BufferedReader tsvReader = new BufferedReader(new FileReader(answerJson))) {
            result = tsvReader.readLine();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return result;
    }
}
