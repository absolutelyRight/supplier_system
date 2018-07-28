package hello.controllers;

import leap.core.annotation.Inject;
import leap.web.action.ControllerBase;
import leap.web.annotation.Path;
import leap.web.annotation.http.GET;
import hello.models.RoleEntity;

public class RoleController extends ControllerBase{

    public RoleEntity getRole(){
        RoleEntity r = RoleEntity.findOrNull("fang");
        if (null == r ){
            r = new RoleEntity();
            r.setRId("fang");
            r.setRName("yimikn");
            r.setRType(4);
        }
        return r;
    }
}
