package de.viadee.bpm.process;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import static de.viadee.bpm.process.ProcessContext.STATUS_DECLINED;
import static de.viadee.bpm.process.ProcessContext.STATUS_NAME;
import static org.camunda.bpm.engine.variable.Variables.createVariables;

@Configuration
public class InvoiceDeclineWorker {
  private static final Logger log = LoggerFactory.getLogger(InvoiceDeclineWorker.class);

  @ExternalTaskSubscription("decline-invoice")
  public void declineInvoice(final ExternalTask task,
                             final ExternalTaskService taskService) {

    var context = new ProcessContext(task.getAllVariables());
    log.info("Invoice declined 👎, Amount: '{}', Reason: <{}>", context.getAmount(), context.getReason());

    taskService.complete(task, createVariables().putValue(STATUS_NAME, STATUS_DECLINED));
  }
}
