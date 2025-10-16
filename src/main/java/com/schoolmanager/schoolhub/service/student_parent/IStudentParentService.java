package com.schoolmanager.schoolhub.service.student_parent;

import com.schoolmanager.schoolhub.dto.StudentParentDto;
import com.schoolmanager.schoolhub.model.StudentParent;

public interface IStudentParentService {

   /**
  * Internal method for business logic. Returns raw User entity.
  */

  StudentParent getStudentParentById(Long id);

  StudentParent getStudentParentByStudentIdAndParentId(Long studentId, Long parentId);

  StudentParent assignRelationship(Long studentId, Long parentId, String relationship);

  //=================================================//

  /**
  * Public-facing method. Returns sanitized UserDto for API response.
  */

  StudentParentDto getStudentParentDtoById(Long id);

  StudentParentDto getStudentParentDtoByStudentIdAndParentId(Long studentId, Long parentId);

  StudentParentDto assignRelationshipAndReturnDto(Long studentId, Long parentId, String relationship);


   /**
   * Convert raw to dto
   */  

  StudentParentDto convertToDto(StudentParent studentParent);
}
