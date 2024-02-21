package testab.infra;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import testab.domain.*;


@RestController
// @RequestMapping(value="/models")
@Transactional
public class MyRequestController {

  @Autowired
  MyRequestRepository myRequestRepository;

  // 모델 정보 확인
  @RequestMapping(value = "/models/mylist",
      method = RequestMethod.GET,
      produces = "application/json;charset=UTF-8")
  public Iterable<MyRequest> check(HttpServletRequest request, HttpServletResponse response) throws Exception {
    System.out.println("##### /target/get  called ##### target get: ");
    Iterable<MyRequest> optionalModel = myRequestRepository.findAll();
    return optionalModel;
  }
}
