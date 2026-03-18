package org.task;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import lombok.SneakyThrows;
import org.apache.commons.io.output.WriterOutputStream;
import org.apache.http.params.CoreConnectionPNames;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;

public class ApiClient {
    RestAssuredConfig config;

    @SneakyThrows
    ApiClient(String apiTimeout) {
        try (FileWriter fileWriter = new FileWriter("/logs/logging.txt"); // вынести в конфиг и создавать файл
             PrintStream printStream = new PrintStream(new WriterOutputStream(fileWriter), true)) {
            config = RestAssured.config()
                    .httpClient(HttpClientConfig.httpClientConfig()
                            .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, apiTimeout)
                            .setParam(CoreConnectionPNames.SO_TIMEOUT, apiTimeout)
                    ).logConfig(
                            LogConfig
                                    .logConfig()
                                    .defaultStream(printStream)
                                    .enableLoggingOfRequestAndResponseIfValidationFails());
        }

    }

}