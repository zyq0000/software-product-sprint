package com.google.sps.data;

public final class Message {
  private final long id;
  private final String comment;
  private final long timestamp;

  public Message(long id, String comment, long timestamp) {
    this.id = id;
    this.comment = comment;
    this.timestamp = timestamp;
  }
}