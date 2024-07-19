package org.example.myblog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Schema(description = "名字")
    private String username;


    @Schema(description = "密码")
    private String password;

    @Schema(description = "邮箱")
    private String email;

    @OneToMany(mappedBy="user",cascade = CascadeType.PERSIST)
    private List<Post> postList;

    @Schema(description = "创建时间")
    @Column(insertable = false,updatable = false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime createTime;


    @Schema(description = "更新时间")
    @Column(insertable = false,updatable = false,columnDefinition="TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime updateTime;


}
