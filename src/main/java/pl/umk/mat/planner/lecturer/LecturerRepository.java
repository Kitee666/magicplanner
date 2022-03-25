package pl.umk.mat.planner.lecturer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LecturerRepository extends JpaRepository<Lecturer, Long> {
}