package com.example.restdemo2.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Builder
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "{NotBlank.task.title}")
    private String title;

    @NotBlank(message = "{NotBlank.task.description}")
    @Lob
    private String description;

    private int priority;

    public enum Priority {
        URGENT(5, "Khẩn cấp", "table-danger"),
        HIGH(4, "Cao", "table-warning"),
        MEDIUM(3, "Trung bình", "table-success"),
        LOW(2, "Thấp", "table-info"),
        NO(1, "Không", "table-secondary");

        private int code;
        private String name;
        private String classTable;

        Priority(int code, String name, String classTable) {
            this.code = code;
            this.name = name;
            this.classTable = classTable;
        }

        public static Priority getPriorityByCode(int value) {
            for (Priority level : Priority.values()) {
                if (level.code == value) return level;
            }
            throw new IllegalArgumentException("Kiểu cấp bậc ưu tiên không tồn tại!");
        }

        public int getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public String getClassTable() {
            return classTable;
        }
    }

    @ManyToOne(fetch = FetchType.EAGER)
    private Person person;
}
