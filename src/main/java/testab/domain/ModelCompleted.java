package testab.domain;

import java.util.*;
import lombok.*;
import testab.domain.*;
import testab.infra.AbstractEvent;

@Data
@ToString
public class ModelCompleted extends AbstractEvent {

    private Long id;
    private String type;
    private String state;
}
