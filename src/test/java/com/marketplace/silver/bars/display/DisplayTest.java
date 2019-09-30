package com.marketplace.silver.bars.display;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;

public class DisplayTest {

  @Test
  public void publishesMessage() {
    Writer writer = new StringWriter();
    Display display = new Display(writer);

    display.publish("Summary of offers");

    assertEquals(writer.toString(), "Summary of offers");
  }

  @Test
  public void throwsDisplayExceptionIfPublishingErrors() throws IOException {
    Writer writerThatThrowsException = Mockito.mock(Writer.class);
    Display displayThatErrors = new Display(writerThatThrowsException);
    doThrow(new IOException("Exception for testing")).when(writerThatThrowsException).flush();

    assertThrows(
        DisplayException.class,
        () -> {
          displayThatErrors.publish("message");
        });
  }
}
