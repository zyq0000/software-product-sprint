package com.google.sps.data;

public final class Message {
  private final long id;
  private final String comment;
  private final long timestamp;
  private final double score;

  public Message(long id, String comment, long timestamp, double score) {
    this.id = id;
    this.comment = comment;
    this.timestamp = timestamp;
    this.score = score;
  }
}