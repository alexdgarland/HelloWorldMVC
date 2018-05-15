package personal.alexgarland.helloworldmvc.service;

public class IdSequence {

  private int nextId;

  public IdSequence(int seed) {
    nextId = seed;
  }

  public IdSequence() {
    this(1);
  }

  public int getNextId() {
    return nextId++;
  }

}
