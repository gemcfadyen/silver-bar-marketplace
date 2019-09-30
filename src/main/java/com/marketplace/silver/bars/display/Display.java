package com.marketplace.silver.bars.display;

import java.io.*;

public class Display {
  private Writer writer;

  public Display(Writer writer) {
    this.writer = writer;
  }

  public void publish(String message) {
    try {
      writer.write(message);
      writer.flush();
    } catch (IOException e) {
      throw new DisplayException("Error in writing to the command line", e);
    }
  }
}
