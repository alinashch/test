package org.example.model.basic;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString
public class SuccessModel<T> {

    private Integer statusCode;

    private String subject;

    private T data;

    public SuccessModel(HttpStatus httpStatus, String subject, T data) {
        this.statusCode = httpStatus.value();
        this.subject = subject;
        this.data = data;
    }

    public static <T> SuccessModel<T> okSuccessModel(T data, String subject) {
        return new SuccessModel<>(OK, subject, data);
    }

    public static SuccessModel<String> deletedSuccessModel(String subject) {
        return new SuccessModel<>(OK, subject, "Successfully deleted");
    }

    public static <T> SuccessModel<T> createdSuccessModel(T data, String subject) {
        return new SuccessModel<>(CREATED, subject, data);
    }
}
