// The purpose of this class is to provide an object
//representation of a flowable process task
// Tasks can vary from users tasks, services, etc.
package com.flowable.flowableboot.model;

import java.util.HashMap;
import java.util.Map;

public class UserTaskRepresentation {
  private String id;
  private String name;
  private Map<String, String> assign = new HashMap<String, String>();

  public UserTaskRepresentation(String id, String name,
      Map<String, String> assign) {
    this.id = id;
    this.name = name;
    this.assign = assign;
  }


  public String getId(){
    return id;
  }
  public void setId(String id){
    this.id = id;
  }
  public String getName(){
    return name;
  }
  public void setName(String name){
    this.name = name;
  }

  public Map<String, String> getAssign() {
    return assign;
  }

  public void setAssign(Map<String, String> assign) {
    this.assign = assign;
  }
}