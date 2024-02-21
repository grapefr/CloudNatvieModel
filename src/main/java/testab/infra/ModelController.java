package testab.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import testab.domain.*;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/models")
@Transactional
public class ModelController {


    @Autowired
    ModelRepository modelRepository;

    @RequestMapping(value = "/models",
            method = RequestMethod.POST,
            produces = "application/json;charset=UTF-8")
    public Model reqeust(HttpServletRequest request, HttpServletResponse response, @RequestBody Model model
        ) throws Exception {
            System.out.println("##### /target/approve  called ##### target : " + model.getUserId() );
            model.setState("requested");
            // modelRepository.save(model);
            return modelRepository.save(model);
    }

    @RequestMapping(value = "/models",
            method = RequestMethod.PUT,
            produces = "application/json;charset=UTF-8")
    public Model approve(HttpServletRequest request, HttpServletResponse response, @RequestBody Approved approved
        ) throws Exception {
            System.out.println("##### /target/approve  called ##### target approve :  " + approved.getState()  );
            Optional<Model> optionalModel = modelRepository.findById(approved.getId());
            optionalModel.get().setState("approved");
            modelRepository.save(optionalModel.get());
            return optionalModel.get();
    }

    @RequestMapping(value = "/models/{id}",
            method = RequestMethod.PATCH,
            produces = "application/json;charset=UTF-8")
    public Model reject(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Long id
        ) throws Exception {
            System.out.println("##### /target/approve  called ##### target reject: " + id);
            Optional<Model> optionalModel = modelRepository.findById(id);
            optionalModel.get().setState("rejected");
            modelRepository.save(optionalModel.get());
            return optionalModel.get();
    }

    // 모델 취소 API 작성
    @RequestMapping(value = "/models/{id}",
            method = RequestMethod.DELETE,
            produces = "application/json;charset=UTF-8")
    public Model cancel(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Long id) throws Exception {
        System.out.println("##### /target/cancel  called ##### target cancel: " + id);
        Optional<Model> optionalModel = modelRepository.findById(id);
        optionalModel.get().setState("requestCanceled");
        modelRepository.save(optionalModel.get());
        return optionalModel.get();
    }


    // 모델 정보 확인
    @RequestMapping(value = "/models/{id}",
            method = RequestMethod.GET,
            produces = "application/json;charset=UTF-8")
    public Model check(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Long id) throws Exception {
        System.out.println("##### /target/get  called ##### target get: " + id);
        Optional<Model> optionalModel = modelRepository.findById(id);
        return optionalModel.get();
    }
}
//>>> Clean Arch / Inbound Adaptor
