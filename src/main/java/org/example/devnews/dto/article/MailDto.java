package org.example.devnews.dto.article;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class MailDto {
    private String url;
    private String title;
    private String companyName;
    private String summary;

    public MailDto(String url, String title, String companyName, String summary){
        this.url = url;
        this.title = title;
        this.companyName = companyName;
        this.summary = summary;
    }
}
