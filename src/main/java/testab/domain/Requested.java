package testab.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import testab.domain.*;
import testab.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class Requested extends AbstractEvent {

    private Long id;
    private String state;
    private String userId;
    private String modelName;

    public Requested(Model aggregate) {
        super(aggregate);
    }

    public Requested() {
        super();
    }
}
//>>> DDD / Domain Event
