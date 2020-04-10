package aleator;

import java.util.Random;

public class AleatorParser {
    protected Random r;

    AleatorParser() {
        r = new Random();
    }
    public ResultContainer parse(String s) {
        return parseForSum(s);
    }
    public ResultContainer parseForSum(String s) {
        String[] parts = s.split("\\+");
        ResultContainer rc = new ResultContainer();
        ResultContainer res;
        for(String p : parts) {
            res = this.parseForDiff(p);
            rc.result += res.result;
            rc.dice.addAll(res.dice);
        }
        return rc;
    }
    public ResultContainer parseForDiff(String s) {
        String[] parts;
        parts = s.split("\\-");
        ResultContainer rc = this.parseForProduct(parts[0]);
        ResultContainer res;
        for(int i=1; i<parts.length; i++) {
            res = this.parseForProduct(parts[i]);
            rc.result -= res.result;
            rc.dice.addAll(res.dice);
        }
        return rc;
    }
    public ResultContainer parseForProduct(String s) {
        String[] parts;
        parts = s.split("\\*");
        ResultContainer rc = this.parseForQuot(parts[0]);
        ResultContainer res;
        for(int i=1; i<parts.length; i++) {
            res = this.parseForQuot(parts[i]);
            rc.result *= res.result;
            rc.dice.addAll(res.dice);
        }
        return rc;
    }
    public ResultContainer parseForQuot(String s) {
        String[] parts;
        parts = s.split("\\÷");
        ResultContainer rc = this.parseForDie(parts[0]);
        ResultContainer res;
        for(int i=1; i<parts.length; i++) {
            res = this.parseForDie(parts[i]);
            rc.result /= res.result;
            rc.dice.addAll(rc.dice);
        }
        return rc;
    }
    public ResultContainer parseForDie(String s) {
        ResultContainer rc = new ResultContainer();
        String[] parts;
        rc.result = 0;
        parts = s.split("d");
        if (parts.length == 2) {
            int x = (int)Float.parseFloat(parts[0]);
            int y = (int)Float.parseFloat(parts[1]);
            int res;
            for(int i=0; i<x; i++) {
                res = rollDie(y);
                rc.result += (float)res;
                rc.dice.add(res);
            }
        } else {
            rc.result = Float.parseFloat(parts[0]);
        }
        return rc;
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