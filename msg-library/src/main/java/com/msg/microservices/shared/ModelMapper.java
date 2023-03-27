package com.msg.microservices.shared;

import org.modelmapper.config.Configuration;

public class ModelMapper {
    static public org.modelmapper.ModelMapper mapTo() {
        org.modelmapper.ModelMapper modelMapper = new org.modelmapper.ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        return modelMapper;
    }
}
