package com.hvn.youtubeclone.dto;

import com.hvn.youtubeclone.model.VideoStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VideoDto {
    private String id;
    private String title;
    private String description;
    private Set<String> tags;
    private String videoUrl;
    private VideoStatus videoStatus;
    private String thumbnailUrl;
}
