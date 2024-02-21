package testab.infra;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import testab.config.kafka.KafkaProcessor;
import testab.domain.*;

@Service
public class MyRequestViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private MyRequestRepository myRequestRepository;

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['serviceType']=='model'"
    )
    public void whenRequested_then_CREATE_1(@Payload Requested requested) {
        try {
            if (!requested.validate()) return;

            // view 객체 생성
            MyRequest myRequest = new MyRequest();
            // view 객체에 이벤트의 Value 를 set 함
            myRequest.setId(requested.getId());
            myRequest.setState(requested.getState());
            myRequest.setUserId(requested.getUserId());
            myRequest.setModelName(requested.getModelName());
            // view 레파지 토리에 save
            myRequestRepository.save(myRequest);
            System.out.println("############ CQRS 에 저장:  " + requested);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['serviceType']=='model'"
    )
    public void whenModelCompleted_then_UPDATE_1(
        @Payload ModelCompleted modelCompleted
    ) {
        try {
            if (!modelCompleted.validate()) return;
            // view 객체 조회
            Optional<MyRequest> myRequestOptional = myRequestRepository.findById(
                modelCompleted.getId()
            );

            if (myRequestOptional.isPresent()) {
                MyRequest myRequest = myRequestOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                myRequest.setState(modelCompleted.getState());
                // view 레파지 토리에 save
                myRequestRepository.save(myRequest);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['serviceType']=='model'"
    )
    public void whenModelFailed_then_UPDATE_2(
        @Payload ModelFailed modelFailed
    ) {
        try {
            if (!modelFailed.validate()) return;
            // view 객체 조회
            Optional<MyRequest> myRequestOptional = myRequestRepository.findById(
                modelFailed.getId()
            );

            if (myRequestOptional.isPresent()) {
                MyRequest myRequest = myRequestOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                myRequest.setState(modelFailed.getState());
                // view 레파지 토리에 save
                myRequestRepository.save(myRequest);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['serviceType']=='model'"
    )
    public void whenRejected_then_UPDATE_3(@Payload Rejected rejected) {
        try {
            if (!rejected.validate()) return;
            // view 객체 조회
            Optional<MyRequest> myRequestOptional = myRequestRepository.findById(
                rejected.getId()
            );

            if (myRequestOptional.isPresent()) {
                MyRequest myRequest = myRequestOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                myRequest.setState(rejected.getState());
                // view 레파지 토리에 save
                myRequestRepository.save(myRequest);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['serviceType']=='model'"
    )
    public void whenRequestCanceled_then_UPDATE_4(
        @Payload RequestCanceled requestCanceled
    ) {
        try {
            if (!requestCanceled.validate()) return;
            // view 객체 조회
            Optional<MyRequest> myRequestOptional = myRequestRepository.findById(
                requestCanceled.getId()
            );

            if (myRequestOptional.isPresent()) {
                MyRequest myRequest = myRequestOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                myRequest.setState(requestCanceled.getState());
                // view 레파지 토리에 save
                myRequestRepository.save(myRequest);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //>>> DDD / CQRS
}
