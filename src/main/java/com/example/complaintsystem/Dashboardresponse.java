package com.example.complaintsystem;

public class Dashboardresponse {

    private Long totalcomplaints;
    private Long pendingcomplaints;
    private Long inprogresscomplaints;
    private Long resolvedcomplaints;
    private Long highprioritycomplaints;

    public Dashboardresponse(){

    }

    public void setTotalcomplaint(Long totalcomplaints){
        this.totalcomplaints = totalcomplaints;
    }
    public Long getTotalcomplaints(){
        return totalcomplaints;
    }

    public void setPendingcomplaints(Long pendingcomplaints){
        this.pendingcomplaints = pendingcomplaints;
    }
    public Long getPendingcomplaints(){
        return pendingcomplaints;
    }

    public void setInprogresscomplaints(Long inprogresscomplaints){
        this.inprogresscomplaints = inprogresscomplaints;
    }
    public Long getInprogresscomplaints(){
        return inprogresscomplaints;
    }

    public void setResolvedcomplaints(Long resolvedcomplaints){
        this.resolvedcomplaints = resolvedcomplaints;
    }
    public Long getResolvedcomplaints(){
        return resolvedcomplaints;
    }

    public void setHighprioritycomplaints(Long highprioritycomplaints){
        this.highprioritycomplaints = highprioritycomplaints;
    }
    public Long getHighprioritycomplaints(){
        return highprioritycomplaints;
    }
}

