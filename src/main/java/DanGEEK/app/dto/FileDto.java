package DanGEEK.app.dto;

import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;
@Getter
public class FileDto {
    private MultipartFile file;
    private String originalFileName;
    private int fileAttached;


}
