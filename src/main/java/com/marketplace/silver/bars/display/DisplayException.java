package com.marketplace.silver.bars.display;

import java.io.IOException;

public class DisplayException extends RuntimeException {
  public DisplayException(String message, IOException cause) {
    super(message, cause);
  }
}
