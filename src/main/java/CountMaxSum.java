import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CountMaxSum {
    private File tsvFile = new File("categories.tsv");
    private Map<String, Integer> maxSum = new HashMap<>(); //категория / сумма
    private Map<String, String> product = new HashMap<>(); //категория / название
    private MaxCategory maxCategory = new MaxCategory();

    public void parseTsvFile() {
        ArrayList<String[]> data = new ArrayList<>();
        try (BufferedReader tsvReader = new BufferedReader(new FileReader(tsvFile))) {
            String line = null;
            while ((line = tsvReader.readLine()) != null) {
                String[] lineItems = line.split("\t");
                data.add(lineItems);
                if (!product.containsKey(lineItems[1])) {
                    product.put(lineItems[1], lineItems[0]);
                }
                if (!maxSum.containsKey(lineItems[1])) {
                    maxSum.put(lineItems[1], 0);
                }
            }
        } catch (Exception e) {
            System.err.println("parseTsvFile err " + e.getMessage());
        }
    }

    public void addProduct(String title, long sum) {
        if (product.containsValue(title)) {
            for (Map.Entry<String, String> entry : product.entrySet()) {
                if (Objects.equals(title, entry.getValue())) {
                    long i = maxSum.get(entry.getKey());
                    maxSum.put(entry.getKey(), (int) (i + sum));
                    maxCategory.setCategory(entry.getKey());
                    maxCategory.setSum(maxSum.get(entry.getKey()));
                }
            }
        } else {
            if (maxSum.containsKey("другое")) {
                int i = maxSum.get(product.get(title));
                maxSum.put("другое", (int) (i + sum));
                maxCategory.setCategory("другое");
                maxCategory.setSum(maxSum.get("другое"));
            } else {
                maxSum.put("другое", (int) sum);
                maxCategory.setCategory("другое");
                maxCategory.setSum(maxSum.get("другое"));
            }
        }
    }

    public MaxCategory getMaxCategory() {
        return maxCategory;
    }
}
