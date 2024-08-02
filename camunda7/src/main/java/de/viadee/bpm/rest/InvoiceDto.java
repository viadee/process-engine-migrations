package de.viadee.bpm.rest;

public class InvoiceDto {

  private Double amount;

  private String reason;

  public Double getAmount() {
    return amount;
  }

  public void setAmount(final Double amount) {
    this.amount = amount;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(final String reason) {
    this.reason = reason;
  }
}
