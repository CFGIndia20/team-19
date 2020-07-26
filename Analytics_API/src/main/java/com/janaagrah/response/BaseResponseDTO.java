package com.janaagrah.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Common DTO that is used to transfer responses to the
 * front-end. 
 * Has two common keys:
 * 	data: carries the required data for GET request and
 * 		  an acknowledgment for a PUT/POST request
 * 	error: carries any error message caused due to an
 * 		   exception
 * @author jimil
 *
 */
public class BaseResponseDTO {

    @JsonProperty(value = "data")
    private Object data;
    @JsonProperty(value = "error")
    private Object error;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseResponseDTO that = (BaseResponseDTO) o;
        return Objects.equals(data, that.data) &&
                Objects.equals(error, that.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, error);
    }

    @Override
    public String toString() {
        return "BaseResponseDTO{" +
                "data=" + data +
                ", error=" + error +
                '}';
    }
}
