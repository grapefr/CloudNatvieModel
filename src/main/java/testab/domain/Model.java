package testab.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import testab.ModelApplication;
import testab.domain.Approved;
import testab.domain.Rejected;
import testab.domain.RequestCanceled;
import testab.domain.Requested;
import testab.domain.StateChanged;

@Entity
@Table(name = "Model_table")
@Data
//<<< DDD / Aggregate Root
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String state;

    private String userId;

    private String modelName;

    @PostPersist
    public void onPostPersist() {
        // Approved approved = new Approved(this);
        // approved.publishAfterCommit();

        // Rejected rejected = new Rejected(this);
        // rejected.publishAfterCommit();

        // StateChanged stateChanged = new StateChanged(this);
        // stateChanged.publishAfterCommit();

        Requested requested = new Requested(this);
        requested.publishAfterCommit();

        // RequestCanceled requestCanceled = new RequestCanceled(this);
        // requestCanceled.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate() {
        if (this.state.equals("approved")) {
            Approved approved = new Approved(this);
            approved.publishAfterCommit();
        }
        else if (this.state.equals("rejected")) {
            Rejected rejected = new Rejected(this);
            rejected.publishAfterCommit();
        }
        else if (this.state.equals("stateChanged")) {
            StateChanged stateChanged = new StateChanged(this);
            stateChanged.publishAfterCommit();
        }
        else if (this.state.equals("requested")) {
            Requested requested = new Requested(this);
            requested.publishAfterCommit();
        }
        else if (this.state.equals("requestCanceled")) {
            RequestCanceled requestCanceled = new RequestCanceled(this);
            requestCanceled.publishAfterCommit();
        }
    }

    @PreUpdate
    public void onPreUpdate() {}

    public static ModelRepository repository() {
        ModelRepository modelRepository = ModelApplication.applicationContext.getBean(
            ModelRepository.class
        );
        return modelRepository;
    }

    //<<< Clean Arch / Port Method
    public void request() {
        //implement business logic here:

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void cancel() {
        //implement business logic here:

    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void stateChange(ModelFailed modelFailed) {
        //implement business logic here:

        /** Example 1:  new item 
        Model model = new Model();
        repository().save(model);

        */

        /** Example 2:  finding and process
        
        repository().findById(modelFailed.get???()).ifPresent(model->{
            
            model // do something
            repository().save(model);


         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void stateChange(ModelCompleted modelCompleted) {
        //implement business logic here:

        /** Example 1:  new item 
        Model model = new Model();
        repository().save(model);

        */

        /** Example 2:  finding and process
        
        repository().findById(modelCompleted.get???()).ifPresent(model->{
            
            model // do something
            repository().save(model);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
