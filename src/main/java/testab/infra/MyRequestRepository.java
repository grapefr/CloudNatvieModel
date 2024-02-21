package testab.infra;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import testab.domain.*;

@RepositoryRestResource(
    collectionResourceRel = "myRequests",
    path = "myRequests"
)
public interface MyRequestRepository
    extends PagingAndSortingRepository<MyRequest, Long> {}
