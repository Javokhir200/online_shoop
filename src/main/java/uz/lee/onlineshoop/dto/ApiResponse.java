package uz.lee.onlineshoop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private boolean success;
    private String text;
    private Object object;

    public ApiResponse(boolean success, String text) {
        this.success = success;
        this.text = text;
    }
}
