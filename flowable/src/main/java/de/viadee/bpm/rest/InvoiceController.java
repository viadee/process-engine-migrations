package de.viadee.bpm.rest;

import org.flowable.engine.RuntimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

import static de.viadee.bpm.process.ProcessContext.PROCESS_KEY;
import static de.viadee.bpm.process.ProcessContext.VAR_AMOUNT;
import static de.viadee.bpm.process.ProcessContext.VAR_REASON;

@RestController
public class InvoiceController {
  private static final Logger log = LoggerFactory.getLogger(InvoiceController.class);

  private final RuntimeService runtimeService;

  public InvoiceController(final RuntimeService runtimeService) {
    this.runtimeService = runtimeService;
  }


  @PostMapping(path="/invoice")
  public ResponseEntity<String> handleInvoice(@RequestBody InvoiceDto invoice) {
    log.info("Invoice incoming... 📨");

    var variables = new HashMap<String, Object>();
    variables.put(VAR_AMOUNT, invoice.getAmount());
    variables.put(VAR_REASON, invoice.getReason());

    var instance = runtimeService.startProcessInstanceByKey(PROCESS_KEY, variables);

    return ResponseEntity.ok(instance.getProcessInstanceId());
  }
}
