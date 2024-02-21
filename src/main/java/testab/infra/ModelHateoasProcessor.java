package testab.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import testab.domain.*;

@Component
public class ModelHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Model>> {

    @Override
    public EntityModel<Model> process(EntityModel<Model> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/request")
                .withRel("request")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/requestcancel")
                .withRel("requestcancel")
        );

        return model;
    }
}
