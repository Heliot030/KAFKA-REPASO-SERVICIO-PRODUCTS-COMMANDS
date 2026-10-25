package com.efrain.course.springcloud.kafka.command.models;

public record Command<T>(String type, Long id , T body){
}
