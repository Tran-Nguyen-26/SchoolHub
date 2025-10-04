package com.schoolmanager.schoolhub.service.student_parent;

import com.schoolmanager.schoolhub.dto.StudentParentDto;
import com.schoolmanager.schoolhub.model.StudentParent;

public interface IStudentParentService {
  StudentParent getStudentParentById(Long id);

  StudentParent getStudentParentByStudentIdAndParentId(Long studentId, Long parentId);

  StudentParent assignRelationship(Long studentId, Long parentId, String relationship);

  StudentParentDto convertToDto(StudentParent studentParent);
}
