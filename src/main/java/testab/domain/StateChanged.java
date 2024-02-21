package testab.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import testab.domain.*;
import testab.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class StateChanged extends AbstractEvent {

    private Long id;
    private String state;
    private String userId;
    private String modelName;

    public StateChanged(Model aggregate) {
        super(aggregate);
    }

    public StateChanged() {
        super();
    }
}
//>>> DDD / Domain Event
