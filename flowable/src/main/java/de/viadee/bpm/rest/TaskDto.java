package de.viadee.bpm.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TaskDto {

  private String id;
  private Map<String, Object> variables = new HashMap<>();

  public String getId() {
    return id;
  }

  public void setId(final String id) {
    this.id = id;
  }

  public Map<String, Object> getVariables() {
    if (Objects.isNull(variables)) {
      variables = new HashMap<>();
    }
    return variables;
  }

  public void setVariables(final Map<String, Object> variables) {
    this.variables = variables;
  }
}
