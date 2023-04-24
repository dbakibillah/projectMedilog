package com.example.projectmedilog;

public class MedicalRecordsTable {
    private Integer id;
    private String date;
    private String reference;
    private String testName;
    private String testResult;
    private String comment;
    private String conclusion;

    public MedicalRecordsTable(Integer id, String date, String reference, String testName, String testResult, String comment, String conclusion) {
        this.id = id;
        this.date = date;
        this.reference = reference;
        this.testName = testName;
        this.testResult = testResult;
        this.comment = comment;
        this.conclusion = conclusion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(String id) {
        this.id = Integer.valueOf(id);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }
}
