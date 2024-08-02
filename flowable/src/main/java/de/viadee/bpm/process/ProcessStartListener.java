package de.viadee.bpm.process;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ProcessStartListener implements ExecutionListener {
  private static final Logger log = LoggerFactory.getLogger(ProcessStartListener.class);

  @Override
  public void notify(final DelegateExecution execution) {
    log.info("Process started 🚀 [{}]", shortId(execution));
  }

  private String shortId(final DelegateExecution execution) {
    return execution.getProcessInstanceId().split("-")[0];
  }
}
