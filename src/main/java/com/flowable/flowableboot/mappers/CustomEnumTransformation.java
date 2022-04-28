package com.flowable.flowableboot.mappers;

import com.flowable.flowableboot.model.Loe;
import com.flowable.flowableboot.model.Priority;
import com.flowable.flowableboot.model.Status;
import org.springframework.stereotype.Component;

@Component
public class CustomEnumTransformation{
    public String asString(Priority priority){
        return priority.getPriority();
    }

    public Priority asPriority(String p){
        return Priority.valueByTitle(p);
    }

    public String asString(Status status) {return status.getStatus();}

    public Status asStatus(String status) {return Status.valueByTitle(status);}

    public int asInt(Loe loe) {return loe.getValue();}

    public Loe asLoe(int loe) {return Loe.valueOf(loe);}

}
