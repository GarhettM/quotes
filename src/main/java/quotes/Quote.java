package quotes;

import java.util.ArrayList;

public class Quote {
  public ArrayList<String> tags;
  public String author;
  public String likes;
  public String text;

  public Quote(ArrayList<String> tags, String author, String likes, String text) {
    this.tags = tags;
    this.author = author;
    this.likes = likes;
    this.text = text;
  }

public String toString() {
  return String.format("Here's this thing %s", author);
  }
}

