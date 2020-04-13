package aleator;

import java.util.ArrayList;

public class ResultContainer {
    public float result;
    public ArrayList<Integer> dice;

    ResultContainer() {
        this.result = 0;
        this.dice = new ArrayList<Integer>(0);
    }

    public String diceToString() {
        String str = "";
        if (this.dice.size() > 0) {
            String str2 = this.dice.toString();
            str += str2.substring(1, str2.length() - 1).replace(",", "");    //trim brackets and commas
        }
        return str;
    }
}