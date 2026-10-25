package com.efrain.course.springcloud.kafka.command.models.dto;

public record Replay<T>(String status, String message , T body) {
}
