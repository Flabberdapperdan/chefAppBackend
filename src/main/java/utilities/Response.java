package utilities;

import org.springframework.http.ResponseEntity;

import java.util.List;

public class Response extends ResponseEntity<> {
    public Response(boolean succes) {
        this.succes = succes;
    }

    boolean succes;
}
