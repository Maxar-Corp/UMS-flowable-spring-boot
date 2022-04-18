// The purpose of this class is to provide an object
//representation of a flowable process task
// Tasks can vary from users tasks, services, etc.
package com.flowable.flowableboot.model;

import java.util.HashMap;
import java.util.Map;

public class UserTaskRepresentation {
  private String id;
  private String status;
  private Map<String, String> assign;

  public UserTaskRepresentation(String id, String status,
      Map<String, String> assign) {
    this.id = id;
    this.status = status;
    this.assign = assign;
  }


  public String getId(){
    return id;
  }
  public void setId(String id){
    this.id = id;
  }
  public String getStatus(){
    return status;
  }
  public void setStatus(String status){
    this.status = status;
  }

  public Map<String, String> getAssign() {
//    TODO update this logic/remove, sets assignee to default if no assignee provided
//    String defaultAssignee = assign.getOrDefault("assignee", "default");
//    assign.put("assignee", defaultAssignee);
    return assign;
  }

  public void setAssign(Map<String, String> assign) {
    this.assign = assign;
  }
}