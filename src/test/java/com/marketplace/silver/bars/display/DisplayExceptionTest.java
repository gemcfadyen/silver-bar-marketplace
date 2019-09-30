package com.marketplace.silver.bars.display;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class DisplayExceptionTest {
  @Test
  public void exceptionHasErrorMessage() {
    DisplayException displayException =
        new DisplayException("message", new IOException("error for test"));

    assertEquals(displayException.getMessage(), "message");
  }

  @Test
  public void exceptionHasCause() {
    IOException cause = new IOException("error for test");
    DisplayException displayException = new DisplayException("message", cause);

    assertEquals(displayException.getCause(), cause);
  }
}
