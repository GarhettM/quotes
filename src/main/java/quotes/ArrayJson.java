package quotes;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ArrayJson {

  public ArrayList<Array> quoteArray;

  public ArrayJson(ArrayList<Array> quoteArray) {
    this.quoteArray = quoteArray;
  }

  public String toString() {
    return String.format("Here's this thing %s", quoteArray);
  }
}
