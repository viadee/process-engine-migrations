package de.viadee.bpm.process;

import org.flowable.external.client.AcquiredExternalWorkerJob;
import org.flowable.external.worker.WorkerResult;
import org.flowable.external.worker.WorkerResultBuilder;
import org.flowable.external.worker.annotation.FlowableWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import static de.viadee.bpm.process.ProcessContext.STATUS_DECLINED;
import static de.viadee.bpm.process.ProcessContext.STATUS_NAME;

@Configuration
public class InvoiceDeclineWorker  {
  private static final Logger log = LoggerFactory.getLogger(InvoiceDeclineWorker.class);

  @FlowableWorker(topic = "decline-invoice")
  public WorkerResult processJob(final AcquiredExternalWorkerJob job,
                                 final WorkerResultBuilder resultBuilder) {

    var context = new ProcessContext(job.getVariables());
    log.info("Invoice declined 👎, Amount: '{}', Reason: <{}>",
      context.getAmount(), context.getReason());

    return resultBuilder.success()
                        .variable(STATUS_NAME, STATUS_DECLINED);

  }
}
