package org.project.donkey.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix="file.upload")
public final class FileUploadConfig {
    private String path;
    private String url;
}