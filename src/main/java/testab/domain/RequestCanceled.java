package testab.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import testab.domain.*;
import testab.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class RequestCanceled extends AbstractEvent {

    private Long id;
    private String state;
    private String userId;
    private String modelName;

    public RequestCanceled(Model aggregate) {
        super(aggregate);
    }

    public RequestCanceled() {
        super();
    }
}
//>>> DDD / Domain Event
