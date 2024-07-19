package org.example.myblog.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "内容")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Schema(description = "作者ID")
    @ManyToOne
    private User user;

    @Schema(description = "创建时间")
    @Column(nullable = false, updatable = false)
    private LocalDateTime created;

    @Schema(description = "更新时间")
    @Column(nullable = false)
    private LocalDateTime lastModified;

}
