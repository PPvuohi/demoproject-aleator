package aleator;

import java.util.Random;

public class AleatorParser {
    protected Random r;

    AleatorParser() {
        r = new Random();
    }
    public float parse(String s) {
        return parseForSum(s);
    }
    public float parseForSum(String s) {
        String[] parts = s.split("\\+");
        float result = 0;
        for(String p : parts) {
            result += this.parseForDiff(p);
        }
        return result;
    }
    public float parseForDiff(String s) {
        String[] parts;
        parts = s.split("\\-");
        float result = this.parseForProduct(parts[0]);
        for(int i=1; i<parts.length; i++) {
            result -= this.parseForProduct(parts[i]);
        }
        return result;
    }
    public float parseForProduct(String s) {
        String[] parts;
        parts = s.split("\\*");
        float result = this.parseForQuot(parts[0]);
        for(int i=1; i<parts.length; i++) {
            result *= this.parseForQuot(parts[i]);
        }
        return result;
    }
    public float parseForQuot(String s) {
        String[] parts;
        parts = s.split("\\÷");
        float result = this.parseForDie(parts[0]);
        for(int i=1; i<parts.length; i++) {
            result /= this.parseForDie(parts[i]);
        }
        return result;
    }
    public float parseForDie(String s) {
        String[] parts;
        float result = 0;
        parts = s.split("d");
        if (parts.length == 2) {
            int x = (int)Float.parseFloat(parts[0]);
            int y = (int)Float.parseFloat(parts[1]);
            for(int i=0; i<x; i++) {
                result += rollDie(y);
            }
        } else {
            result = Float.parseFloat(parts[0]);
        }
        return result;
    }
    public int rollDie(int sides) {
        int result;
        if (sides == 0) {
            result = 0;
        } else if (sides < 0) {
            result = -(this.r.nextInt(-sides)+1);
        }
        else {
            result = this.r.nextInt(sides)+1;
        }
        return result;
    }
}