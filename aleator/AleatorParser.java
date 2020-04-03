package aleator;

public class AleatorParser {

    AleatorParser() {

    }
    public float parse(String s) {
        String[] parts = s.split("\\+");
        float result;
        if (parts.length > 1) {
            result = 0;
            for(String p : parts) {
                result += this.parse(p);
            }
        } else {
            result = Float.parseFloat(s);
        }
        return result;
    }

}