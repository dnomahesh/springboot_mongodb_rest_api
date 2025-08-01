package work.mywork.scm.spring_boot.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class StorageServiceImpl implements StorageService {

    
private static final Logger logger = LoggerFactory.getLogger(StorageServiceImpl.class);
    private static final CannedAccessControlList DEFAULT_ACL = CannedAccessControlList.PublicRead;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    private final AmazonS3 s3Client;

    public StorageServiceImpl(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public void init() {
        if (!s3Client.doesBucketExistV2(bucketName)) {
            logger.info("Bucket '{}' does not exist. Creating...", bucketName);
            s3Client.createBucket(bucketName);
        } else {
            logger.info("Bucket '{}' already exists.", bucketName);
        }
    }

    @Override
    public String store(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File must not be empty");
        }

        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
        String objectKey = UUID.randomUUID() + "_" + originalFilename;

        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());

            PutObjectRequest request = new PutObjectRequest(
                    bucketName,
                    objectKey,
                    file.getInputStream(),
                    metadata
            )
            // .withCannedAcl(DEFAULT_ACL)
            ;

            s3Client.putObject(request);

            String fileUrl = s3Client.getUrl(bucketName, objectKey).toString();
            logger.info("File uploaded to S3: {}", fileUrl);
            return fileUrl;

        } catch (IOException e) {
            logger.error("Failed to upload file to S3: {}", originalFilename, e);
            throw new RuntimeException("S3 upload failed for file: " + originalFilename, e);
        }
    }

    @Override
    public Stream<String> loadAll() {
        return s3Client.listObjects(bucketName)
                .getObjectSummaries()
                .stream()
                .map(S3ObjectSummary::getKey);
    }

    @Override
    public String load(String filename) {
        if (!s3Client.doesObjectExist(bucketName, filename)) {
            throw new RuntimeException("File not found in S3: " + filename);
        }
        return s3Client.getUrl(bucketName, filename).toString();
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            S3Object s3Object = s3Client.getObject(bucketName, filename);
            return new InputStreamResource(s3Object.getObjectContent());
        } catch (AmazonS3Exception e) {
            logger.error("Error loading S3 object as resource: {}", filename, e);
            return null;
        }
    }

    @Override
    public void deleteAll() {
        logger.info("Deleting all files in bucket '{}'", bucketName);
        ObjectListing objectListing = s3Client.listObjects(bucketName);
        for (S3ObjectSummary summary : objectListing.getObjectSummaries()) {
            s3Client.deleteObject(bucketName, summary.getKey());
            logger.info("Deleted file: {}", summary.getKey());
        }
    }
}