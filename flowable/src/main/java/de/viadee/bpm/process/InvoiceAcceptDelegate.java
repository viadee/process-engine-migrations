package de.viadee.bpm.process;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static de.viadee.bpm.process.ProcessContext.STATUS_ACCEPTED;
import static de.viadee.bpm.process.ProcessContext.STATUS_NAME;

@Component
public class InvoiceAcceptDelegate implements JavaDelegate {
  private static final Logger log = LoggerFactory.getLogger(InvoiceAcceptDelegate.class);

  @Override
  public void execute(final DelegateExecution execution) {

    var context = new ProcessContext(execution.getVariables());
    log.info("Invoice accepted 💸, Amount: '{}', Reason: <{}>",
      context.getAmount(), context.getReason());

    execution.setVariable(STATUS_NAME, STATUS_ACCEPTED);
  }
}
