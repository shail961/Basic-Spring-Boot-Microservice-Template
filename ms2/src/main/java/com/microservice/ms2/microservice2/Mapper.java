package com.microservice.ms2.microservice2;

import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public Model toModel(Request request) {
        if (request == null) {
            return null;
        }
        return Model.builder()
                .id(request.getId())
                .variable1(request.getVariable1())
                .variable2(request.getVariable2())
                .build();
    }

    public Response toResponse(Model model) {
        if (model == null) {
            return null;
        }
        return Response.builder()
                .id(model.getId())
                .variable1(model.getVariable1())
                .variable2(model.getVariable2())
                .build();
    }

    public void merge(Model model, Request request){
        if(request == null) return;
        if(!request.getVariable1().isEmpty()){
            model.setVariable1(request.getVariable1());
        }
        if(request.getVariable2().isEmpty()){
            model.setVariable2(request.getVariable2());
        }
    }

}
