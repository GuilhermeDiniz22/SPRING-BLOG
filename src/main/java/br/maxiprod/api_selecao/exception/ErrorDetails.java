package br.maxiprod.api_selecao.exception;

import java.time.LocalDate;
import java.util.Objects;

public class ErrorDetails {

    private LocalDate timestamp;

    private String message;

    private String details;

    public ErrorDetails() {
    }

    public ErrorDetails(LocalDate timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public LocalDate getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public ErrorDetails timestamp(LocalDate timestamp) {
        setTimestamp(timestamp);
        return this;
    }

    public ErrorDetails message(String message) {
        setMessage(message);
        return this;
    }

    public ErrorDetails details(String details) {
        setDetails(details);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ErrorDetails)) {
            return false;
        }
        ErrorDetails errorDetails = (ErrorDetails) o;
        return Objects.equals(timestamp, errorDetails.timestamp) && Objects.equals(message, errorDetails.message)
                && Objects.equals(details, errorDetails.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, message, details);
    }

    @Override
    public String toString() {
        return "{" +
                " timestamp='" + getTimestamp() + "'" +
                ", message='" + getMessage() + "'" +
                ", details='" + getDetails() + "'" +
                "}";
    }

}
