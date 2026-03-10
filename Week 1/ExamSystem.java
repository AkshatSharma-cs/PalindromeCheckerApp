// PROBLEM 9

import java.util.*;

class ExamSession {
    private final String studentId;
    private final String examId;
    private int violations;
    private boolean terminated;

    public ExamSession(String studentId, String examId) {
        this.studentId = studentId;
        this.examId = examId;
        this.violations = 0;
        this.terminated = false;
    }

    public String getStudentId() { return studentId; }
    public String getExamId() { return examId; }
    public int getViolations() { return violations; }
    public boolean isTerminated() { return terminated; }

    // Record suspicious activity
    public void recordViolation(String type) {
        if (terminated) return;
        violations++;
        System.out.println("Violation detected (" + type + ") for student " + studentId);
        if (violations >= 3) {
            terminated = true;
            System.out.println("Exam terminated for student " + studentId + " due to repeated violations.");
        }
    }
}

public class ExamSystem {
    private final Map<String, ExamSession> activeSessions; // studentId -> session

    public ExamSystem() {
        activeSessions = new HashMap<>();
    }

    // Start exam session
    public void startExam(String studentId, String examId) {
        if (activeSessions.containsKey(studentId)) {
            System.out.println("Student " + studentId + " already has an active exam.");
            return;
        }
        activeSessions.put(studentId, new ExamSession(studentId, examId));
        System.out.println("Exam started for student " + studentId + " on exam " + examId);
    }

    // Record suspicious activity
    public void reportSuspiciousActivity(String studentId, String type) {
        ExamSession session = activeSessions.get(studentId);
        if (session != null) {
            session.recordViolation(type);
        } else {
            System.out.println("No active exam found for student " + studentId);
        }
    }

    // End exam session
    public void endExam(String studentId) {
        ExamSession session = activeSessions.remove(studentId);
        if (session != null) {
            System.out.println("Exam ended for student " + studentId +
                    ". Violations: " + session.getViolations() +
                    ", Terminated: " + session.isTerminated());
        }
    }

    // Demo
    public static void main(String[] args) {
        ExamSystem system = new ExamSystem();

        system.startExam("student_001", "exam_math");
        system.reportSuspiciousActivity("student_001", "Tab switch");
        system.reportSuspiciousActivity("student_001", "Copy-paste attempt");
        system.reportSuspiciousActivity("student_001", "Multiple logins"); // triggers termination
        system.endExam("student_001");
    }
}
