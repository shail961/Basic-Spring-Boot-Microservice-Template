package com.microservice.ms1.microservice1;

import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public Model toModel(Request request) {
        if (request == null) {
            return null;
        }
        return Model.builder()
                .id(request.getId())
                .field1(request.getField1())
                .field2(request.getField2())
                .build();
    }

    public Response toResponse(Model model) {
        if (model == null) {
            return null;
        }
        return Response.builder()
                .id(model.getId())
                .field1(model.getField1())
                .field2(model.getField2())
                .build();
    }

    public void merge(Model model, Request request){
        if(request == null) return;
        if(!request.getField1().isEmpty()){
            model.setField1(request.getField1());
        }
        if(request.getField2()!=null){
            model.setField2(request.getField2());
        }
    }

}
