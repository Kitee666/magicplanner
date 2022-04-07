package pl.umk.mat.planner.lecturer;

class LecturerNotFoundException extends RuntimeException {

    LecturerNotFoundException(Long id) {
        super("Could not find employee with id - " + id);
    }
}
