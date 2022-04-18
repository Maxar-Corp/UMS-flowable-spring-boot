package com.flowable.flowableboot.mappers;

import com.flowable.flowableboot.model.Loe;
import com.flowable.flowableboot.model.Priority;
import com.flowable.flowableboot.model.Status;
import org.springframework.stereotype.Component;

@Component
public class CustomEnumTransformation{
    public int asInt(Priority priority){
        return priority.getValue();
    }

    public Priority asPriority(int p){
        return Priority.valueOf(p);
    }

    public String asString(Status status) {return status.status();}

    public Status asStatus(String status) {return Status.valueOfStatus(status);}

    public String asString(Loe loe) {return loe.loe();}

    public Loe asLoe(String loe) {return Loe.valueOfLoe(loe);}

}
