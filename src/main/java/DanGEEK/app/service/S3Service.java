package DanGEEK.app.service;

import DanGEEK.app.Component.S3Uploader;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@RequiredArgsConstructor
@Service
public class S3Service{
    private final S3Uploader s3Uploader;

    @Transactional
    public String upload(MultipartFile file) {
        String url = "";
        if(file != null)  url = s3Uploader.uploadFileToS3(file, "static/image");
        return url;
    }
}