package com.tunelyf.nf.service;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.firebase.cloud.StorageClient;

@Service
public class FirebaseStorageService {

    private final String BUCKET_NAME = "work-progress-387ac.appspot.com"; 

    public String uploadFile(MultipartFile file, String folder) {
        try {
            String fileName = folder + "/" + UUID.randomUUID() + "_" + file.getOriginalFilename();

            // 🔐 Generate secure token
            String downloadToken = UUID.randomUUID().toString();

            // 📦 Add token in metadata
            Map<String, String> metadata = Map.of("firebaseStorageDownloadTokens", downloadToken);

            // ✅ Build blob info with metadata
            BlobInfo blobInfo = BlobInfo.newBuilder(BUCKET_NAME, fileName)
                    .setContentType(file.getContentType())
                    .setMetadata(metadata)
                    .build();

            // 🗂️ Upload
            StorageClient.getInstance().bucket().getStorage().create(blobInfo, file.getBytes());

            // 🔗 Return Firebase tokenized URL
            return String.format("https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media&token=%s",
                    BUCKET_NAME,
                    URLEncoder.encode(fileName, StandardCharsets.UTF_8),
                    downloadToken);

        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file to Firebase", e);
        }
    }
    
    // ✅ NEW: Delete file from Firebase
    public void deleteFileByUrl(String fileUrl) {
        try {
            if (fileUrl == null || !fileUrl.contains("/o/")) return;

            // Extract file path between "/o/" and "?alt="
            String encodedPath = fileUrl.substring(fileUrl.indexOf("/o/") + 3, fileUrl.indexOf("?alt="));
            String filePath = URLDecoder.decode(encodedPath, StandardCharsets.UTF_8);

            // Delete from Firebase
            Blob blob = StorageClient.getInstance().bucket().get(filePath);
            if (blob != null) {
                blob.delete();
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to delete file from Firebase", e);
        }
    }	
}
