package org.project.donkey.api.admin;

import lombok.Data;

@Data
public class ConfigForm {
    private String siteTitle= "";
    private String siteDescription = "";
    private String cssJsVersion = "" + 1;
    private String joinTerms = "";
}