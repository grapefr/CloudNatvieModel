package testab.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import testab.config.kafka.KafkaProcessor;
import testab.domain.*;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    ModelRepository modelRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ModelFailed'"
    )
    public void wheneverModelFailed_StateChange(
        @Payload ModelFailed modelFailed
    ) {
        ModelFailed event = modelFailed;
        System.out.println(
            "\n\n##### listener StateChange : " + modelFailed + "\n\n"
        );

        // Sample Logic //
        Model.stateChange(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ModelCompleted'"
    )
    public void wheneverModelCompleted_StateChange(
        @Payload ModelCompleted modelCompleted
    ) {
        ModelCompleted event = modelCompleted;
        System.out.println(
            "\n\n##### listener StateChange : " + modelCompleted + "\n\n"
        );

        // Sample Logic //
        Model.stateChange(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
