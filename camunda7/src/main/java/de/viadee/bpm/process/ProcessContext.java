package de.viadee.bpm.process;

import java.util.Map;

public final class ProcessContext {

  private final Map<String, Object> variables;

  public ProcessContext(final Map<String, Object> variables) {
    this.variables = variables;
  }

  public static final String PROCESS_KEY = "invoice-handling";

  static final String STATUS_NAME = "status";
  static final String STATUS_ACCEPTED = "accepted";
  static final String STATUS_DECLINED = "declined";

  public static final String VAR_AMOUNT = "amount";
  public static final String VAR_REASON = "reason";

  Double getAmount() {
    return (Double) variables.get(VAR_AMOUNT);
  }

  String getReason() {
    return (String) variables.get(VAR_REASON);
  }


}
