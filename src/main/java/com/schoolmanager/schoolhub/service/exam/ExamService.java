package com.schoolmanager.schoolhub.service.exam;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.schoolmanager.schoolhub.dto.ExamDto;
import com.schoolmanager.schoolhub.model.Classroom;
import com.schoolmanager.schoolhub.model.Exam;
import com.schoolmanager.schoolhub.model.Semester;
import com.schoolmanager.schoolhub.model.Subject;
import com.schoolmanager.schoolhub.repository.ExamRepository;
import com.schoolmanager.schoolhub.request.AddExamRequest;
import com.schoolmanager.schoolhub.service.classroom.IClassroomService;
import com.schoolmanager.schoolhub.service.semester.ISemesterService;
import com.schoolmanager.schoolhub.service.subject.ISubjectService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ExamService implements IExamService {

  private final ExamRepository examRepository;
  private final IClassroomService classroomService;
  private final ISemesterService semesterService;
  private final ISubjectService subjectService;
  private final ModelMapper modelMapper;

  @Override
  public Exam getExamById(Long id) {
    return examRepository.findById(id).orElseThrow(() -> new RuntimeException("fail"));
  }

  @Override
  public Exam addExam(AddExamRequest request) {
    Classroom classroom = classroomService.getClassroomByName(request.getClassroomName());
    Semester semester = semesterService.getSemesterBySemesterNameAndSchoolYearName(request.getSemesterName(),
        request.getSchoolYearName());
    Subject subject = subjectService.getSubjectByNameAndGradeLevel(request.getSubjectName(),
        classroom.getGrade().getLevel());
    Exam exam = new Exam();
    exam.setExamDate(request.getExamDate());
    exam.setExamType(request.getExamType());
    exam.setClassroom(classroom);
    classroom.getExams().add(exam);
    exam.setSemester(semester);
    semester.getExams().add(exam);
    exam.setSubject(subject);
    subject.getExams().add(exam);
    return examRepository.save(exam);
  }

  public ExamDto convertToDto(Exam exam) {
    ExamDto examDto = modelMapper.map(exam, ExamDto.class);
    examDto.setClassroomName(exam.getClassroom().getName());
    examDto.setSubjectName(exam.getSubject().getName());
    examDto.setSemesterName(exam.getSemester().getSemesterName());
    examDto.setSchoolYearName(exam.getSemester().getSchoolYear().getYearName());
    return examDto;
  }
}
