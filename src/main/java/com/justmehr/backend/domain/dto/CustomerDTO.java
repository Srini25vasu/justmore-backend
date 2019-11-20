package com.justmehr.backend.domain.dto;

import java.util.Set;

import com.justmehr.backend.domain.Course;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CustomerDTO extends UserDTO{

	Set<Course> courses;
}
